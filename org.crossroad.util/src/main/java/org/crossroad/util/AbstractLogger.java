/**
 * 
 */
package org.crossroad.util;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author e.soden
 *
 */
public abstract class AbstractLogger {
	protected final Logger log = LogManager.getLogger(this.getClass().getName());

	protected StopWatch sw = new StopWatch();

	/**
	 * 
	 */
	public AbstractLogger() {

	}

}
