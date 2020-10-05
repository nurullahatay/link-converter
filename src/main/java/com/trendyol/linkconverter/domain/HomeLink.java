package com.trendyol.linkconverter.domain;

import com.trendyol.linkconverter.domain.base.Link;
import com.trendyol.linkconverter.domain.enums.Page;

public class HomeLink extends Link {

    public HomeLink(String urlHost, String deeplinkUrl, String query) {
        super(urlHost, deeplinkUrl, Page.HOME);
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getDeeplink() {
        StringBuilder deeplinkBuilder = new StringBuilder();
        deeplinkBuilder.append(getDeeplinkHost()).append("://");
        deeplinkBuilder.append("?Page=").append(getPage().getValue());
        return deeplinkBuilder.toString();
    }
}
