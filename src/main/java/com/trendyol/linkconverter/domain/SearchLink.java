package com.trendyol.linkconverter.domain;

import com.trendyol.linkconverter.domain.base.Link;
import com.trendyol.linkconverter.domain.enums.Page;
import com.trendyol.linkconverter.util.LinkUtils;

import java.util.Map;

public class SearchLink extends Link {

    private final String query;

    public SearchLink(String urlHost, String deeplinkUrl, String query) {
        super(urlHost, deeplinkUrl, Page.SEARCH);

        Map<String, String> parameters = LinkUtils.getParameters(query);
        this.query = parameters.get("q");
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
        deeplinkBuilder.append("&Query=").append(query);
        return deeplinkBuilder.toString();
    }
}
