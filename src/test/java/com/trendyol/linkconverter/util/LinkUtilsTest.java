package com.trendyol.linkconverter.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LinkUtilsTest {

    @Test
    public void it_should_resolve_query_parameters_for_all_parameters() {
        //GIVEN
        String query = "Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";

        //WHEN
        Map<String, String> queryParameters = LinkUtils.resolveQueryParameters(query);

        //THEN
        Map<String, String> queryParametersResult = new HashMap<>();
        queryParametersResult.put("page", "Product");
        queryParametersResult.put("contentid", "1925865");
        queryParametersResult.put("campaignid", "439892");
        queryParametersResult.put("merchantid", "105064");

        assertThat(queryParameters).isEqualTo(queryParametersResult);
    }

    @Test
    public void it_should_resolve_query_parameters_for_encoded_characters() {
        //GIVEN
        String query = "Page=Search&Query=%C3%BCt%C3%BC";

        //WHEN
        Map<String, String> queryParameters = LinkUtils.resolveQueryParameters(query);

        //THEN
        Map<String, String> queryParametersResult = new HashMap<>();
        queryParametersResult.put("page", "Search");
        queryParametersResult.put("query", "%C3%BCt%C3%BC");

        assertThat(queryParameters).isEqualTo(queryParametersResult);
    }


    @Test
    public void it_should_resolve_query_parameters_for_decoded_characters() {
        //GIVEN
        String query = "Page=Search&Query=ütü";

        //WHEN
        Map<String, String> queryParameters = LinkUtils.resolveQueryParameters(query);

        //THEN
        Map<String, String> queryParametersResult = new HashMap<>();
        queryParametersResult.put("page", "Search");
        queryParametersResult.put("query", "%C3%BCt%C3%BC");

        assertThat(queryParameters).isEqualTo(queryParametersResult);
    }
}