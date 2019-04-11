/**
 * 
 */
package org.crossroad.util.config.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.crossroad.util.config.IConfigDir;

/**
 * @author e.soden
 *
 */
public final class DirConfig implements IConfigDir {
	private Path home = null;

	/**
	 * 
	 */
	public DirConfig(String home) {
		this.home = Paths.get(home);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.crossroad.util.config.IConfigDir#getHomeDir()
	 */
	public Path getHomeDir() {
		return this.home;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.crossroad.util.config.IConfigDir#getLogDir()
	 */
	public Path getLogDir() {
		return getHomeDir().resolve(LOG_FOLDER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.crossroad.util.config.IConfigDir#getConfDir()
	 */
	public Path getConfDir() {
		return getHomeDir().resolve(ETC_FOLDER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.crossroad.util.config.IConfigDir#getTmpDir()
	 */
	public Path getTmpDir() {
		return getHomeDir().resolve(TMP_FOLDER);
	}

	public Path getLibDir() {
		return getHomeDir().resolve(LIB_FOLDER);
	}

	@Override
	public Path getBinDir() {
		return getHomeDir().resolve(BIN_FOLDER);
	}

}
