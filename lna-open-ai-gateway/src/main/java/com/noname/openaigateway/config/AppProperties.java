package com.noname.openaigateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "luna.open-ai")
public class AppProperties {
    private String apiKey;
    private String completionsUrl;
    private String imagesUrl;
    private String embeddingsUrl;
}
