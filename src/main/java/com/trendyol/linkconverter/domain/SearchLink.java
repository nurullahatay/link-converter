package com.trendyol.linkconverter.domain;

import com.trendyol.linkconverter.domain.base.Link;
import com.trendyol.linkconverter.domain.enums.Page;
import com.trendyol.linkconverter.util.LinkConstants;
import com.trendyol.linkconverter.util.LinkUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class SearchLink extends Link {

    private final String query;

    public SearchLink(String urlHost, String deeplinkUrl, String query) {
        super(urlHost, deeplinkUrl, Page.SEARCH);

        Map<String, String> parameters = LinkUtils.resolveQueryParameters(query);
        this.query = StringUtils.isNotEmpty(parameters.get("q")) ? parameters.get("q") : parameters.get("query");
    }

    @Override
    public String getUrl() {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(getUrlHost());
        urlBuilder.append(LinkUtils.PATH_OPERATOR).append(LinkConstants.SEARCH_PARAM).append(LinkUtils.QUERY_OPERATOR);
        urlBuilder.append("q").append(LinkUtils.EQUALS_OPERATOR).append(query);
        return urlBuilder.toString();
    }

    @Override
    public String getDeeplink() {
        StringBuilder deeplinkBuilder = new StringBuilder();
        deeplinkBuilder.append(getDeeplinkHost()).append(LinkUtils.QUERY_OPERATOR);
        deeplinkBuilder.append("Page").append(LinkUtils.EQUALS_OPERATOR).append(getPage().getValue());
        deeplinkBuilder.append(LinkUtils.SEPARATE_OPERATOR);
        deeplinkBuilder.append("Query").append(LinkUtils.EQUALS_OPERATOR).append(query);
        return deeplinkBuilder.toString();
    }
}
