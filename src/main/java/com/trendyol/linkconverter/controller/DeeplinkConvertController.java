package com.trendyol.linkconverter.controller;


import com.trendyol.linkconverter.controller.model.request.ConvertDeeplinkToUrlRequest;
import com.trendyol.linkconverter.controller.model.response.UrlResponse;
import com.trendyol.linkconverter.service.DeeplinkConvertService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class DeeplinkConvertController {

    private final DeeplinkConvertService deeplinkConvertService;

    @PostMapping(value = "/v1/deeplink/convert/url")
    public UrlResponse convertDeeplinkToUrl(@Valid @RequestBody ConvertDeeplinkToUrlRequest request) {
        return deeplinkConvertService.convertToUrl(request);
    }
}