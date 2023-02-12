# my-credentialhandler

認証で使用する TomcatのRealmでパスワードのハッシュ化に使用する [CredentialHandler](https://tomcat.apache.org/tomcat-9.0-doc/config/credentialhandler.html) を試す。


以下の手順で行う

1. CredentialHandler の実装クラスを作成
1. ビルドし `$CATALINA_HOME/lib` に成果物jarを配置
1. `conf/server.xml` の `Realm` に追加

## `server.xml` の記入例

```
<Realm className="org.apache.catalina.realm.UserDatabaseRealm" resourceName="UserDatabase">
  <CredentialHandler className="epsilongtmyon.reverse.ReverseTextCredentialHandler"/>
</Realm>
```
