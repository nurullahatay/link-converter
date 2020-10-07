package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.configuration.HostResource;
import com.trendyol.linkconverter.configuration.exception.ResolveUrlException;
import com.trendyol.linkconverter.controller.model.request.ConvertUrlToDeeplinkRequest;
import com.trendyol.linkconverter.controller.model.response.DeeplinkResponse;
import com.trendyol.linkconverter.domain.HomeLink;
import com.trendyol.linkconverter.domain.ProductLink;
import com.trendyol.linkconverter.domain.SearchLink;
import com.trendyol.linkconverter.domain.base.Link;
import com.trendyol.linkconverter.domain.log.ConvertType;
import com.trendyol.linkconverter.domain.log.Log;
import com.trendyol.linkconverter.repository.LinkLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlConvertService {
    private static final String PRODUCT_PARAM = "-p-";
    private static final String ALL_PRODUCTS_PARAM = "tum--urunler";

    private final LinkLogService linkLogService;
    private final HostResource hostResource;

    public DeeplinkResponse convertToDeeplink(ConvertUrlToDeeplinkRequest request) {
        Link link = resolveUrl(request.getUrl());
        DeeplinkResponse deeplinkResponse = new DeeplinkResponse(link.getDeeplink());

        Log log = new Log(request.getUrl(), deeplinkResponse.getDeeplink(), ConvertType.DEEPLINK);
        linkLogService.saveLog(log);
        return deeplinkResponse;
    }

    public Link resolveUrl(String url) {
        try {
            URL linkUrl = new URL(url);
            String host = linkUrl.getHost();
            String path = linkUrl.getPath();
            String query = linkUrl.getQuery();
            String deeplinkHost = hostResource.getDeeplink();

            if (path.contains(PRODUCT_PARAM)) {
                return new ProductLink(host, deeplinkHost, path, query);
            }

            if (path.contains(ALL_PRODUCTS_PARAM)) {
                return new SearchLink(host, deeplinkHost, query);
            }

            return new HomeLink(host, deeplinkHost);

        } catch (Exception ex) {
            log.error("Error occured when url resolving, url: {} , exception: {}", url, ex);
            throw new ResolveUrlException();
        }
    }
}
