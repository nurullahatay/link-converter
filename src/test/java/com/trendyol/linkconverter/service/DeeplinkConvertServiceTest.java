package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.configuration.HostResource;
import com.trendyol.linkconverter.controller.model.request.ConvertDeeplinkToUrlRequest;
import com.trendyol.linkconverter.controller.model.response.UrlResponse;
import com.trendyol.linkconverter.domain.log.ConvertType;
import com.trendyol.linkconverter.domain.log.Log;
import com.trendyol.linkconverter.repository.LinkLogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeeplinkConvertServiceTest {

    @Mock
    private LinkLogService linkLogService;

    @Mock
    private HostResource hostResource;

    @InjectMocks
    private DeeplinkConvertService deeplinkConvertService;

    @Test
    public void it_should_convert_product1_deeplink_to_product_url() {
        //GIVEN
        String deeplink = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";
        ConvertDeeplinkToUrlRequest convertDeeplinkToUrlRequest = new ConvertDeeplinkToUrlRequest(deeplink);

        given(hostResource.getUrl()).willReturn("https://www.trendyol.com");

        //WHEN
        UrlResponse urlResponse = deeplinkConvertService.convertToUrl(convertDeeplinkToUrlRequest);


        //THEN
        assertThat(urlResponse.getUrl()).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(linkLogService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.URL);
        assertThat(log.getDeeplink()).isEqualTo(deeplink);
        assertThat(log.getUrl()).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064");
    }

    @Test
    public void it_should_convert_product2_deeplink_to_product_url() {
        //GIVEN
        String deeplink = "ty://?Page=Product&ContentId=1925865";
        ConvertDeeplinkToUrlRequest convertDeeplinkToUrlRequest = new ConvertDeeplinkToUrlRequest(deeplink);

        given(hostResource.getUrl()).willReturn("https://www.trendyol.com");

        //WHEN
        UrlResponse urlResponse = deeplinkConvertService.convertToUrl(convertDeeplinkToUrlRequest);


        //THEN
        assertThat(urlResponse.getUrl()).isEqualTo("https://www.trendyol.com/brand/name-p-1925865");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(linkLogService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.URL);
        assertThat(log.getDeeplink()).isEqualTo(deeplink);
        assertThat(log.getUrl()).isEqualTo("https://www.trendyol.com/brand/name-p-1925865");
    }

    @Test
    public void it_should_convert_product3_deeplink_to_product_url() {
        //GIVEN
        String deeplink = "ty://?Page=Product&ContentId=1925865&CampaignId=439892";
        ConvertDeeplinkToUrlRequest convertDeeplinkToUrlRequest = new ConvertDeeplinkToUrlRequest(deeplink);

        given(hostResource.getUrl()).willReturn("https://www.trendyol.com");

        //WHEN
        UrlResponse urlResponse = deeplinkConvertService.convertToUrl(convertDeeplinkToUrlRequest);


        //THEN
        assertThat(urlResponse.getUrl()).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(linkLogService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.URL);
        assertThat(log.getDeeplink()).isEqualTo(deeplink);
        assertThat(log.getUrl()).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892");
    }

    @Test
    public void it_should_convert_product4_deeplink_to_product_url() {
        //GIVEN
        String deeplink = "ty://?Page=Product&ContentId=1925865&MerchantId=105064";
        ConvertDeeplinkToUrlRequest convertDeeplinkToUrlRequest = new ConvertDeeplinkToUrlRequest(deeplink);

        given(hostResource.getUrl()).willReturn("https://www.trendyol.com");

        //WHEN
        UrlResponse urlResponse = deeplinkConvertService.convertToUrl(convertDeeplinkToUrlRequest);


        //THEN
        assertThat(urlResponse.getUrl()).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?merchantId=105064");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(linkLogService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.URL);
        assertThat(log.getDeeplink()).isEqualTo(deeplink);
        assertThat(log.getUrl()).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?merchantId=105064");
    }

    @Test
    public void it_should_convert_search_deeplink_to_search_url() {
        //GIVEN
        String deeplink = "ty://?Page=Search&Query=elbise";
        ConvertDeeplinkToUrlRequest convertDeeplinkToUrlRequest = new ConvertDeeplinkToUrlRequest(deeplink);

        given(hostResource.getUrl()).willReturn("https://www.trendyol.com");

        //WHEN
        UrlResponse urlResponse = deeplinkConvertService.convertToUrl(convertDeeplinkToUrlRequest);


        //THEN
        assertThat(urlResponse.getUrl()).isEqualTo("https://www.trendyol.com/tum--urunler?q=elbise");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(linkLogService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.URL);
        assertThat(log.getDeeplink()).isEqualTo(deeplink);
        assertThat(log.getUrl()).isEqualTo("https://www.trendyol.com/tum--urunler?q=elbise");
    }

    @Test
    public void it_should_convert_search_deeplink_to_search_url_with_turkish_character_contains_url() {
        //GIVEN
        String deeplink = "ty://?Page=Search&Query=ütü";
        ConvertDeeplinkToUrlRequest convertDeeplinkToUrlRequest = new ConvertDeeplinkToUrlRequest(deeplink);

        given(hostResource.getUrl()).willReturn("https://www.trendyol.com");

        //WHEN
        UrlResponse urlResponse = deeplinkConvertService.convertToUrl(convertDeeplinkToUrlRequest);


        //THEN
        assertThat(urlResponse.getUrl()).isEqualTo("https://www.trendyol.com/tum--urunler?q=%C3%BCt%C3%BC");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(linkLogService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.URL);
        assertThat(log.getDeeplink()).isEqualTo(deeplink);
        assertThat(log.getUrl()).isEqualTo("https://www.trendyol.com/tum--urunler?q=%C3%BCt%C3%BC");
    }

    @Test
    public void it_should_convert_search_deeplink_to_search_url_with_encode_character_contains_url() {
        //GIVEN
        String deeplink = "ty://?Page=Search&Query=%C3%BCt%C3%BC";
        ConvertDeeplinkToUrlRequest convertDeeplinkToUrlRequest = new ConvertDeeplinkToUrlRequest(deeplink);

        given(hostResource.getUrl()).willReturn("https://www.trendyol.com");

        //WHEN
        UrlResponse urlResponse = deeplinkConvertService.convertToUrl(convertDeeplinkToUrlRequest);


        //THEN
        assertThat(urlResponse.getUrl()).isEqualTo("https://www.trendyol.com/tum--urunler?q=%C3%BCt%C3%BC");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(linkLogService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.URL);
        assertThat(log.getDeeplink()).isEqualTo(deeplink);
        assertThat(log.getUrl()).isEqualTo("https://www.trendyol.com/tum--urunler?q=%C3%BCt%C3%BC");
    }

    @Test
    public void it_should_convert_my_account_deeplink_to_home_url() {
        //GIVEN
        String deeplink = "ty://?Page=Favorites";
        ConvertDeeplinkToUrlRequest convertDeeplinkToUrlRequest = new ConvertDeeplinkToUrlRequest(deeplink);

        given(hostResource.getUrl()).willReturn("https://www.trendyol.com");

        //WHEN
        UrlResponse urlResponse = deeplinkConvertService.convertToUrl(convertDeeplinkToUrlRequest);


        //THEN
        assertThat(urlResponse.getUrl()).isEqualTo("https://www.trendyol.com");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(linkLogService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.URL);
        assertThat(log.getDeeplink()).isEqualTo(deeplink);
        assertThat(log.getUrl()).isEqualTo("https://www.trendyol.com");
    }

    @Test
    public void it_should_convert_my_orders_deeplink_to_home_url() {
        //GIVEN
        String deeplink = "ty://?Page=Orders";
        ConvertDeeplinkToUrlRequest convertDeeplinkToUrlRequest = new ConvertDeeplinkToUrlRequest(deeplink);

        given(hostResource.getUrl()).willReturn("https://www.trendyol.com");

        //WHEN
        UrlResponse urlResponse = deeplinkConvertService.convertToUrl(convertDeeplinkToUrlRequest);


        //THEN
        assertThat(urlResponse.getUrl()).isEqualTo("https://www.trendyol.com");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(linkLogService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.URL);
        assertThat(log.getDeeplink()).isEqualTo(deeplink);
        assertThat(log.getUrl()).isEqualTo("https://www.trendyol.com");
    }
}