/**
 * 
 */
package org.crossroad.util.renderer;

/**
 * @author e.soden
 *
 */
public abstract class AbstractTableRenderer extends AbstractRenderer {
	protected OutTable table = null;
	/**
	 * @param table
	 */
	public AbstractTableRenderer() {
	}

	public void setTable(OutTable table) {
		this.table = table;
	}
}
