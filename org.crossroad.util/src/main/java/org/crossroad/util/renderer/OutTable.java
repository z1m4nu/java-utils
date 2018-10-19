/**
 * 
 */
package org.crossroad.util.renderer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author e.soden
 *
 */
public class OutTable {
	private List<OutTableColumn> columns = new ArrayList<OutTableColumn>();
	
	private List<String[]> rows = new ArrayList<String[]>();
	
	private String separator = null;
	private String banalyzer = null;
	private String name = null;
	
	/**
	 * @return the banalyzer
	 */
	public String getBanalyzer() {
		return banalyzer;
	}

	/**
	 * @param banalyzer the banalyzer to set
	 */
	public void setBanalyzer(String banalyzer) {
		this.banalyzer = banalyzer;
	}

	public void addColumn(OutTableColumn column)
	{
		this.columns.add(column);
	}
	
	public List<OutTableColumn> getColumns()
	{
		return this.columns;
	}
	
	/**
	 * @return the separator
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * @param separator the separator to set
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	

	public void setRows(List<String[]> rows) {
		this.rows = rows;
	}
	
	public List<String[]> getRows() {
		return rows;
	}
	
	public void addRow(String[] row)
	{
		this.rows.add(row);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
}
