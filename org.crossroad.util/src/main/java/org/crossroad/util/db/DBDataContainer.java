/**
 * 
 */
package org.crossroad.util.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.crossroad.util.AbstractLogger;

/**
 * @author e.soden
 *
 */
public class DBDataContainer extends AbstractLogger implements Serializable {
	private Map<String, IDBData> values = new HashMap<String, IDBData>();

	/**
	 * 
	 */
	protected DBDataContainer() {
		super();
	}

	public void addData(IDBData data) {
		values.put(data.getName(), data);
	}

	public void addAll(Map<String, IDBData> map) {
		this.values.putAll(map);
	}

	public IDBData get(String key) {
		return this.values.get(key);
	}

	public Map<String, IDBData> getData() {
		return this.values;
	}
	
	public String computeString(String template, String splitter, String separator) throws Exception {
		String parsedString = null;

		log.trace("Template [" + template + "]");

		parsedString = template.replace(splitter, separator);

		log.trace("Template with separator [" + parsedString + "]");

		Pattern pattern = Pattern.compile("\\$\\[(.+?)\\]", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(parsedString);

		while (matcher.find()) {
			String key = matcher.group(1);
			String replace = "";

			if (key.contains("@")) {
				String[] l = key.split("@");
				if (values.containsKey(l[0]))
					replace = DataUtil.formatAsString(values.get(l[0]), l[1]);
			} else {
				if (values.containsKey(key))
					replace = values.get(key).getValueAsString();
			}

			parsedString = parsedString.replaceAll("\\$\\[" + key + "\\]", replace);

		}

		log.trace("ParsedString [" + parsedString + "]");
		return parsedString;
	}


	public static DBDataContainer loadFromFile(String file) throws Exception {
		DBDataContainer container = new DBDataContainer();
		ObjectInputStream reader = null;

		reader = new ObjectInputStream(new FileInputStream(file));
		container.addAll((Map<String, IDBData>) reader.readObject());
		
		reader.close();

		return container;
	}
	
	public static DBDataContainer createEmpty() {
		return new DBDataContainer();
	}

	public static DBDataContainer loadFromDatas(Map<String, IDBData> map) {
		DBDataContainer container = createEmpty();
		container.addAll(map);
		
		return container;
	}
	
	public synchronized void serialize(String output) {
		ObjectOutputStream writer = null;
		try {
			writer = new ObjectOutputStream(new FileOutputStream(output));
			writer.writeObject(values);
			writer.flush();
		} catch (Exception e) {
			log.error("Unable to dump data", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					log.error("Unable to close the writer", e);
				}
			}
		}
	}


	/**
	 * @return
	 * @see java.util.Map#size()
	 */
	public int size() {
		return values.size();
	}

	/**
	 * @return
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty() {
		return values.isEmpty();
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key) {
		return values.containsKey(key);
	}

	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		values.clear();
	}

}
