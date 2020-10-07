package com.trendyol.linkconverter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.linkconverter.controller.model.request.ConvertDeeplinkToUrlRequest;
import com.trendyol.linkconverter.controller.model.response.UrlResponse;
import com.trendyol.linkconverter.service.DeeplinkConvertService;
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


@WebMvcTest(controllers = DeeplinkConvertController.class)
class DeeplinkConvertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeeplinkConvertService deeplinkConvertService;

    @Test
    public void it_should_convert_deeplink_to_url() throws Exception {
        //GIVEN
        String deeplink = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";
        ConvertDeeplinkToUrlRequest convertDeeplinkToUrlRequest = new ConvertDeeplinkToUrlRequest(deeplink);

        UrlResponse response = new UrlResponse("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064");

        given(deeplinkConvertService.convertToUrl(convertDeeplinkToUrlRequest)).willReturn(response);


        //WHEN - THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/deeplink/convert/url")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(convertDeeplinkToUrlRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("url", is("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064")));
    }


}