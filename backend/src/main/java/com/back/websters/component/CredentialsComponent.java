package com.back.websters.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "cloud.aws.credentials")
@Component
public class CredentialsComponent {

    private String accessKey;
    private String secretKey;
}
