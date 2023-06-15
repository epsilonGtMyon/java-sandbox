package epsilongtmyon.dummy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.tomcat.util.buf.MessageBytes;
import org.apache.tomcat.util.http.MimeHeaders;

/**
 * ダミーのHTTPリクエストヘッダーを付与するValve
 *
 */
public class DummyHeaderValve extends ValveBase {

	private Map<String, String> dummyHeaders = Collections.emptyMap();

	@Override
	protected void initInternal() throws LifecycleException {
		super.initInternal();
		loadDummyHeaderConfig();
	}

	private void loadDummyHeaderConfig() {
		Path dummyHeadersPath = new File(container.getCatalinaHome(), "conf/dummyHeaders.properties").toPath();
		containerLog.info("dummyHeadersPath:" + dummyHeadersPath.toAbsolutePath().toString());
		if (Files.notExists(dummyHeadersPath)) {
			containerLog.info("dummyHeaderPath: not exists");
			return;
		}

		try (BufferedReader br = Files.newBufferedReader(dummyHeadersPath)) {
			Properties p = new Properties();
			p.load(br);

			// unmodifiableMapで変更不可能に
			Map<String, String> m = new HashMap<>(p.size());
			p.forEach((k, v) -> {
				String key = (String) k;
				String value = (String) v;
				m.put(key.trim(), value.trim());
			});

			dummyHeaders = Collections.unmodifiableMap(m);

			dummyHeaders.forEach((k, v) -> {
				containerLog.info("dummyHeader:" + k + " = " + v);
			});

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}

	@Override
	public void invoke(Request request, Response response) throws IOException, ServletException {
		org.apache.coyote.Request coyoteRequest = request.getCoyoteRequest();
		MimeHeaders mimeHeaders = coyoteRequest.getMimeHeaders();

		dummyHeaders.forEach((k, v) -> {
			MessageBytes header = mimeHeaders.addValue(k);
			header.setString(v);
		});

		getNext().invoke(request, response);
	}

}
