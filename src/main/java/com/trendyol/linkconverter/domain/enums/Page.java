package com.trendyol.linkconverter.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Page {
    PRODUCT("Product"),
    SEARCH("Search"),
    HOME("Home");

    private final String value;
}
