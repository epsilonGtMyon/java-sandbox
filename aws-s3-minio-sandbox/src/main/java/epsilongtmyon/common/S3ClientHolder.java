package epsilongtmyon.common;

import java.net.URI;
import java.net.URISyntaxException;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

public class S3ClientHolder implements AutoCloseable {

	private final S3Client s3Client;

	public S3ClientHolder(S3Client s3Client) {
		super();
		this.s3Client = s3Client;
	}

	public S3Client getS3Client() {
		return s3Client;
	}

	// ---------------------------
	public static S3ClientHolder create() throws URISyntaxException {

		URI endpoint = new URI("http://localhost:9000");
		S3Client s3Client = null;

		s3Client = S3Client.builder()
				.region(Region.AP_NORTHEAST_1)
				.endpointOverride(endpoint)

				// localhostでminio使う時にいるらしい(要調査)
			    .serviceConfiguration(S3Configuration.builder()
			        .pathStyleAccessEnabled(true)
			        .build())
			    
				.build();

		return new S3ClientHolder(s3Client);
	}

	@Override
	public void close() throws Exception {
		s3Client.close();
	}

}
