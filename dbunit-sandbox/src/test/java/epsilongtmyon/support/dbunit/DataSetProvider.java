package epsilongtmyon.support.dbunit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.dataset.excel.XlsDataSet;

import epsilongtmyon.support.util.ResourceUtil;

public class DataSetProvider {

	private final String pathPrefix;

	private DataSetProvider(String pathPrefix) {
		this.pathPrefix = pathPrefix;
	}

	public static DataSetProvider get(Class<?> clazz) {
		String prefix = clazz.getName().replaceAll("\\.", "/");
		return get(prefix);
	}

	public static DataSetProvider get(String prefix) {
		String pathPrefix = "META-INF/" + prefix + "/";
		return new DataSetProvider(pathPrefix);
	}

	// -----------------------------------

	public IDataSet loadXls(String resourceName) throws DataSetException, IOException {
		String resourcePath = pathPrefix + resourceName;

		try (InputStream in = ResourceUtil.getResourceAsStream(resourcePath)) {
			if (in == null) {
				throw new IOException("resourcePath %s not found.".formatted(resourcePath));
			}
			return wrapReplacement(new XlsDataSet(in));
		}
	}

	public IDataSet loadCsv(String dirName) throws DataSetException, IOException {
		String dirPath = pathPrefix + dirName;
		Path csvPath = Path.of("src", "test", "resources").resolve(dirPath).toAbsolutePath();
		if (Files.notExists(csvPath)) {
			throw new FileNotFoundException("csvPath %s not found.".formatted(csvPath.toString()));
		}

		// CSVの場合はディレクトリを指定する。
		return wrapReplacement(new CsvDataSet(csvPath.toFile()));
	}

	private IDataSet wrapReplacement(IDataSet dataSet) {
		// 置換元と置換先を指定
		// nullの指定方法だったり、空文字列の変換などをするといいのかな？
		ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);
		replacementDataSet.addReplacementObject("", null);
		replacementDataSet.addReplacementObject("<NULL>", null);
		replacementDataSet.addReplacementObject("<null>", null);
		replacementDataSet.addReplacementObject("<empty>", "");
		return replacementDataSet;
	}
}
