/**
 * 
 */
package org.crossroad.util.renderer;

import org.crossroad.util.AbstractLogger;

/**
 * @author e.soden
 *
 */
public abstract class AbstractRenderer extends AbstractLogger {
	
	

	/**
	 * 
	 */
	public AbstractRenderer() {
		
	}

	public abstract void preRendering() throws RenderException;

	public abstract void postRendering() throws RenderException;

	public abstract void doRendering() throws RenderException;

	public void render() throws RenderException {
		long start = System.currentTimeMillis();
		try {
			operations();
		} finally {
			log.info("Render in " + (System.currentTimeMillis() - start) + " ms.");
		}
	}
	
	protected void operations() throws RenderException
	{
		preRendering();

		doRendering();

		postRendering();
	}
	
	
	

}
