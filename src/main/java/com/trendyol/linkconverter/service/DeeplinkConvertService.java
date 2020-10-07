package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.configuration.HostResource;
import com.trendyol.linkconverter.controller.model.request.ConvertDeeplinkToUrlRequest;
import com.trendyol.linkconverter.controller.model.response.UrlResponse;
import com.trendyol.linkconverter.domain.HomeLink;
import com.trendyol.linkconverter.domain.ProductLink;
import com.trendyol.linkconverter.domain.SearchLink;
import com.trendyol.linkconverter.domain.base.Link;
import com.trendyol.linkconverter.domain.enums.Page;
import com.trendyol.linkconverter.domain.log.ConvertType;
import com.trendyol.linkconverter.domain.log.Log;
import com.trendyol.linkconverter.repository.LinkLogService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeeplinkConvertService {

    private final LinkLogService linkLogService;
    private final HostResource hostResource;

    public UrlResponse convertToUrl(ConvertDeeplinkToUrlRequest request) {
        Link link = resolveDeeplink(request.getDeeplink());
        UrlResponse urlResponse = new UrlResponse(link.getUrl());

        Log log = new Log(urlResponse.getUrl(), request.getDeeplink(), ConvertType.URL);
        linkLogService.saveLog(log);
        return urlResponse;
    }

    public Link resolveDeeplink(String url) {

        String host = StringUtils.substringBefore(url, "?");
        String query = StringUtils.substringAfter(url, "?");
        String urlHost = hostResource.getUrl();

        if (query.contains(Page.PRODUCT.getValue())) {
            return new ProductLink(urlHost, host, "", query);
        }

        if (query.contains(Page.SEARCH.getValue())) {
            return new SearchLink(urlHost, host, query);
        }

        return new HomeLink(urlHost, host);
    }
}
