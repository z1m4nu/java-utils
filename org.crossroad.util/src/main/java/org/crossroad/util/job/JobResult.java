/**
 * 
 */
package org.crossroad.util.job;

import java.util.HashMap;
import java.util.Map;

import org.crossroad.util.time.TimeContent;

/**
 * @author e.soden
 *
 */
public class JobResult implements IJobResult {
	private long total = 0;
	private long success = 0;
	private long error = 0;
	private long unproceed = 0;
	private Map<String, TimeContent> times = new HashMap<String, TimeContent>();

	/**
	 * 
	 */
	public JobResult() {
		// TODO Auto-generated constructor stub
	}
	
	
	/* (non-Javadoc)
	 * @see org.crossroad.util.job.IJobResult#getTotal()
	 */
	@Override
	public long getTotal() {
		return total;
	}

	/* (non-Javadoc)
	 * @see org.crossroad.util.job.IJobResult#setTotal(long)
	 */
	@Override
	public void setTotal(long total) {
		this.total = total;
	}

	/* (non-Javadoc)
	 * @see org.crossroad.util.job.IJobResult#getSuccess()
	 */
	@Override
	public long getSuccess() {
		return success;
	}

	/* (non-Javadoc)
	 * @see org.crossroad.util.job.IJobResult#setSuccess(long)
	 */
	@Override
	public void setSuccess(long success) {
		this.success = success;
	}

	/* (non-Javadoc)
	 * @see org.crossroad.util.job.IJobResult#getError()
	 */
	@Override
	public long getError() {
		return this.error;
	}

	/* (non-Javadoc)
	 * @see org.crossroad.util.job.IJobResult#setError(long)
	 */
	@Override
	public void setError(long error) {
		this.error = error;
	}

	/* (non-Javadoc)
	 * @see org.crossroad.util.job.IJobResult#getUnproceed()
	 */
	@Override
	public long getUnproceed() {
		return this.unproceed;
	}

	/* (non-Javadoc)
	 * @see org.crossroad.util.job.IJobResult#setUnproceed(long)
	 */
	@Override
	public void setUnproceed(long unproceed) {
		this.unproceed = unproceed;
	}

	/* (non-Javadoc)
	 * @see org.crossroad.util.job.IJobResult#getTimes()
	 */
	@Override
	public Map<String, TimeContent> getTimes() {
		return this.times;
	}

	/* (non-Javadoc)
	 * @see org.crossroad.util.job.IJobResult#getExecutionTime()
	 */
	@Override
	public long getExecutionTime() {
		return this.times.get("MAIN").getTime();
	}

	/* (non-Javadoc)
	 * @see org.crossroad.util.job.IJobResult#setTimes(java.util.Map)
	 */
	@Override
	public void setTimes(Map<String, TimeContent> times) {
		this.times = times;

	}

}
