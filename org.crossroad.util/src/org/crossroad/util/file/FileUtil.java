/**
 * 
 */
package org.crossroad.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author e.soden
 *
 */
public final class FileUtil {
	private static final Log log = LogFactory.getLog(FileUtil.class);

	/**
	 * 
	 * @param inputStream
	 * @param output
	 * @return
	 * @throws IOException
	 */
	public static boolean unzip(InputStream inputStream, String output) throws IOException, ZipException {

		ZipInputStream zis = new ZipInputStream(inputStream);
		ZipEntry entry;
		boolean isEmpty = true;
		File outputFolder = null;

		try {

			outputFolder = new File(output);
			if (outputFolder.exists() && outputFolder.isDirectory() && outputFolder.canWrite()) {
				zis = new ZipInputStream(inputStream);

				while ((entry = zis.getNextEntry()) != null) {
					isEmpty = false;
					File newFile = new File(outputFolder, entry.getName());
					if (newFile.getParentFile().mkdirs() && !entry.isDirectory()) {
						FileOutputStream fos = new FileOutputStream(newFile);
						IOUtils.copy(zis, fos);
						fos.close();
					}
				}
			} else {
				throw new ZipException(output + " must point to a directory and have write access");
			}

		} finally {
			if (zis != null) {
				zis.close();
			}
			outputFolder = null;
			entry = null;
			zis = null;
		}

		return !isEmpty;
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String readTextFile(String file) throws Exception {
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;

			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return buffer.toString();
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static Properties loadProperties(String file) throws Exception {
		Properties properties = null;
		File f = new File(file);

		if (f.exists()) {
			properties = new Properties();
			properties.load(new FileInputStream(f));
		}
		return properties;
	}

	/**
	 * Utility method to save InputStream data to target location/file
	 * 
	 * @param inStream
	 *            - InputStream to be saved
	 * @param target
	 *            - full path to destination file
	 */
	public static void saveToFile(InputStream inStream, String target) throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		try {
			out = new FileOutputStream(new File(target));
			while ((read = inStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} finally {

			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * Creates a folder to desired location if it not already exists
	 * 
	 * @param dirName
	 *            - full path to the folder
	 * @throws Exception
	 *             - in case you don't have permission to create the folder
	 */
	public static void createFolder(String dirName) throws Exception {
		String sep = "/";
		int index = 0;
		if ((index = dirName.indexOf("\\")) > 0) {
			sep = "\\\\";
		} else {
			sep = "/";
		}

		String pathPart[] = dirName.split(sep);
		StringBuffer pathToCreate = new StringBuffer();
		for (String p : pathPart) {
			if (pathToCreate.length() > 0) {
				pathToCreate.append(sep);
			}

			pathToCreate.append(p);

			File theDir = new File(pathToCreate.toString());
			if (!theDir.exists()) {
				theDir.mkdir();
			}
			theDir = null;

		}
	}

}
