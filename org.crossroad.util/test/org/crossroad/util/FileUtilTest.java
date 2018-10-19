/**
 * 
 */
package org.crossroad.util;

import java.util.UUID;

import org.crossroad.util.file.FileUtil;

/**
 * @author e.soden
 *
 */
public class FileUtilTest {

	/**
	 * 
	 */
	public FileUtilTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		FileUtil.createFolder("d:\\tmp\\upload\\"+UUID.randomUUID().toString()+"\\test.jar");

	}

}
