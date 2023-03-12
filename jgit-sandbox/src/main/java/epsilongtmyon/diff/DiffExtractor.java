package epsilongtmyon.diff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.util.io.DisabledOutputStream;

/**
 * 差分抽出をするクラス
 */
public class DiffExtractor {

	private final Git git;
	private final DiffFormatterProvider formatterProvider;

	public DiffExtractor(Git git, DiffFormatterProvider formatterProvider) {
		this.git = git;
		this.formatterProvider = formatterProvider;
	}

	public ExtractedCommitLog extract(String commitHash) throws IOException, GitAPIException {
		Repository repository = git.getRepository();

		ObjectId obj1 = repository.resolve(commitHash);
		// 該当コミットのログを取り出す
		List<RevCommit> commits = toList(git.log().add(obj1).setMaxCount(1).call());
		if (commits.size() != 1) {
			throw new RuntimeException("commit not found " + commitHash);
		}

		final RevCommit commit = commits.get(0);
		final RevTree tree = commit.getTree();

		// マージしたときとかはparentが2つになる
		// 普通にマージしたときは片方が0だけど2つになるときもある。
		final RevCommit[] parentCommits = commit.getParents();

		final List<ExtractedCommitDiff> parentCommitDiffs = new ArrayList<>();

		for (RevCommit parentCommit : parentCommits) {
			final RevTree parentTree = parentCommit.getTree();

			// 該当コミットとその親のコミットを比較して差分を取得
			final List<DiffEntry> diffEntries;
			try (DiffFormatter diffFormatter = formatterProvider.createDiffFormatter(DisabledOutputStream.INSTANCE)) {
				diffEntries = diffFormatter.scan(parentTree, tree);
			}

			ExtractedCommitDiff d = new ExtractedCommitDiff(parentCommit, diffEntries);
			parentCommitDiffs.add(d);
		}

		ExtractedCommitLog commitLog = new ExtractedCommitLog(commit, parentCommitDiffs);
		return commitLog;
	}

	private static <T> List<T> toList(Iterable<T> iterable) {
		if (iterable instanceof List) {
			return (List<T>) iterable;
		}
		List<T> list = new ArrayList<>();
		iterable.forEach(list::add);
		return list;
	}

	/**
	 * コミットログ 
	 */
	public static class ExtractedCommitLog {

		/** 該当のコミット */
		private final RevCommit commit;

		/** 該当のコミットの親のログ */
		private final List<ExtractedCommitDiff> parentCommitDiffs;

		public ExtractedCommitLog(RevCommit commit, List<ExtractedCommitDiff> parentCommitDiffs) {
			this.commit = commit;
			this.parentCommitDiffs = parentCommitDiffs;
		}

		public RevCommit getCommit() {
			return commit;
		}

		public List<ExtractedCommitDiff> getParentCommitDiffs() {
			return parentCommitDiffs;
		}

	}

	/**
	 * 親のコミットログ
	 */
	public static class ExtractedCommitDiff {

		/** 親のコミット */
		private final RevCommit commit;

		/** 親との差分 */
		private final List<DiffEntry> diffEntries;

		public ExtractedCommitDiff(RevCommit commit, List<DiffEntry> diffEntries) {
			this.commit = commit;
			this.diffEntries = diffEntries;
		}

		public RevCommit getCommit() {
			return commit;
		}

		public List<DiffEntry> getDiffEntries() {
			return diffEntries;
		}

	}

}
