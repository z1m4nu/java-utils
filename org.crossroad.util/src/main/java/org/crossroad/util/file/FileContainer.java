package org.crossroad.util.file;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileContainer {
	private Path path = null;
	private String permission = null;

	public FileContainer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the path
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(Path path) {
		this.path = path;
	}
	
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = Paths.get(path);
	}

	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Path getSubPath(String subPath)
	{
		return this.path.resolve(subPath);
	}
	
}
