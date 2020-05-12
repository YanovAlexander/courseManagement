package com.courses.management.common;

import com.amazonaws.services.s3.AmazonS3;

public interface CommonService {

    AmazonS3 getS3Client();
    String getS3BucketName();
}
