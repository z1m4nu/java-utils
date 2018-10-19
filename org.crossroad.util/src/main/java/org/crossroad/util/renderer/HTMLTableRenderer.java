/**
 * 
 */
package org.crossroad.util.renderer;

/**
 * @author e.soden
 *
 */
public class HTMLTableRenderer extends StringTableRenderer {

	/**
	 * 
	 */
	public HTMLTableRenderer() {
		
	}
	
	

	@Override
	public void doRendering() throws RenderException {
		try {
			this.buffer = new StringBuffer();
			buffer.append("<table><tr>");

			for (OutTableColumn column : table.getColumns()) {
				buffer.append("<th>").append(column.getTitle()).append("</th>");

			}
			buffer.append("</tr>");

			for (String[] row : table.getRows()) {
				buffer.append("<tr>");
				for (String col : row) {
					buffer.append("<td>").append(col.replace(table.getSeparator(), table.getBanalyzer()))
							.append("</td>");
				}
				buffer.append("</tr>");
			}
			buffer.append("</table>");
		} catch (Exception e) {
			throw new RenderException(e);
		}
	}
}
