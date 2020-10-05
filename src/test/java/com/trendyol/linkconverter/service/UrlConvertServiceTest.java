package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.controller.model.request.ConvertUrlToDeeplinkRequest;
import com.trendyol.linkconverter.controller.model.response.DeeplinkResponse;
import com.trendyol.linkconverter.domain.log.ConvertType;
import com.trendyol.linkconverter.domain.log.Log;
import com.trendyol.linkconverter.repository.LogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UrlConvertServiceTest {

    @Mock
    private LogService logService;

    @InjectMocks
    private UrlConvertService urlConvertService;

    @Test
    public void it_should_convert_product1_url_to_product_deeplink() {
        //GIVEN
        String url = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064";
        ConvertUrlToDeeplinkRequest convertUrlToDeeplinkRequest = new ConvertUrlToDeeplinkRequest(url);

        //WHEN
        DeeplinkResponse deeplinkResponse = urlConvertService.convertToDeeplink(convertUrlToDeeplinkRequest);


        //THEN
        assertThat(deeplinkResponse.getDeeplink()).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(logService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.DEEPLINK);
        assertThat(log.getUrl()).isEqualTo(url);
        assertThat(log.getDeeplink()).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");
    }

    @Test
    public void it_should_convert_product2_url_to_product_deeplink() {
        //GIVEN
        String url = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865";
        ConvertUrlToDeeplinkRequest convertUrlToDeeplinkRequest = new ConvertUrlToDeeplinkRequest(url);

        //WHEN
        DeeplinkResponse deeplinkResponse = urlConvertService.convertToDeeplink(convertUrlToDeeplinkRequest);


        //THEN
        assertThat(deeplinkResponse.getDeeplink()).isEqualTo("ty://?Page=Product&ContentId=1925865");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(logService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.DEEPLINK);
        assertThat(log.getUrl()).isEqualTo(url);
        assertThat(log.getDeeplink()).isEqualTo("ty://?Page=Product&ContentId=1925865");
    }

    @Test
    public void it_should_convert_product3_url_to_product_deeplink() {
        //GIVEN
        String url = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?BoutiqueId=439892";
        ConvertUrlToDeeplinkRequest convertUrlToDeeplinkRequest = new ConvertUrlToDeeplinkRequest(url);

        //WHEN
        DeeplinkResponse deeplinkResponse = urlConvertService.convertToDeeplink(convertUrlToDeeplinkRequest);


        //THEN
        assertThat(deeplinkResponse.getDeeplink()).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(logService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.DEEPLINK);
        assertThat(log.getUrl()).isEqualTo(url);
        assertThat(log.getDeeplink()).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892");
    }

    @Test
    public void it_should_convert_product4_url_to_product_deeplink() {
        //GIVEN
        String url = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?merchantId=105064";
        ConvertUrlToDeeplinkRequest convertUrlToDeeplinkRequest = new ConvertUrlToDeeplinkRequest(url);

        //WHEN
        DeeplinkResponse deeplinkResponse = urlConvertService.convertToDeeplink(convertUrlToDeeplinkRequest);


        //THEN
        assertThat(deeplinkResponse.getDeeplink()).isEqualTo("ty://?Page=Product&ContentId=1925865&MerchantId=105064");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(logService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.DEEPLINK);
        assertThat(log.getUrl()).isEqualTo(url);
        assertThat(log.getDeeplink()).isEqualTo("ty://?Page=Product&ContentId=1925865&MerchantId=105064");
    }

    @Test
    public void it_should_convert_search_url_to_search_deeplink() {
        //GIVEN
        String url = "https://www.trendyol.com/tum--urunler?q=elbise";
        ConvertUrlToDeeplinkRequest convertUrlToDeeplinkRequest = new ConvertUrlToDeeplinkRequest(url);

        //WHEN
        DeeplinkResponse deeplinkResponse = urlConvertService.convertToDeeplink(convertUrlToDeeplinkRequest);


        //THEN
        assertThat(deeplinkResponse.getDeeplink()).isEqualTo("ty://?Page=Search&Query=elbise");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(logService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.DEEPLINK);
        assertThat(log.getUrl()).isEqualTo(url);
        assertThat(log.getDeeplink()).isEqualTo("ty://?Page=Search&Query=elbise");
    }

    @Test
    public void it_should_convert_search_url_to_search_deeplink_with_turkish_character_contains_url() {
        //GIVEN
        String url = "https://www.trendyol.com/tum--urunler?q=ütü";
        ConvertUrlToDeeplinkRequest convertUrlToDeeplinkRequest = new ConvertUrlToDeeplinkRequest(url);

        //WHEN
        DeeplinkResponse deeplinkResponse = urlConvertService.convertToDeeplink(convertUrlToDeeplinkRequest);


        //THEN
        assertThat(deeplinkResponse.getDeeplink()).isEqualTo("ty://?Page=Search&Query=%C3%BCt%C3%BC");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(logService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.DEEPLINK);
        assertThat(log.getUrl()).isEqualTo(url);
        assertThat(log.getDeeplink()).isEqualTo("ty://?Page=Search&Query=%C3%BCt%C3%BC");
    }

    @Test
    public void it_should_convert_search_url_to_search_deeplink_with_encode_character_contains_url() {
        //GIVEN
        String url = "https://www.trendyol.com/tum--urunler?q=%C3%BCt%C3%BC";
        ConvertUrlToDeeplinkRequest convertUrlToDeeplinkRequest = new ConvertUrlToDeeplinkRequest(url);

        //WHEN
        DeeplinkResponse deeplinkResponse = urlConvertService.convertToDeeplink(convertUrlToDeeplinkRequest);


        //THEN
        assertThat(deeplinkResponse.getDeeplink()).isEqualTo("ty://?Page=Search&Query=%C3%BCt%C3%BC");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(logService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.DEEPLINK);
        assertThat(log.getUrl()).isEqualTo(url);
        assertThat(log.getDeeplink()).isEqualTo("ty://?Page=Search&Query=%C3%BCt%C3%BC");
    }

    @Test
    public void it_should_convert_my_account_url_to_home_deeplink() {
        //GIVEN
        String url = "https://www.trendyol.com/Hesabim/Favoriler";
        ConvertUrlToDeeplinkRequest convertUrlToDeeplinkRequest = new ConvertUrlToDeeplinkRequest(url);

        //WHEN
        DeeplinkResponse deeplinkResponse = urlConvertService.convertToDeeplink(convertUrlToDeeplinkRequest);


        //THEN
        assertThat(deeplinkResponse.getDeeplink()).isEqualTo("ty://?Page=Home");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(logService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.DEEPLINK);
        assertThat(log.getUrl()).isEqualTo(url);
        assertThat(log.getDeeplink()).isEqualTo("ty://?Page=Home");
    }

    @Test
    public void it_should_convert_my_orders_url_to_home_deeplink() {
        //GIVEN
        String url = "https://www.trendyol.com/Hesabim/#/Siparislerim";
        ConvertUrlToDeeplinkRequest convertUrlToDeeplinkRequest = new ConvertUrlToDeeplinkRequest(url);

        //WHEN
        DeeplinkResponse deeplinkResponse = urlConvertService.convertToDeeplink(convertUrlToDeeplinkRequest);


        //THEN
        assertThat(deeplinkResponse.getDeeplink()).isEqualTo("ty://?Page=Home");

        ArgumentCaptor<Log> acLog = ArgumentCaptor.forClass(Log.class);
        Mockito.verify(logService).saveLog(acLog.capture());

        Log log = acLog.getValue();
        assertThat(log.getConvertType()).isEqualTo(ConvertType.DEEPLINK);
        assertThat(log.getUrl()).isEqualTo(url);
        assertThat(log.getDeeplink()).isEqualTo("ty://?Page=Home");
    }
}