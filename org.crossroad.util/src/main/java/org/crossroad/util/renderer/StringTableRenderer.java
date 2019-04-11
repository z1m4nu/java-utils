/**
 * 
 */
package org.crossroad.util.renderer;

/**
 * @author e.soden
 *
 */
public class StringTableRenderer extends AbstractTableRenderer {

	protected StringBuffer buffer = null;

	public StringTableRenderer() {
	}

	public String getRenderedString() {
		return buffer.toString();
	}

	@Override
	public void preRendering() throws RenderException {
		
	}

	@Override
	public void postRendering() throws RenderException {
	}

	@Override
	public void doRendering() throws RenderException {

		this.buffer = new StringBuffer();
		
		
		try {

			for (OutTableColumn column : table.getColumns()) {
				buffer.append(column.getTitle());
				buffer.append(table.getSeparator());
			}
			buffer.append("\n");
			for (String[] row : table.getRows()) {
				for (String col : row) {
					
					buffer.append(col.replace(table.getSeparator(), table.getBanalyzer()));
					buffer.append(table.getSeparator());

				}
				buffer.append("\n");
			}
		} catch (Exception e) {
			throw new RenderException(e);
		}
	}


}
