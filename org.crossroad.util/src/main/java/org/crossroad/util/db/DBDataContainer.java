/**
 * 
 */
package org.crossroad.util.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UnknownFormatConversionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.crossroad.util.AbstractLogger;

/**
 * @author e.soden
 *
 */
public class DBDataContainer extends AbstractLogger {
	private Map<String, IDBData> values = new HashMap<String, IDBData>();

	/**
	 * 
	 */
	public DBDataContainer() {
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

		log.debug("Template [" + template + "]");

		parsedString = template.replace(splitter, separator);

		log.debug("Template with separator [" + parsedString + "]");

		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.DOTALL);
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

			parsedString = parsedString.replaceAll("\\$\\{" + key + "\\}", replace);

		}

		log.debug("ParsedString [" + parsedString + "]");
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
