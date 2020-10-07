package com.trendyol.linkconverter.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertDeeplinkToUrlRequest {

    @NotEmpty(message = "validation.deeplink.empty")
    @Pattern(regexp = "^(ty?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "validation.deeplink.invalid")
    private String deeplink;
}
