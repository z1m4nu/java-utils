/**
 * 
 */
package org.crossroad.util.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

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
	
	
	public static void createDirectory(Path path, String perms, boolean isPosix) throws IOException {

		Set<PosixFilePermission> permissions = null;
		FileAttribute<Set<PosixFilePermission>> attr = null;

		try {
			if (isPosix) {
				if (perms != null) {
					permissions = PosixFilePermissions.fromString(perms);
					attr = PosixFilePermissions.asFileAttribute(permissions);
				}
				Files.createDirectories(path, (attr != null) ? attr : null);

				Files.setPosixFilePermissions(path, permissions);
			} else {
				Files.createDirectories(path);
			}
		} finally {
			permissions = null;
			attr = null;
		}
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
