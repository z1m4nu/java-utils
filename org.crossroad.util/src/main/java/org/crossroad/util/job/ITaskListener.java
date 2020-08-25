/**
 * 
 */
package org.crossroad.util.job;

import java.util.Map;

import org.crossroad.util.Status;
import org.crossroad.util.time.TimeContent;

/**
 * @author e.soden
 *
 */
public interface ITaskListener {
	void setStatus(Status status, Map<String, TimeContent> times);
	
	void onFinish(ITask task);
}
