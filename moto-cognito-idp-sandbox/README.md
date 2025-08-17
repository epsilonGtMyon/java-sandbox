# moto-cognito-idp-sandbox

[Moto](https://docs.getmoto.org/en/latest/index.html) を使ってCognito部分をモック化し、AWS のユーザープール周りをいろいろ試す。


## gradleまわり

dependencies に追加

```
dependencies {
	// aws
	implementation platform("software.amazon.awssdk:bom:2.32.22")
	implementation "software.amazon.awssdk:cognitoidentityprovider"
}

```

## docker まわり


[motoserver/moto](https://hub.docker.com/r/motoserver/moto) を使う


[対応状況](https://docs.getmoto.org/en/latest/docs/services/cognito-idp.html)

