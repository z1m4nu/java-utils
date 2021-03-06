/**
* 
*/
package org.crossroad.util.renderer;

import java.time.Duration;
import java.time.Instant;

import org.crossroad.util.time.TimeMonitorController;

/**
 * @author e.soden
 *
 */
public class JSonTableRenderer extends StringTableRenderer {

	/**
	 * @param table
	 */
	public JSonTableRenderer() {
		super();
	}

	@Override
	public void preRendering() throws RenderException {
		// TODO Auto-generated method stub

	}

	@Override
	public void postRendering() throws RenderException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doRendering() throws RenderException {
		final Instant start = Instant.now();

		this.buffer = null;
		this.buffer = new StringBuffer();
		try {

			buffer.append("{\"").append(table.getName()).append("\":[");

			boolean first = true;
			for (String[] row : table.getRows()) {

				buffer.append("{");
				if (!first) {
					buffer.append(",");
				} else {
					first = false;
				}

				for (int index = 0; index < row.length; index++) {
					OutTableColumn column = table.getColumns().get(index);
					String col = row[index];

					if (index > 0)
						buffer.append(",");

					buffer.append("\"").append(column.getReference()).append("\":").append("\"").append(col).append("\"");

				}

				buffer.append("}");

			}
			
			buffer.append("]}");
		} catch (Exception e) {
			throw new RenderException(e);
		} finally {
			TimeMonitorController.getInstance().addStepTime("JSONRENDER",Duration.between(start, Instant.now()).toMillis());
		}

	}

}
