package epsilongtmyon.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkPath {

	public static Path get(Class<?> clazz) {
		final String className = clazz.getName();
		final String[] classNameElements = className.split("\\.");
		final Path root = Paths.get("build", "work");
		
		Path p = root;
		for (String s : classNameElements) {
			p = p.resolve(s);
		}

		return p;
	}
}
