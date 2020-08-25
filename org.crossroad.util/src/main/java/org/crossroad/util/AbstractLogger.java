/**
 * 
 */
package org.crossroad.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author e.soden
 *
 */
public abstract class AbstractLogger {
	protected final Logger log = LogManager.getLogger(this.getClass().getName());

	

	/**
	 * 
	 */
	public AbstractLogger() {

	}

}
