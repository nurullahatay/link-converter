# Link Converter Backend Application

## 1. About the Project

Link Converter app is the web service that helps others convert connections between mobile and web apps.

Web applications use URLs, mobile applications use deep links. Both apps use links to redirect specific locations within apps. When you want to redirect between apps, you need to convert URLs to deep links or deep links to URLs.

A quick example for URL and deeplink:  
URL: https://{YOUR_WEBSITE_URL}/Hesabim/Favoriler  
Deeplink: ty://?Page=Home  

#### Used technologies
 - Java 1.8
 - Spring Boot
 - Couchbase
 - Swagger
 
## 2. Getting Started

###  2.1. Installation

You should have [docker application](https://www.docker.com/products/docker-desktop)  
Install a Couchbase server
    
    docker run -t --name db -p 8091-8094:8091-8094 -p 11210:11210 couchbase/server-sandbox:6.5.0  

Default Couchbase login info :

    username: Administrator
    password: password


If your Couchbase login info different this change in application.yaml file.

Create a bucket named **'linklog'** in Couchbase server.


###  2.2. Usage

Clone repository

    git clone https://github.com/nurullahatay/link-converter

Run application  
```
$ cd link-converter
$ mvn spring-boot:run  
```

Show application ui in Swagger  

    http://localhost:8080/swagger-ui.html  

Run tests
```
$ cd link-converter
$ mvn test
```


#### Examples

1. Url to deeplink example request and response

    http://localhost:8080/v1/url/convert/deeplink HTTP POST
    
    Request
    
    ```
    {
      "url": "https://{YOUR_WEBSITE_URL}/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"
    }
    ```
    Response
    
    ```
    {
      "deeplink": "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
    }
    ```

2. Deeplink to url example request and response

    http://localhost:8080/v1/deeplink/convert/url HTTP POST
    
    Request
    
    ```
    {
      "deeplink": "ty://?Page=Product&ContentId=1925865&CampaignId=439892"
    }
    
    ```
    Response
    
    ```
    {
      "url": "https://{YOUR_WEBSITE_URL}/brand/name-p-1925865?boutiqueId=439892"
    }
    ```

3. Deeplink to url example invalid request and response  
    
    http://localhost:8080/v1/deeplink/convert/url HTTP POST
    
    Request
    ```
    {
      "deeplink": "ex://?Page=Product&ContentId=1925865&CampaignId=439892"
    }
    ```
    
    Response  
    Status 400
    ```
    {
      "title": "Bad Request",
      "status": 400,
      "message": "Invalid deeplink."
    }
    ```

## 3. Algorithm

#### 3.1 Url To Deeplink
   Website has 2 main pages, so these pages must have equivalent deeplinks.


1. Product Detail Page 
    Every product in your website has multiple product detail page URLs.
    
    https://{YOUR_WEBSITE_URL}/{BrandName-or-CategoryName}/{ProductName}-p-{ContentId}?boutiqueId={BoutiqueId}&merchantId={MerchantId}
    
    - Product detail page URL must contain "-p-" text.
    - Product detail page URLs must contain contentId which is located after "-p-" prefix.
    - URL can contain boutiqueId and merchantId.
    - If URL doesn't contain boutiqueId, you shouldn't add CampaignId to deeplink 
    - If URL doesn't contain merchantId, you shouldn't add MerchantId to deeplink
    - Deeplink and Web URL have differences on CampaignId and boutiqueId. Deeplinks have CampaignId, web URLs have boutiqueId.
 
2. Search Page 

    Search page URL must be converted to valid deeplinks.
    
    - Search pages must contain "tum--urunler"
    - "q" query parameters must be converted to Query deeplink parameter.
    - Be careful with Turkish characters

3.  Other Page  

    Other pages, which are not filtered as homepage, search page or product detail page, must be converted as empty homepage deeplink.3.  Other pages, which are not filtered as homepage, search page or product detail page, must be converted as empty homepage deeplink.

#### 3.2 Deeplink To Url

The reverse of the rules for converting url to deeplink is valid.



