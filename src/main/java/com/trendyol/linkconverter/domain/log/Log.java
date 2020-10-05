package com.trendyol.linkconverter.domain.log;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class Log {

    private String id;
    private String url;
    private String deeplink;
    private ConvertType convertType;
    private Long creationDate;

    public Log(String url, String deeplink, ConvertType convertType) {
        this.id = UUID.randomUUID().toString();
        this.url = url;
        this.deeplink = deeplink;
        this.convertType = convertType;
        this.creationDate = Instant.now().toEpochMilli();
    }
}
