/**
 * 
 */
package org.crossroad.util.file.journal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author e.soden
 *
 */
public final class JournalEntry {
	private Map<String, String> entry = new HashMap<String, String>();
	/**
	 * 
	 */
	public JournalEntry() {
	}

	
	public void addField(String key, String value)
	{
		this.entry.put(key, value);
	}


	/**
	 * @param key
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(String key) {
		return entry.containsKey(key);
	}


	/**
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public String get(String key) {
		return entry.get(key);
	}
	
	
}
