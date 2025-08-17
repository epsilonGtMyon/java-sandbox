package epsilongtmyon.sandbox01;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminSetUserPasswordRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminSetUserPasswordResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthFlowType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CreateUserPoolClientRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CreateUserPoolClientResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CreateUserPoolRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CreateUserPoolResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.DeliveryMediumType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.DescribeUserPoolClientRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.DescribeUserPoolClientResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ExplicitAuthFlowsType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUserPoolClientsRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUserPoolClientsResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUserPoolsRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUserPoolsResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.MessageActionType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.NotAuthorizedException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserNotFoundException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserPoolClientDescription;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserPoolClientType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserPoolDescriptionType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserPoolType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UsernameAttributeType;

public class Sandbox01Main {

	private final CognitoIdentityProviderClient cognitoClient;

	private final String userPoolId;

	private final String clientId;

	private final String clientSecret;

	public Sandbox01Main(
			CognitoIdentityProviderClient cognitoClient,
			String userPoolId,
			String clientId,
			String clientSecret) {
		super();
		this.cognitoClient = cognitoClient;
		this.userPoolId = userPoolId;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public static void main(String[] args) throws URISyntaxException {
		// https://qiita.com/kawachi/items/cb59a44b5430988741be

		// AWSの設定情報をシステムプロパティから取得するように
		// SystemPropertyCredentialsProvider 
		System.setProperty(SdkSystemSetting.AWS_ACCESS_KEY_ID.property(), "dummy");
		System.setProperty(SdkSystemSetting.AWS_SECRET_ACCESS_KEY.property(), "dummy");

		try (CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
				.region(Region.AP_NORTHEAST_1)
				.endpointOverride(new URI("http://localhost:3000"))
				.build();) {

			Sandbox01Setting setting = Sandbox01Setting.load();

			var main = new Sandbox01Main(
					cognitoClient,

					setting.getUserPoolId(),
					setting.getClientId(),
					setting.getClientSecret());

			main.doDescribeUserPoolClient();
		}
	}

	// CreateUserPool
	// ユーザープールの作成
	private void doCreateUserPool() {
		CreateUserPoolRequest request = CreateUserPoolRequest.builder()
				.poolName("pool-" + System.currentTimeMillis())
				.usernameAttributes(UsernameAttributeType.EMAIL)
				.build();

		CreateUserPoolResponse response = cognitoClient.createUserPool(request);
		System.out.println(response);

		UserPoolType userPool = response.userPool();
		System.out.println(userPool.id());
	}

	// ListUserPools
	// ユーザープールの一覧
	private void doListUserPools() {
		ListUserPoolsRequest request = ListUserPoolsRequest.builder()
				.maxResults(5)
				.build();

		ListUserPoolsResponse response = cognitoClient.listUserPools(request);
		List<UserPoolDescriptionType> userPools = response.userPools();

		for (UserPoolDescriptionType pool : userPools) {
			System.out.println(pool.name());
		}

	}

	// CreateUserPoolClient
	// ユーザープールのクライアントを作成
	private void doCreateUserPoolClient() {

		CreateUserPoolClientRequest request = CreateUserPoolClientRequest.builder()
				.userPoolId(userPoolId)
				.clientName("client-" + System.currentTimeMillis())

				.generateSecret(true) // サーバーサイドのアプリならtrue
				.explicitAuthFlows(ExplicitAuthFlowsType.ALLOW_ADMIN_USER_PASSWORD_AUTH)
				.build();

		CreateUserPoolClientResponse response = cognitoClient.createUserPoolClient(request);
		System.out.println(response);

		UserPoolClientType client = response.userPoolClient();
		System.out.println(client.clientId());
		System.out.println(client.clientSecret());
	}

	// ListUserPoolClients
	// ユーザープールクライアントの一覧を取得
	private void doListUserPoolClients() {
		ListUserPoolClientsRequest request = ListUserPoolClientsRequest.builder()
				.userPoolId(userPoolId)
				.maxResults(3)
				.build();

		ListUserPoolClientsResponse response = cognitoClient.listUserPoolClients(request);

		List<UserPoolClientDescription> userPoolClients = response.userPoolClients();
		for (UserPoolClientDescription clinet : userPoolClients) {
			System.out.println();
			System.out.println(clinet.clientId());
		}

	}

	// DescribeUserPoolClient
	// ユーザープールクライアントの詳細を取得
	private void doDescribeUserPoolClient() {
		DescribeUserPoolClientRequest request = DescribeUserPoolClientRequest.builder()
				.userPoolId(userPoolId)
				.clientId(clientId)

				.build();

		DescribeUserPoolClientResponse response = cognitoClient.describeUserPoolClient(request);
		System.out.println(response);

		UserPoolClientType clinet = response.userPoolClient();
		System.out.println(clinet.clientId());
		System.out.println(clinet.clientSecret());
	}

	// AdminCreateUser
	private void doAdminCreateUser() {
		AdminCreateUserRequest request = AdminCreateUserRequest.builder()
				.userPoolId(userPoolId)
				.username("test@example.com")
				// このあたりよくわからん..
				.messageAction(MessageActionType.SUPPRESS)
				.desiredDeliveryMediums(DeliveryMediumType.EMAIL)
				.userAttributes(
						AttributeType.builder().name("email").value("test@example.com").build()

				)
				.build();

		AdminCreateUserResponse response = cognitoClient.adminCreateUser(request);

		System.out.println(response);
		System.out.println(response.user());
	}

	// AdminSetUserPassword
	private void doAdminSetUserPassword() {
		AdminSetUserPasswordRequest request = AdminSetUserPasswordRequest.builder()
				.userPoolId(userPoolId)
				.username("test@example.com")
				.password("Test_1234")
				.permanent(true)
				.build();

		AdminSetUserPasswordResponse response = cognitoClient.adminSetUserPassword(request);

		System.out.println(response);
	}

	// AdminInitiateAuth
	// 認証
	private void doAdminInitiateAuth() {
		//https://docs.aws.amazon.com/ja_jp/sdk-for-java/latest/developer-guide/java_cognito-identity-provider_code_examples.html
		// https://docs.aws.amazon.com/cognito/latest/developerguide/signing-up-users-in-your-app.html#cognito-user-pools-computing-secret-hash
		Map<String, String> authParameters = new HashMap<>();
		authParameters.put("USERNAME", "user01@example.com");
		authParameters.put("PASSWORD", "User01_123");
		authParameters.put("SECRET_HASH", clientSecret); // secretをtrueにしていたら。

		AdminInitiateAuthRequest request = AdminInitiateAuthRequest.builder()
				.clientId(clientId)
				.userPoolId(userPoolId)
				.authParameters(authParameters)
				.authFlow(AuthFlowType.ADMIN_USER_PASSWORD_AUTH)
				.build();

		try {
			AdminInitiateAuthResponse response = cognitoClient.adminInitiateAuth(request);
		} catch (UserNotFoundException e) {
			System.out.println("ユーザーなし");
			throw e;
		} catch (NotAuthorizedException e) {
			System.out.println("パスワードなし");
			throw e;
		}

		// AdminRespondToAuthChallengeで多要素認証ができるらしい
	}

	// AdminRespondToAuthChallenge
}
