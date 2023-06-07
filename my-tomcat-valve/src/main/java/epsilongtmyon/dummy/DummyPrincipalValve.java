package epsilongtmyon.dummy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.valves.ValveBase;

/**
 * DummyのPrincipalをセットするValve
 */
public class DummyPrincipalValve extends ValveBase {

	private String dummyUserName = "dummyUser";
	private List<String> dummyRoles = Arrays.asList("dummyRole");

	@Override
	protected void initInternal() throws LifecycleException {
		super.initInternal();
		loadDummyConfing();
	}

	private void loadDummyConfing() {
		Path dummyConfPath = new File(container.getCatalinaHome(), "conf/dummy.properties").toPath();
        containerLog.info("dummyConfPath:" + dummyConfPath.toAbsolutePath().toString());
		if (Files.notExists(dummyConfPath)) {
	        containerLog.info("dummyConfPath: not exists");
			return;
		}

		try (BufferedReader br = Files.newBufferedReader(dummyConfPath)) {
			Properties p = new Properties();
			p.load(br);

			String user = p.getProperty("dummyUserName");
			String roles = p.getProperty("dummyRoles");
			if (user != null) {
				dummyUserName = user.trim();
			}

			if (roles != null) {
				dummyRoles = Arrays.stream(roles.split(","))
						.map(String::trim)
						.collect(Collectors.toList());
			}
			

	        containerLog.info("dummyUserName:" + dummyUserName);
	        containerLog.info("dummyRoles:" + dummyRoles);

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}

	@Override
	public void invoke(Request request, Response response) throws IOException, ServletException {
		putDummyPrincipal(request);
		getNext().invoke(request, response);
	}

	private void putDummyPrincipal(Request request) {
		if (request.getPrincipal() != null) {
			// 既に埋め込まれているので何もしない
			return;
		}

		GenericPrincipal principal = new GenericPrincipal(dummyUserName, null, dummyRoles);
		request.setUserPrincipal(principal);

	}

}
