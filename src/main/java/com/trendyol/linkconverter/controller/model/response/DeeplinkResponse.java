package com.trendyol.linkconverter.controller.model.response;

import com.trendyol.linkconverter.domain.base.Link;
import lombok.Data;

@Data
public class DeeplinkResponse {
    private String deeplink;

    private DeeplinkResponse() {
    }

    public DeeplinkResponse(String deeplink) {
        this.deeplink = deeplink;
    }

    public static DeeplinkResponse from(Link link) {
        return new DeeplinkResponse(link.getDeeplink());
    }
}
