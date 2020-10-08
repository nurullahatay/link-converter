package com.trendyol.linkconverter.domain.link.base;


import com.trendyol.linkconverter.domain.link.enums.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Link {
    private String urlHost;
    private String deeplinkHost;
    private Page page;

    public abstract String getUrl();

    public abstract String getDeeplink();
}
