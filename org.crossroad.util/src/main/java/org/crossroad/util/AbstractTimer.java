/**
 * 
 */
package org.crossroad.util;

import java.util.HashMap;
import java.util.Map;

import org.crossroad.util.time.TimeContent;

/**
 * @author e.soden
 *
 */
public class AbstractTimer extends AbstractLogger implements ITimer  {
	private Map<String, TimeContent> times = new HashMap<String, TimeContent>();
	/**
	 * 
	 */
	public AbstractTimer() {
		// TODO Auto-generated constructor stub
	}
	
	protected void saveTime(String key, Long value)
	{
		if (this.times.containsKey(key))
		{
			TimeContent tc = this.times.get(key);
			tc.addTime(value);
			this.times.replace(key, tc);
		} else {
			this.times.put(key, new TimeContent(value));
		}
	}
	
	protected void saveTimes(Map<String, TimeContent> map)
	{
		for(String key:map.keySet())
		{
			TimeContent nTc = map.get(key);
			if (this.times.containsKey(key))
			{
				TimeContent tc = this.times.get(key);
				tc.update(nTc.getTime(), nTc.getMember());
				this.times.replace(key, tc);
			} else {
				this.times.put(key, nTc);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.crossroad.util.ITimer#getTimes()
	 */
	@Override
	public Map<String, TimeContent> getTimes()
	{
		return this.times;
	}
	
}
