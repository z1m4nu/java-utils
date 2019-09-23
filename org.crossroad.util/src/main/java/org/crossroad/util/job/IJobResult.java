/**
 * 
 */
package org.crossroad.util.job;

import java.util.Map;

import org.crossroad.util.time.TimeContent;

/**
 * @author e.soden
 *
 */
public interface IJobResult {

	/**
	 * @return the total
	 */
	long getTotal();

	/**
	 * @param total the total to set
	 */
	void setTotal(long total);

	/**
	 * @return the success
	 */
	long getSuccess();

	/**
	 * @param success the success to set
	 */
	void setSuccess(long success);

	/**
	 * @return the error
	 */
	long getError();

	/**
	 * @param error the error to set
	 */
	void setError(long error);

	/**
	 * @return the unproceed
	 */
	long getUnproceed();

	/**
	 * @param unproceed the unproceed to set
	 */
	void setUnproceed(long unproceed);

	//void addTimes(Map<String, TimeContent> times);

	//void addTime(String key, Long value);

	Map<String, TimeContent> getTimes();

	long getExecutionTime();

	void setTimes(Map<String, TimeContent> times);
}
