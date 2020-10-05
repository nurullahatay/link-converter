package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.controller.model.request.ConvertUrlToDeeplinkRequest;
import com.trendyol.linkconverter.controller.model.response.DeeplinkResponse;
import com.trendyol.linkconverter.domain.HomeLink;
import com.trendyol.linkconverter.domain.ProductLink;
import com.trendyol.linkconverter.domain.SearchLink;
import com.trendyol.linkconverter.domain.base.Link;
import com.trendyol.linkconverter.domain.log.ConvertType;
import com.trendyol.linkconverter.domain.log.Log;
import com.trendyol.linkconverter.repository.LogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@AllArgsConstructor
public class UrlConvertService {
    private static final String DEEPLINK_HOST = "ty";
    private static final String PRODUCT_PARAM = "-p-";
    private static final String ALL_PRODUCTS_PARAM = "tum--urunler";

    private final LogService logService;

    public DeeplinkResponse convertToDeeplink(ConvertUrlToDeeplinkRequest request) {
        Link link = resolveUrl(request.getUrl());
        DeeplinkResponse deeplinkResponse = DeeplinkResponse.from(link);

        Log log = new Log(request.getUrl(), deeplinkResponse.getDeeplink(), ConvertType.DEEPLINK);
        logService.saveLog(log);
        return deeplinkResponse;
    }

    public Link resolveUrl(String url) {
        try {
            URL linkUrl = new URL(url);
            String host = linkUrl.getHost();
            String path = linkUrl.getPath();
            String query = linkUrl.getQuery();

            if (path.contains(PRODUCT_PARAM)) {
                return new ProductLink(host, DEEPLINK_HOST, path, query);
            }

            if (path.contains(ALL_PRODUCTS_PARAM)) {
                return new SearchLink(host, DEEPLINK_HOST, query);
            }

            return new HomeLink(host, DEEPLINK_HOST, query);

        } catch (Exception ex) {
            //todo log and define dedicated exception
            throw new RuntimeException(ex);
        }
    }
}
