package com.trendyol.linkconverter.domain.link;

import com.trendyol.linkconverter.domain.link.base.Link;
import com.trendyol.linkconverter.domain.link.enums.Page;
import com.trendyol.linkconverter.util.LinkUtils;

public class HomeLink extends Link {

    public HomeLink(String urlHost, String deeplinkUrl) {
        super(urlHost, deeplinkUrl, Page.HOME);
    }

    @Override
    public String getUrl() {
        return getUrlHost();
    }

    @Override
    public String getDeeplink() {
        StringBuilder deeplinkBuilder = new StringBuilder();
        deeplinkBuilder.append(getDeeplinkHost()).append(LinkUtils.QUERY_OPERATOR);
        deeplinkBuilder.append("Page").append(LinkUtils.EQUALS_OPERATOR).append(getPage().getValue());
        return deeplinkBuilder.toString();
    }
}
