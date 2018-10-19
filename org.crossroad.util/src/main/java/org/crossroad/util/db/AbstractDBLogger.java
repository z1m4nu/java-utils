/**
 * 
 */
package org.crossroad.util.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import org.crossroad.util.AbstractLogger;

/**
 * @author e.soden
 *
 */
public abstract class AbstractDBLogger extends AbstractLogger {
	protected HashMap<String, Connection> connections = new HashMap<String, Connection>();

	/**
	 * 
	 */
	public AbstractDBLogger() {
	}

	protected void connect(String id, String driverName, String url, String username, String password) throws Exception {
		Class driverClass = this.getClass().getClassLoader().loadClass(driverName);
		Driver driver = (Driver) driverClass.newInstance();

		Properties props = new Properties();
		props.setProperty("user", username);
		props.setProperty("password", password);
		this.connections.put(id, driver.connect(url, props));

	}
	
	protected Connection getConnection(String key) throws Exception
	{
		if (this.connections.containsKey(key))
		{
			return this.connections.get(key);
		} else {
			throw new Exception("Connection ["+key+"] does not exist");
		}
	}
	
	protected void disconnect(String key) throws SQLException
	{
		if (this.connections.containsKey(key))
		{
			this.connections.get(key).close();
		}
	}

	protected void disconnectAll()  {
		if (!this.connections.isEmpty()) {
			for (String key : connections.keySet()) {
				
				try {
					this.disconnect(key);
				} catch (SQLException e) {
					log.error("Unable to close connection ["+key+"]",e);
				}
			}
		}
	}
	
	protected PreparedStatement getPrepareStatement(String connId, String statement) throws SQLException, Exception
	{
		return this.getConnection(connId).prepareStatement(statement);
	}

}
