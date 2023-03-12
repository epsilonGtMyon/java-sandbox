package epsilongtmyon.diff;

import java.io.OutputStream;

import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.Repository;


/**
 * {@link DiffFormatter}のプロバイダー
 */
public class DiffFormatterProvider {

	private final Repository repository;

	public DiffFormatterProvider(Repository repository) {
		this.repository = repository;
	}

	public DiffFormatter createDiffFormatter(OutputStream out) {
		DiffFormatter formatter = new DiffFormatter(out);
		formatter.setRepository(repository);
		formatter.setDetectRenames(true);

		return formatter;
	}
}
