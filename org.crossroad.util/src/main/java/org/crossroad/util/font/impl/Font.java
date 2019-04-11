package org.crossroad.util.font.impl;

import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;
import org.crossroad.util.font.IFont;

public class Font implements IFont {
	private Path path = null;
	private String alias = null;
	private String name = null;

	public Font(Path path) {
		this.path = path;
		this.name = FilenameUtils.getBaseName(path.toString());
		this.alias = this.name;
	}
	
	public Font(Path path, String alias) {
		this.path = path;
		this.alias = alias;
	}

	@Override
	public Path getPath() {
		return path;
	}

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public String getName() {
		return name;
	}
}
