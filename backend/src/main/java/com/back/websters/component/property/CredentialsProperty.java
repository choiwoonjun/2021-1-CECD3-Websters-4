package com.back.websters.component.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "cloud.aws.credentials")
@Component
public class CredentialsProperty {

    private String accessKey;
    private String secretKey;
}
