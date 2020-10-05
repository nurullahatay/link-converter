package com.trendyol.linkconverter.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertUrlToDeeplinkRequest {

    private String url;
}
