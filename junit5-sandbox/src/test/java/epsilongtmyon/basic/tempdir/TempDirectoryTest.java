package epsilongtmyon.basic.tempdir;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.io.TempDir;


// フィールドもしくはメソッドのパラメータにPath, Fileで宣言し
// TempDirアノテーションをつけることで、一時ディレクトリが作られる
// フォルダの作成方法のカスタマイズはfactoryで
// クリーンアップのルールはcleanupで指定できる。

public class TempDirectoryTest {

	@TempDir
	Path tempPath;

	@Test
	void test1(TestInfo tInfo, @TempDir Path tempPath1) {
		System.out.println(tInfo.getDisplayName());
		System.out.println(tempPath);
		System.out.println(tempPath1);

	}

	@Test
	void test2(TestInfo tInfo,@TempDir Path tempPath1) {
		System.out.println(tInfo.getDisplayName());
		System.out.println(tempPath);
		System.out.println(tempPath1);
	}
}
