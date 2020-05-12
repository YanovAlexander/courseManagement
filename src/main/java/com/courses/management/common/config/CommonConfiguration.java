package com.courses.management.common.config;

import com.courses.management.common.AWSService;
import com.courses.management.common.CommonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class CommonConfiguration {

    @Bean
    @Profile("aws")
    public CommonService commonService() {
        return new AWSService();
    }
}
