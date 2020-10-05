package com.trendyol.linkconverter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.linkconverter.controller.model.request.ConvertUrlToDeeplinkRequest;
import com.trendyol.linkconverter.controller.model.response.DeeplinkResponse;
import com.trendyol.linkconverter.service.UrlConvertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UrlConvertController.class)
class UrlConvertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UrlConvertService urlConvertService;

    @Test
    public void it_should_convert_url_to_deeplink() throws Exception {
        //GIVEN
        String url = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064";
        ConvertUrlToDeeplinkRequest convertUrlToDeeplinkRequest = new ConvertUrlToDeeplinkRequest(url);

        DeeplinkResponse response = new DeeplinkResponse("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");

        given(urlConvertService.convertToDeeplink(convertUrlToDeeplinkRequest)).willReturn(response);


        //WHEN - THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/url/convert/deeplink")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(convertUrlToDeeplinkRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("deeplink", is("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064")));
    }


}