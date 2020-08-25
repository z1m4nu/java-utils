/**
 * 
 */
package org.crossroad.util.job;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.crossroad.util.AbstractTimer;
import org.crossroad.util.Status;

/**
 * @author e.soden
 *
 */
public abstract class AbstractTask extends AbstractTimer  implements Runnable, ITask{
	

	private List<ITaskListener> listeners = new ArrayList<ITaskListener>();
	private String id = UUID.randomUUID().toString();
	
	private Status status = Status.UNKNOWN;
	
	/**
	 * 
	 */
	public AbstractTask() {
		// TODO Auto-generated constructor stub
	}

	
	protected List<ITaskListener> getListeners() {
		return listeners;
	}
	
	public void addListener(ITaskListener listener) {
		this.listeners.add(listener);
	}

	public void removeListener(ITaskListener listener) {
		listeners.remove(listener);
	}
	
	protected synchronized void informListeners(Status status) {
		for (ITaskListener listener: listeners)
		{
			listener.setStatus(status, getTimes());
		}
	}
	
	protected synchronized void informListeners()
	{
		for (ITaskListener listener:listeners)
		{
			listener.onFinish(this);
		}
	}

	/* (non-Javadoc)
	 * @see org.crossroad.util.job.ITask#getId()
	 */
	@Override
	public String getId() {
		return id;
	}
	
	protected abstract void clean(); 
	
	/* (non-Javadoc)
	 * @see org.crossroad.util.job.ITask#getStatus()
	 */
	@Override
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
