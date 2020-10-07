package com.trendyol.linkconverter.domain;

import com.trendyol.linkconverter.domain.base.Link;
import com.trendyol.linkconverter.domain.enums.Page;
import com.trendyol.linkconverter.util.LinkConstants;
import com.trendyol.linkconverter.util.LinkUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class ProductLink extends Link {

    private final String contentId;
    private final String boutiqueId;
    private final String campaignId;
    private final String merchantId;

    public ProductLink(String urlHost, String deeplinkUrl, String path, String query) {
        super(urlHost, deeplinkUrl, Page.PRODUCT);

        Map<String, String> parameters = LinkUtils.resolveQueryParameters(query);
        this.boutiqueId = parameters.get("boutiqueid");
        this.merchantId = parameters.get("merchantid");
        this.campaignId = parameters.get("campaignid");

        if (StringUtils.contains(path, LinkConstants.PRODUCT_PARAM)) {
            this.contentId = StringUtils.substringAfterLast(path, LinkConstants.PRODUCT_PARAM);
        } else {
            this.contentId = parameters.get("contentid");
        }
    }

    @Override
    public String getUrl() {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(getUrlHost());
        urlBuilder.append(LinkUtils.PATH_OPERATOR).append("brand");
        urlBuilder.append(LinkUtils.PATH_OPERATOR).append("name").append(LinkConstants.PRODUCT_PARAM).append(contentId);

        if (StringUtils.isNotEmpty(campaignId) || StringUtils.isNotEmpty(merchantId)) {
            urlBuilder.append(LinkUtils.QUERY_OPERATOR);
        }

        if (StringUtils.isNotEmpty(campaignId)) {
            urlBuilder.append("boutiqueId").append(LinkUtils.EQUALS_OPERATOR).append(campaignId);
        }

        if (StringUtils.isNotEmpty(merchantId)) {
            if (StringUtils.isNotEmpty(campaignId)) {
                urlBuilder.append(LinkUtils.SEPARATE_OPERATOR);
            }
            urlBuilder.append("merchantId").append(LinkUtils.EQUALS_OPERATOR).append(merchantId);
        }
        return urlBuilder.toString();
    }

    @Override
    public String getDeeplink() {
        StringBuilder deeplinkBuilder = new StringBuilder();
        deeplinkBuilder.append(getDeeplinkHost()).append(LinkUtils.QUERY_OPERATOR);
        deeplinkBuilder.append("Page").append(LinkUtils.EQUALS_OPERATOR).append(getPage().getValue());
        deeplinkBuilder.append(LinkUtils.SEPARATE_OPERATOR);
        deeplinkBuilder.append("ContentId").append(LinkUtils.EQUALS_OPERATOR).append(contentId);

        if (StringUtils.isNotEmpty(boutiqueId)) {
            deeplinkBuilder.append(LinkUtils.SEPARATE_OPERATOR).append("CampaignId").append(LinkUtils.EQUALS_OPERATOR).append(boutiqueId);
        }

        if (StringUtils.isNotEmpty(merchantId)) {
            deeplinkBuilder.append(LinkUtils.SEPARATE_OPERATOR).append("MerchantId").append(LinkUtils.EQUALS_OPERATOR).append(merchantId);
        }
        return deeplinkBuilder.toString();
    }
}
