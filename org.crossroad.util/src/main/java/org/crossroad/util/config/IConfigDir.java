/**
 * 
 */
package org.crossroad.util.config;

import java.nio.file.Path;

/**
 * @author e.soden
 *
 */
public interface IConfigDir {
	public static final String BIN_FOLDER = "bin";
	public static final String ETC_FOLDER = "etc";
	public static final String LIB_FOLDER = "lib";
	public static final String LOG_FOLDER = "log";
	public static final String TMP_FOLDER = "tmp";
	

	Path getHomeDir();

	Path getBinDir();
	
	Path getConfDir();

	Path getLibDir();

	Path getLogDir();

	Path getTmpDir();
	
}
