# my-tomcat-valve

TomcatでダミーのヘッダーやPrincipalをリクエストに設定するValve

以下の手順で行う

1. ValveBase の実装クラスを作成
1. ビルドし `$CATALINA_HOME/lib` に成果物jarを配置
1. `conf/server.xml` の `Valve` に追加

## `server.xml` の記入例

```
  <Host appBase="webapps" autoDeploy="true" name="localhost" unpackWARs="true">
    <Valve className="epsilongtmyon.dummy.DummyHeaderValve"/>
    <Valve className="epsilongtmyon.dummy.DummyPrincipalValve"/>
```
## DummyHeaderValve

`DummyHeaderValve` は `conf/dummyHeaders.properties` でヘッダーを指定できるようにしている(必須ではない)

記入例

```
aaa=001
bbb=002

```


## DummyPrincipalValve

`DummyPrincipalValve` は `conf/dummyPrincipal.properties` でユーザー、ロールを設定できるようにしている(必須ではない)

記入例

```
dummyUserName=00001
dummyRoles=aaa,bbb

```
