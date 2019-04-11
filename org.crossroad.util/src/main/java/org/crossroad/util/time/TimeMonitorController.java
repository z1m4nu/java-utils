/**
 * 
 */
package org.crossroad.util.time;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
	
	public synchronized static TimeMonitorController getInstance() {
		return INSTANCE;
	}
	
	public synchronized void addToTotalTime(long time)
	{
		this.totalTime += time;
	}
	
	public synchronized void addStepTime(String step, long time)
	{

		this.times.put(step +"#"+UUID.randomUUID().toString(), time);
	}
	
	public synchronized long getStepTime(String step)
	{
		return this.times.get(step);
	}
	
	public long getTotalTime()
	{
		return this.totalTime;
	}

	
	public String summary()
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("Total execution time " + getTotalTime() + " ms.\n");
		
		for (String key:times.keySet())
		{
			buffer.append("\t"+key + " execution time "+times.get(key)+ " ms.\n");
		}
		
		return buffer.toString();
	}
	
	public Set<String> getSteps()
	{
		return this.times.keySet();
	}
}
