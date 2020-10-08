package com.trendyol.linkconverter.util;

import org.junit.jupiter.api.Test;

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
        assertThat(queryParameters.get("page")).isEqualToIgnoringCase("Product");
        assertThat(queryParameters.get("contentid")).isEqualToIgnoringCase("1925865");
        assertThat(queryParameters.get("campaignid")).isEqualToIgnoringCase("439892");
        assertThat(queryParameters.get("merchantid")).isEqualToIgnoringCase("105064");
    }

    @Test
    public void it_should_resolve_query_parameters_for_encoded_characters() {
        //GIVEN
        String query = "Page=Search&Query=%C3%BCt%C3%BC";

        //WHEN
        Map<String, String> queryParameters = LinkUtils.resolveQueryParameters(query);

        //THEN
        assertThat(queryParameters.get("page")).isEqualToIgnoringCase("Search");
        assertThat(queryParameters.get("query")).isEqualToIgnoringCase("%C3%BCt%C3%BC");
    }


    @Test
    public void it_should_resolve_query_parameters_for_decoded_characters() {
        //GIVEN
        String query = "Page=Search&Query=ütü";

        //WHEN
        Map<String, String> queryParameters = LinkUtils.resolveQueryParameters(query);

        //THEN
        assertThat(queryParameters.get("page")).isEqualToIgnoringCase("Search");
        assertThat(queryParameters.get("query")).isEqualToIgnoringCase("%C3%BCt%C3%BC");
    }
}