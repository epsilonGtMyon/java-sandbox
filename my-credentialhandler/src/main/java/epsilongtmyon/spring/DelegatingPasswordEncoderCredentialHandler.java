package epsilongtmyon.spring;

import org.apache.catalina.CredentialHandler;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security の {@link PasswordEncoder} に委譲する {@link CredentialHandler}の実装
 * 
 */
public class DelegatingPasswordEncoderCredentialHandler implements CredentialHandler {

	private static final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Override
	public boolean matches(String inputCredentials, String storedCredentials) {
		return encoder.matches(inputCredentials, storedCredentials);
	}

	@Override
	public String mutate(String inputCredentials) {
		return encoder.encode(inputCredentials);
	}
}
