package epsilongtmyon.app.pages.login.spec;

import javax.servlet.ServletRequest;

public class LoginRequest {

	private String userId;

	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	// ------------------------

	@Override
	public String toString() {
		return "LoginRequest [userId=" + userId + ", password=" + password + "]";
	}

	public static LoginRequest fromServletRequest(ServletRequest request) {

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		LoginRequest req = new LoginRequest();
		req.setUserId(userId);
		req.setPassword(password);

		return req;
	}
}
