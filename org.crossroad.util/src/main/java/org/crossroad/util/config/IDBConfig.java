/**
 * 
 */
package org.crossroad.util.config;

import org.crossroad.util.db.DBConnector;

/**
 * @author e.soden
 *
 */
public interface IDBConfig {
	DBConnector getDBConnector();
}
