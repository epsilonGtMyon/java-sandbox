# authentication-sandbox

Servlet APIの認証試してみる

tomcat だとRealmの設定が必要で `server.xml` を確認するとデフォルトだと `org.apache.catalina.users.MemoryUserDatabaseFactory` が使われている

`conf/tomcat-users.xml` にユーザー情報を書けばいい
