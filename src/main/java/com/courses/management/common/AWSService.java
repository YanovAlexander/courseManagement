package com.courses.management.common;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Objects;

public class AWSService implements CommonService {
    private AmazonS3 client;
    private String accessKey;
    private String accessSecret;

    @PostConstruct
    private void postConstruct() {
        if (Objects.nonNull(client)) {
            return;
        }

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);

        client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }

    @Override
    public AmazonS3 getS3Client() {
        return client;
    }

    @Value("${aws.access.key}")
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Value("${aws.access.secret}")
    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }
}
