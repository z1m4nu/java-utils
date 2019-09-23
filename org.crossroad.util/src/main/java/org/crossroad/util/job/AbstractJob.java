/**
 * 
 */
package org.crossroad.util.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.crossroad.util.AbstractTimer;
import org.crossroad.util.Status;
import org.crossroad.util.time.TimeContent;

/**
 * @author e.soden
 *
 */
public abstract class AbstractJob extends AbstractTimer implements IJob {

	protected ExecutorService executorService = null;

	private List<IJobListener> listeners = new ArrayList<IJobListener>();

	private String id = null;

	protected long total = 0;
	protected long error = 0;
	protected long success = 0;
	protected long unproceed = 0;

	protected IJobResult results = new JobResult();

	public AbstractJob() {
		super();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void addListener(IJobListener listener) {
		this.listeners.add(listener);
	}

	public void removeListener(IJobListener listener) {
		this.listeners.remove(listener);
	}

	private void buildExecutor() {
		if (executorService == null) {

			int cpu = Runtime.getRuntime().availableProcessors();
			int corePool = (cpu <= 4) ? cpu : cpu - 4;
			int maxPool = (cpu <= 2) ? cpu : cpu - 2;
			long timeout = 1000;

			StringBuffer buffer = new StringBuffer();
			buffer.append("\nExecutor service\n\t- Thread core size #").append(corePool)
					.append("\n\t- Thread Max size #").append(maxPool).append("\n\t- KeepAlive timeout #")
					.append(timeout).append(" ms.\n");

			log.info(buffer.toString());

			executorService = getExecutorService(corePool, maxPool, timeout, TimeUnit.MILLISECONDS);

		}
	}

	private void initialize() throws JobException {

		this.results = createJobResult();
		buildExecutor();

	}

	@Override
	public void work() throws JobException {
		sw.start();
		log.info("\n\t- Job ID:\t" + this.id);
		initialize();

		try {
			doWork();
		} finally {
			this.executorService.shutdown();

			try {
				executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			} catch (InterruptedException e) {
				log.error("Executor service interrupted", e);
			}

			postWork();

			results.setError(error);
			results.setSuccess(success);
			results.setTotal(total);
			results.setUnproceed(unproceed);

			sw.stop();
			saveTime("MAIN", sw.getTime());

			results.setTimes(getTimes());

			for (IJobListener listener : listeners) {
				listener.onFinishExecution(results);
			}

		}

	}

	protected abstract void postWork() throws JobException;

	protected abstract void doWork() throws JobException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.crossroad.util.job.ITaskListener#setStatus(org.crossroad.util.Status,
	 * java.util.Map)
	 */
	public synchronized void setStatus(Status status, Map<String, TimeContent> times) {

		saveTimes(times);

		switch (status) {
		case ERROR:
		case WARNING:
		case UNKNOWN:
			error++;
			break;
		case OK:
			success++;
			break;
		}
	}

	protected void submitTask(AbstractTask task) {
		executorService.submit(task);
	}

	protected abstract IJobResult createJobResult();

	protected abstract ExecutorService getExecutorService(int corePool, int maxPool, long timeout, TimeUnit unit);
}
