/**
 * 
 */
package org.crossroad.util.font;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;



/**
 * @author e.soden
 *
 */
public class FontContainer {
	private Map<String, IFont> map = new HashMap<String,IFont>();

	/**
	 * 
	 */
	public FontContainer() {
	}

	/**
	 * @return
	 * @see java.util.Map#size()
	 */
	public int size() {
		return map.size();
	}

	/**
	 * @return
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean hasFont(String key) {
		return map.containsKey(key);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public IFont getFont(String key) {
		return map.get(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public void addFont(String key, IFont value) {
		map.put(key, value);
	}

	
	

	/**
	 * @param m
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void addFonts(Map<String, IFont> m) {
		map.putAll(m);
	}

	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		map.clear();
	}

	/**
	 * @return
	 * @see java.util.Map#keySet()
	 */
	public Set<String> keySet() {
		return map.keySet();
	}
	
	

}
