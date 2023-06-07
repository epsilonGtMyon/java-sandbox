package epsilongtmyon.dummy;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.tomcat.util.buf.MessageBytes;
import org.apache.tomcat.util.http.MimeHeaders;

/**
 * ダミーのHTTPヘッダーを付与するValve
 *
 */
public class DummyHeaderValve extends ValveBase {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-mm-dd HH:mm:ss");

	@Override
	public void invoke(Request request, Response response) throws IOException, ServletException {
		org.apache.coyote.Request coyoteRequest = request.getCoyoteRequest();
		MimeHeaders mimeHeaders = coyoteRequest.getMimeHeaders();

		LocalDateTime now = LocalDateTime.now();

		String headerValue = "dummy-" + now.format(formatter);
		MessageBytes value = mimeHeaders.addValue("dummy-valve");
		value.setString(headerValue);

		getNext().invoke(request, response);
	}

}
