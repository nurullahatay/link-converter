package com.trendyol.linkconverter.util;

import com.trendyol.linkconverter.configuration.exception.ResolveQueryParamException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LinkUtils {

    public static final String EQUALS_OPERATOR = "=";
    public static final String SEPARATE_OPERATOR = "&";
    public static final String QUERY_OPERATOR = "?";
    public static final String PATH_OPERATOR = "/";

    private LinkUtils() {
    }

    public static Map<String, String> resolveQueryParameters(String query) {
        Map<String, String> parameters = new HashMap<>();

        try {
            if (StringUtils.isNotEmpty(query)) {
                String[] pairs = query.split(SEPARATE_OPERATOR);
                for (String pair : pairs) {
                    String key = StringUtils.substringBefore(pair, EQUALS_OPERATOR).toLowerCase();
                    String value = StringUtils.substringAfter(pair, EQUALS_OPERATOR);

                    if (!value.equals(URLDecoder.decode(value, StandardCharsets.UTF_8.toString()))) {
                        value = URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
                    }
                    value = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());

                    parameters.put(key, value);
                }
            }
        } catch (Exception ex) {
            log.error("Error occured when query resolving, query: {} , exception: {}", query, ex);
            throw new ResolveQueryParamException();
        }
        return parameters;
    }
}
