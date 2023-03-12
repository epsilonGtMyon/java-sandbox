package epsilongtmyon.diff;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.Side;
import org.eclipse.jgit.lib.AbbreviatedObjectId;
import org.eclipse.jgit.lib.FileMode;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;

/**
 * 変更前後のファイルを出力します。
 */
public class DiffExporter {

	private final Repository repository;

	public DiffExporter(Repository repository) {
		this.repository = repository;
	}

	public void export(Path exportRoot, DiffEntry diff, Side side)
			throws IOException {

		final FileMode fileMode = diff.getMode(side);
		if (fileMode == FileMode.MISSING) {
			return;
		}

		final String path = diff.getPath(side);
		final AbbreviatedObjectId id = diff.getId(side);

		final Path outPath = exportRoot.resolve(path);
		
		// 親ディレクトリを作る
		final Path outDir = outPath.getParent();
		if (Files.notExists(outPath)) {
			Files.createDirectories(outDir);
		}

		try (OutputStream out = Files.newOutputStream(outPath)) {
			ObjectLoader newLoader = repository.open(id.toObjectId());
			newLoader.copyTo(out);
		}

	}

}
