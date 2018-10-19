/**
 * 
 */
package org.crossroad.util.renderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * @author e.soden
 *
 */
public class CSVFileStringRenderer extends StringTableRenderer {

	private String outFileName = null;

	/**
	 * @param table
	 */
	public CSVFileStringRenderer(OutTable table, String outFile) {
		super();
		this.setTable(table);
		this.outFileName = outFile;
	}

	@Override
	public void postRendering() throws RenderException {
		super.postRendering();
        long start = System.currentTimeMillis();
		OutputStreamWriter dataWriter = null;
		try {
			if (outFileName != null) {
				File outFile = new File(outFileName);
				dataWriter = new OutputStreamWriter(new FileOutputStream(outFile),
						Charset.forName("UTF-8").newEncoder());
				dataWriter.write(super.getRenderedString());
				dataWriter.flush();
				
				log.info(outFileName + " generated.");
			} else {
				throw new RenderException("Output file is not set");
			}
		} catch (Exception e) {
			throw new RenderException(e);
		} finally {
			if (dataWriter != null) {
				try {
					dataWriter.flush();
					dataWriter.close();
				} catch (Exception e) {
					log.warn(e);
				}
			}
			
			log.info("Write csv file in "+(System.currentTimeMillis() - start)+" ms.");
		}
	}

}
