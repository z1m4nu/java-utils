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
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;

/**
 * @author e.soden
 *
 */
public final class FileUtil {

	/**
	 * Wraps the input stream with GZIPInputStream if needed.
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 * @throws ZipConvertionException
	 */
	public static InputStream convertToZip(InputStream inputStream) throws IOException, ZipConvertionException {
		inputStream = new ZipInputStream(inputStream);

		if (((ZipInputStream) inputStream).getNextEntry() == null) {
			throw new ZipConvertionException("Stream is not a zip file.");
		}

		return inputStream;
	}

	/**
	 * 
	 * @param inputStream
	 * @param output
	 * @return
	 * @throws IOException
	 */
	public boolean unzip(InputStream inputStream, String output) throws IOException {

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

}
