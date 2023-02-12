package epsilongtmyon.reverse;

import java.util.Objects;

import org.apache.catalina.CredentialHandler;

/**
 * パスワードを反転させるだけの{@link CredentialHandler}の実装です。
 * 
 * 学習目的に作成
 *
 */
public class ReverseTextCredentialHandler implements CredentialHandler {

	@Override
	public boolean matches(String inputCredentials, String storedCredentials) {

		final String reversedInputCredentials = new StringBuilder(inputCredentials)
				.reverse().toString();
		return Objects.equals(reversedInputCredentials, storedCredentials);
	}

	@Override
	public String mutate(String inputCredentials) {

		StringBuilder buf = new StringBuilder(inputCredentials);
		buf.reverse();
		return buf.toString();
	}

}
