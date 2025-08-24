# aws-s3-minio-sandbox

Minioを使ってaws sdkのs3を試す。

## gradleまわり

dependencies に追加

```
dependencies {
	// aws
	implementation platform("software.amazon.awssdk:bom:2.32.27")
	implementation "software.amazon.awssdk:s3"
}
```

## minioまわり

Dockerで[minio/minio](https://hub.docker.com/r/minio/minio)を使う


起動後 [http://localhost:9001/](http://localhost:9001/) にアクセスするとUI操作ができる。

諸々のユーザーやパスワードやキーは `minioadmin` になっている模様


## 参考

- [Amazon S3 で使用する](https://docs.aws.amazon.com/ja_jp/sdk-for-java/latest/developer-guide/examples-s3.html)
