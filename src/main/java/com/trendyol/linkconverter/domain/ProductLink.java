package com.trendyol.linkconverter.domain;

import com.trendyol.linkconverter.domain.base.Link;
import com.trendyol.linkconverter.domain.enums.Page;
import com.trendyol.linkconverter.util.LinkUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class ProductLink extends Link {

    private final String contentId;
    private final String boutiqueId;
    private final String campaignId;
    private final String merchantId;

    private static final String PRODUCT_PARAM = "-p-";

    public ProductLink(String urlHost, String deeplinkUrl, String path, String query) {
        super(urlHost, deeplinkUrl, Page.PRODUCT);

        Map<String, String> parameters = LinkUtils.getParameters(query);
        this.boutiqueId = parameters.get("boutiqueid");
        this.merchantId = parameters.get("merchantid");
        this.campaignId = parameters.get("campaignid");

        if (StringUtils.contains(path, PRODUCT_PARAM)) {
            this.contentId = StringUtils.substringAfterLast(path, PRODUCT_PARAM);
        } else {
            this.contentId = parameters.get("contentid");
        }
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
        deeplinkBuilder.append("&ContentId=").append(contentId);

        if (StringUtils.isNotEmpty(boutiqueId)) {
            deeplinkBuilder.append("&CampaignId=").append(boutiqueId);
        }

        if (StringUtils.isNotEmpty(merchantId)) {
            deeplinkBuilder.append("&MerchantId=").append(merchantId);
        }
        return deeplinkBuilder.toString();
    }
}
