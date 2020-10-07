package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.controller.model.request.ConvertUrlToDeeplinkRequest;
import com.trendyol.linkconverter.controller.model.response.DeeplinkResponse;
import com.trendyol.linkconverter.service.UrlConvertService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UrlConvertController {

    private final UrlConvertService urlConvertService;

    @PostMapping(value = "/v1/url/convert/deeplink")
    public DeeplinkResponse convertUrlToDeeplink(@Valid @RequestBody ConvertUrlToDeeplinkRequest request) {
        return urlConvertService.convertToDeeplink(request);
    }
}
