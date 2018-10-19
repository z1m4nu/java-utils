/**
 * 
 */
package org.crossroad.util.time;

import java.util.HashMap;
import java.util.Map;

/**
 * @author e.soden
 *
 */
public class TimeMonitorController {
	private static final TimeMonitorController INSTANCE = new TimeMonitorController();
	public static String TOTAL = "T";
	private long totalTime = 0L;
	
	private Map<String, Long> times = new HashMap<String, Long>();

	/**
	 * 
	 */
	private TimeMonitorController() {
		times.put(TOTAL, 0L);
	}
	
	public static TimeMonitorController getInstance() {
		return INSTANCE;
	}
	
	public void addToTotalTime(long time)
	{
		this.totalTime += time;
	}
	
	public void addStepTime(String step, long time)
	{
		this.times.put(step, time);
	}
	
	public long getStepTime(String step)
	{
		return this.times.get(step);
	}
	
	public long getTotalTime()
	{
		return this.totalTime;
	}

}
