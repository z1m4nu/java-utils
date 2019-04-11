/**
 * 
 */
package org.crossroad.util.config.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.crossroad.util.config.IBaseConfiguration;
import org.crossroad.util.config.IConfigDir;
import org.crossroad.util.exception.ConfigurationException;
import org.crossroad.util.exception.InitializationException;

/**
 * @author e.soden
 *
 */
public abstract class AbstractConfiguration implements IBaseConfiguration {

	private DirConfig directoryConf = null;
	private Properties properties = new Properties();
	protected String confFile = "configuration.properties";

	/**
	 * 
	 */
	public AbstractConfiguration() {

	}

	public void load(String homeDir) throws InitializationException {
		FileInputStream stream = null;
		File file = null;
		System.out.println("Loading home directory ["+homeDir+"]");
		try {
			preLoading();
			if (!properties.isEmpty()) {
				properties.clear();
			}

			directoryConf = new DirConfig(homeDir);
			onLoadDirectory();
			
			file = directoryConf.getConfDir().resolve(confFile).toFile();
			if (file.exists()) {
				stream = new FileInputStream(file);
				properties.load(stream);

				postLoading();
			} else {
				throw new Exception("Unable to load [" + file.getPath() + "]");
			}
		} catch (Exception e) {
			throw new InitializationException("Unable to load configuration file", e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {

				}
				stream = null;
			}

			file = null;
		}
	}
	
	public abstract void onLoadDirectory() throws ConfigurationException;

	public abstract void preLoading() throws ConfigurationException;

	public abstract void postLoading() throws ConfigurationException;

	/**
	 * @param key
	 * @return
	 * @see java.util.Hashtable#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key) {
		return properties.containsKey(key);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 * @see java.util.Properties#getProperty(java.lang.String, java.lang.String)
	 */
	public String getProperty(String key, String defaultValue) {		
		return properties.getProperty(key, defaultValue);
	}

	public IConfigDir getAppStorage() {
		return this.directoryConf;
	}

	public String getTemplatedProperty(String prpName) {

		String parsedString = getProperty(prpName, null);
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(parsedString);

		while (matcher.find()) {
			String key = matcher.group(1);
			String replace = getProperty(key, null);

			parsedString = parsedString.replaceAll("\\$\\{" + key + "\\}", replace);

		}

		return parsedString;

	}

	public boolean getBooleanProperty(String key, String def) {
		return Boolean.parseBoolean(getProperty(key, ((def != null) ? def : "true")));
	}
	
	public int getIntProperty(String key, String def) {
		return Integer.valueOf(getProperty(key, ((def != null) ? def : "0")));
	}
}
