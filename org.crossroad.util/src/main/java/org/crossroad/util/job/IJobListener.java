/**
 * 
 */
package org.crossroad.util.job;



/**
 * @author e.soden
 *
 */
public interface IJobListener {
	public void onFinishExecution(IJobResult results) throws JobException;
}
