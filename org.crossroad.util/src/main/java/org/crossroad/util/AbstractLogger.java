/**
 * 
 */
package org.crossroad.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.crossroad.util.time.TimeMonitorController;

/**
 * @author e.soden
 *
 */
public abstract class AbstractLogger {
	protected Log log = LogFactory.getLog(this.getClass());
	private long start = 0L;

	/**
	 * 
	 */
	public AbstractLogger() {

	}

	protected void startTime() {
		start = System.currentTimeMillis();
	}

	protected void stopTime(String stepname, boolean main) {
		long end = System.currentTimeMillis() - start;

		TimeMonitorController.getInstance().addStepTime(stepname, end);

		if (main)
			TimeMonitorController.getInstance().addToTotalTime(end);
	}

	protected long getTime(String step)
	{
		return TimeMonitorController.getInstance().getStepTime(step);
	}
}
