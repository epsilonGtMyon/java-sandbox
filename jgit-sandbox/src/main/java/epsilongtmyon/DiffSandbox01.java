package epsilongtmyon;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.Side;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.FileMode;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.revwalk.RevCommit;

import epsilongtmyon.diff.DiffExporter;
import epsilongtmyon.diff.DiffExtractor;
import epsilongtmyon.diff.DiffExtractor.ExtractedCommitDiff;
import epsilongtmyon.diff.DiffExtractor.ExtractedCommitLog;
import epsilongtmyon.diff.DiffFormatterProvider;

public class DiffSandbox01 {

	public static void main(String[] args) throws IOException, GitAPIException {

		Path repositoryPath = Path.of(args[0]);
		String commitHash = args[1];

		Repository repo = new RepositoryBuilder()
				.setGitDir(repositoryPath.toFile())
				.build();

		try (Git git = new Git(repo)) {
			DiffFormatterProvider formatterProvider = new DiffFormatterProvider(repo);

			DiffExtractor extractor = new DiffExtractor(git, formatterProvider);
			ExtractedCommitLog commitLog = extractor.extract(commitHash);

			RevCommit commit = commitLog.getCommit();
			List<ExtractedCommitDiff> parentCommitDiffs = commitLog.getParentCommitDiffs();

			System.out.println("========================");
			System.out.println(commit.getShortMessage());

			for (ExtractedCommitDiff parentCommitDiff : parentCommitDiffs) {
				RevCommit parentCommit = parentCommitDiff.getCommit();
				List<DiffEntry> diffEntries = parentCommitDiff.getDiffEntries();
				if (diffEntries.isEmpty()) {
					continue;
				}

				System.out.println("------------");
				System.out.println(parentCommit.getShortMessage());

				Path beforeRoot = Path.of("diffs", "beforeRoot_" + ObjectId.toString(parentCommit));
				Path afterRoot = Path.of("diffs", "afterRoot");

				DiffExporter diffExporter = new DiffExporter(repo);

				try (OutputStream out = Files.newOutputStream(Path.of("diffs", "diffs.txt"));
						DiffFormatter formatter = formatterProvider.createDiffFormatter(out);) {

					for (DiffEntry diff : diffEntries) {
						//					System.out.println("----");
						//					ChangeType changeType = diff.getChangeType();
						//					System.out.println(changeType);
						//					System.out.println(diff.getNewPath());
						//					//					System.out.println(resolveFileModeObjectType(de.getNewMode()));
						//					System.out.println(diff.getOldPath());
						//					//					System.out.println(resolveFileModeObjectType(de.getOldMode()));

						// 差分の比較ファイルを出力
						formatter.format(diff);

						// 前後のファイルそのものを出力
						diffExporter.export(beforeRoot, diff, Side.OLD);
						diffExporter.export(afterRoot, diff, Side.NEW);
					}
				}
			}

		}

	}

	private static String resolveFileModeObjectType(FileMode fileMode) {

		if (fileMode == FileMode.TREE) {
			return "TREE";
		}
		if (fileMode == FileMode.SYMLINK) {
			return "SYMLINK";
		}
		if (fileMode == FileMode.REGULAR_FILE) {
			return "REGULAR_FILE";
		}
		if (fileMode == FileMode.EXECUTABLE_FILE) {
			return "EXECUTABLE_FILE";
		}
		if (fileMode == FileMode.GITLINK) {
			return "GITLINK";
		}
		if (fileMode == FileMode.MISSING) {
			return "MISSING";
		}
		return "";
	}

}
