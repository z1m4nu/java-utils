/**
 * 
 */
package org.crossroad.util.file;

import java.io.File;
import java.nio.file.FileSystems;

/**
 * @author e.soden
 *
 */
public final class FileUtil {

	/**
	 * 
	 */
	private FileUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static void createDirs(String path) throws Exception 
	{
		File f = new File(path);
		if (!f.exists()) {
			if (!f.mkdirs())
				throw new Exception("Unable to create directory [" + path + "]");
		}
	}
	
	public static boolean isPosix()
	{
		return FileSystems.getDefault().supportedFileAttributeViews().contains("posix");
	}

}
