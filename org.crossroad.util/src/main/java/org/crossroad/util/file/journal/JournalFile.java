/**
 * 
 */
package org.crossroad.util.file.journal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.crossroad.util.AbstractLogger;
import org.crossroad.util.exception.JournalException;

/**
 * @author e.soden
 *
 */
public class JournalFile extends AbstractLogger {
	private List<JournalEntry> entries = new ArrayList<JournalEntry>();
	private String SEP = ",";
	private String keys[] = null;
	private Path file = null;

	/**
	 * 
	 */
	public JournalFile(Path file, String[] keys) {
		this.keys = keys;
		this.file = file;
	}
	
	public void init() throws JournalException
	{
		BufferedWriter writer = null;
		try {
			
			File journal = file.toFile();
			
			if (!journal.exists())
			{
				StringBuffer buffer = new StringBuffer();
			
				writer = new BufferedWriter(new FileWriter(journal,true));
				for (String key:keys)
				{
					buffer.append(key).append(SEP);
				}
				
				buffer.append("\n");
				
				writer.write(buffer.toString());
				writer.flush();
			}
		} catch (Exception e) {
			log.error("Unable to flush entries", e);
			throw new JournalException(e);
		} finally {
			if (writer != null) {
				try {
					writer.flush();
					writer.close();
				} catch (Exception e) {
					log.error("Unable to close journal file handle", e);
				}
			}
		}
	}

	public void addEntry(JournalEntry entry) throws JournalException {
		this.entries.add(entry);
	}

	public List<JournalEntry> getEntries() {
		return entries;
	}

	public void flushEntries() throws JournalException {
		BufferedWriter writer = null;

		try {
			init();
			writer = new BufferedWriter(new FileWriter(file.toFile(),true));
			StringBuffer buffer = new StringBuffer();
			for (JournalEntry entry : entries) {
				for (String key : keys) {
					if (entry.containsKey(key)) {
						buffer.append(entry.get(key)).append(SEP);
					} else {
						buffer.append(SEP);		
					}
				}
				
				buffer.append("\n");
			}
			
			writer.write(buffer.toString());
			writer.flush();
		} catch (Exception e) {
			log.error("Unable to flush entries", e);
			throw new JournalException(e);
		} finally {
			if (writer != null) {
				try {
					writer.flush();
					writer.close();
				} catch (Exception e) {
					log.error("Unable to close journal file handle", e);
				}
			}
			entries.clear();
		}
	}
}
