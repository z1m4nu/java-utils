/**
 * 
 */
package org.crossroad.util.file;

import java.nio.file.Path;


/**
 * @author e.soden
 *
 */
public interface IFileFinderListener {
	void onFindFile(Path file);
	
	void onFindError(Path file, Exception e);
	
	void onFindFinish();
}
