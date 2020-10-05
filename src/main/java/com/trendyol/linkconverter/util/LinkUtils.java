package com.trendyol.linkconverter.util;

import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LinkUtils {

    private static final String EQUALS_OPERATOR = "=";
    private static final String SEPARATE_OPERATOR = "&";
    private static final String ENCODE_CHARACTER = "%";

    private LinkUtils() {
    }

    public static Map<String, String> getParameters(String query) {
        Map<String, String> parameters = new HashMap<>();

        try {
            if (StringUtils.isNotEmpty(query)) {
                String[] pairs = query.split(SEPARATE_OPERATOR);
                for (String pair : pairs) {
                    String key = StringUtils.substringBefore(pair, EQUALS_OPERATOR).toLowerCase();
                    String value = StringUtils.substringAfter(pair, EQUALS_OPERATOR);

                    if (StringUtils.containsNone(value, ENCODE_CHARACTER)) {
                        value = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
                    }

                    parameters.put(key, value);
                }
            }
        } catch (Exception ex) {
            //todo log and define dedicated exception
            throw new RuntimeException(ex);
        }
        return parameters;
    }
}
