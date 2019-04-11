/**
 * 
 */
package org.crossroad.util.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author e.soden
 *
 */
public class FileFinder extends SimpleFileVisitor<Path> {
	private final Logger log = LogManager.getLogger(this.getClass().getName());
	private final PathMatcher matcher;
	private int numMatches = 0;
	private List<IFileFinderListener> listeners = new ArrayList<IFileFinderListener>();
	
	/**
	 * 
	 */
	public FileFinder(String pattern) {
		matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
	}

	public void addListener(IFileFinderListener action)
	{
		this.listeners.add(action);
	}
	
	public void removeListener(IFileFinderListener action)
	{
		this.listeners.remove(action);
	}
	// Compares the glob pattern against
	// the file or directory name.
	void find(Path file) {
		Path name = file.getFileName();
		
		if (name != null && matcher.matches(name)) {
			for(IFileFinderListener listener:listeners)
			{
				listener.onFindFile(file);
			}
			numMatches++;
		}
	}

	// Prints the total number of
	// matches to standard out.
	void done() {
		for (IFileFinderListener listener:listeners)
		{
			listener.onFindFinish();
		}
	}

	// Invoke the pattern matching
	// method on each file.
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		find(file);
		return FileVisitResult.CONTINUE;
	}

	// Invoke the pattern matching
	// method on each directory.
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
		find(dir);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		for (IFileFinderListener listener:listeners)
		{
			listener.onFindError(file, exc);
		}
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return super.postVisitDirectory(dir, exc);
	}
	
	public int match()
	{
		return this.numMatches;
	}

}
