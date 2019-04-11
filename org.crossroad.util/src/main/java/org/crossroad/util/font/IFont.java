/**
 * 
 */
package org.crossroad.util.font;

import java.nio.file.Path;

/**
 * @author e.soden
 *
 */
public interface IFont {
	public static final String BOLD = "bold";
	public static final String REGULAR = "regular";
	
	Path getPath();

	String getAlias();

	String getName();
}
