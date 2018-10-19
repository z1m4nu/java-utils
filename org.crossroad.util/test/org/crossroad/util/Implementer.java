package org.crossroad.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

public class Implementer {

	public Implementer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {

		Implementer i = new Implementer();
		i.implementInterface();
	}

	public void implementInterface() throws Exception {
		File jar = new File("D:\\sources\\000_GIT\\java-db-tools\\org.crossroad.db.tools\\release\\lib\\sqljdbc4.jar");

		URL[] urls = { jar.toURI().toURL() };
		URLClassLoader urlCl = new URLClassLoader(urls, System.class.getClassLoader());

		JarInputStream is = new JarInputStream(new FileInputStream(jar));

		JarEntry entry;
		while ((entry = is.getNextJarEntry()) != null) {
			if (entry.getName().endsWith(".class") && (entry.getName().indexOf('$')==-1)) {
				String name = entry.getName();
				
				int idx = name.lastIndexOf('.');
				name = name.substring(0, idx).replaceAll("/", ".");
				Class cl = urlCl.loadClass(name);
				if (Driver.class.isAssignableFrom(cl))
					System.out.println(cl.getName());		
			}
		}

	}
}
