package com.back.websters.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "bookmark.generator")
@Component
public class BoookmarkGeneratorPathComponent {
    private String path;
}
