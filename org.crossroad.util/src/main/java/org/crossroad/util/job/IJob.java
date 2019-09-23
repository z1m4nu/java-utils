/**
 * 
 */
package org.crossroad.util.job;



/**
 * @author e.soden
 *
 */
public interface IJob extends ITaskListener {
	public void work() throws JobException;
}
