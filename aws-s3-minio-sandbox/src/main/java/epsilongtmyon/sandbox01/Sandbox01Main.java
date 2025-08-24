package epsilongtmyon.sandbox01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import epsilongtmyon.common.S3ClientHolder;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.core.internal.util.Mimetype;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.ContentStreamProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.BucketVersioningStatus;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutBucketVersioningRequest;
import software.amazon.awssdk.services.s3.model.PutBucketVersioningResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

//https://docs.aws.amazon.com/ja_jp/sdk-for-java/latest/developer-guide/examples-s3.html

public class Sandbox01Main {

	private static final String BUCKET_NAME = "my-bucket";

	private final S3ClientHolder s3ClientHolder;

	public Sandbox01Main(S3ClientHolder s3ClientHolder) {
		super();
		this.s3ClientHolder = s3ClientHolder;
	}

	public static void main(String[] args) throws Exception {

		System.setProperty(SdkSystemSetting.AWS_ACCESS_KEY_ID.property(), "minioadmin");
		System.setProperty(SdkSystemSetting.AWS_SECRET_ACCESS_KEY.property(), "minioadmin");

		try (S3ClientHolder s3ClientHolder = S3ClientHolder.create()) {
			var main = new Sandbox01Main(s3ClientHolder);
			//			main.doCreateBucket();
			//			main.doPutBucketVersioningRequest();
			//			main.doPutObjectRequest();
			main.doGetObjectRequest();
		}

	}

	private void doCreateBucket() {
		S3Client s3Client = s3ClientHolder.getS3Client();

		CreateBucketRequest request = CreateBucketRequest.builder()
				.bucket(BUCKET_NAME)
				.build();

		CreateBucketResponse response = s3Client.createBucket(request);
		System.out.println(response);

	}

	private void doPutBucketVersioningRequest() {

		S3Client s3Client = s3ClientHolder.getS3Client();

		PutBucketVersioningRequest request = PutBucketVersioningRequest.builder()
				.bucket(BUCKET_NAME)
				.versioningConfiguration(c -> c.status(BucketVersioningStatus.ENABLED))
				.build();

		PutBucketVersioningResponse response = s3Client.putBucketVersioning(request);
		System.out.println(response);
	}

	//https://docs.aws.amazon.com/ja_jp/sdk-for-java/latest/developer-guide/best-practices-s3-uploads.html
	private void doPutObjectRequest() {

		S3Client s3Client = s3ClientHolder.getS3Client();

		// Pathを使うならそれ専用のものがあるが、ここではInputStreamを使うことをメインにしたい
		Path filePath = Path.of(".files", "image01.png").toAbsolutePath();
		String mimeType = Mimetype.getInstance().getMimetype(filePath);

		PutObjectRequest request = PutObjectRequest.builder()
				.bucket(BUCKET_NAME)
				.key(filePath.getFileName().toString())
				.build();

		try (InputStream is = Files.newInputStream(filePath)) {
			ContentStreamProvider provider = ContentStreamProvider.fromInputStream(is);
			RequestBody body = RequestBody.fromContentProvider(provider, mimeType);
			PutObjectResponse response = s3Client.putObject(request, body);
			System.out.println(response);

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		//TODO 大きなファイルの時はマルチパートを使うらしい
	}

	private void doGetObjectRequest() {

		S3Client s3Client = s3ClientHolder.getS3Client();
		String key = "aaa.txt";
		key = "image01.png";

		GetObjectRequest request = GetObjectRequest.builder()
				.bucket(BUCKET_NAME)
				.key(key)
				//.versionId("xxx")
				.build();

		try (ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request)) {

			Path dir = Path.of(".files", "download");
			Files.createDirectories(dir);
			Path savePath = dir.resolve(System.currentTimeMillis() + "-" + key);

			try (OutputStream os = Files.newOutputStream(savePath)) {
				response.transferTo(os);
			}

		} catch (Exception e) {
			// いろいろな例外出るけどとりあえずこれでまとめて
			throw new RuntimeException(e);
		}

	}
}
