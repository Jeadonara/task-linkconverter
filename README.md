#Link Converter Task

Implementing a web service that helps others to convert shopme.com links between mobile and web applications. Web applications use URLs and mobile applications use deeplinks. Both applications use links to redirect specific locations inside applications. When you want to redirect across applications.

A quick example for URL and deeplink:

Web URL: https://www.shopme.com/butik/liste/erkek

Equivalent deeplink: ty://?Page=Home&SectionId=2

**Task Details**
You should implement a web service that helps others to convert shopme.com links between mobile and web applications. Web applications use URLs and mobile applications use deeplinks. Both applications use links to redirect specific locations inside applications. When you want to redirect across applications, you should convert URLs to deeplinks or deeplinks to URLs.
A quick example for URL and deeplink:
Web URL: https://www.shopme.com/butik/liste/erkek Equivalent deeplink: ty://?Page=Home&SectionId=2

You can use the following technologies:
* Java 1.8 or greater, NodeJS, Go, NET Core or Kotlin for programming language * Use well-known frameworks
* Postgresql, Mysql, Couchbase, Redis or Elasticsearch for data storage
  
You should be aware of the following conventions while you are working on this exercise:
  
● Publish a running project
  
● The project is divided into three tasks, commit each task separately as you work at different
  times 
  
● Apply SOLID and OOP principles
  
● Document your project for a developer
  
● Handle exceptions for resilient web service
● Test Driven Development is a good choice, but it's up to you how you are testing your code.
  
● Nice to have: Dockerize your project


    Task 1 (Initialize a web service)
Start a new web service project and initialize it. It should be ready to be run.
    
    Task 2 (Web URL to Deeplink)
Write an endpoint that converts web URLs to deeplinks.
- The endpoint should get a web URL and return a converted deeplink.
- You must store each request and response in persistent storage.
- You should design your table columns/document fields


    1. Product Detail Page
   Every product in shopme has multiple product detail page URLs.
   https://www.shopme.com/{BrandName-or-CategoryName}/{ProductName}-p-{ContentId}? boutiqueId={BoutiqueId}&merchantId={MerchantId}
   
● Product detail page URL must contain "-p-" text.
   
● Product detail page URLs must contain contentId which is located after "-p-" prefix.
   
● URL can contain boutiqueId and merchantId.
   
● If URL doesn't contain boutiqueId, you shouldn't add CampaignId to deeplink
   
● If URL doesn't contain merchantId, you shouldn't add MerchantId to deeplink
   
● Deeplink and Web URL have differences on CampaignId and boutiqueId. Deeplinks have
   CampaignId, web URLs have boutiqueId.

    2. Search Page
   
Search page URL must be converted to valid deeplinks.
   
● Search pages path must start with "/sr"

● "q" query parameters must be converted to Query deeplink
   parameter.
   
● Be careful with Turkish characters

    3. Other pages
   
Other pages, which are not filtered as a search page or product detail page, must be converted as empty homepage deeplink.

   
https://www.shopme.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=1050 64
   
https://www.shopme.com/casio/erkek-kol-saati-p-1925865
   
https://www.shopme.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892
   
https://www.shopme.com/casio/erkek-kol-saati-p-1925865?merchantId=105064
   
https://www.shopme.com/sr?q=elbise
   
ty://?Page=Product&ContentId=1925865&Camp aignId=439892&MerchantId=105064
ty://?Page=Product&ContentId=1925865
   
ty://?Page=Product&ContentId=1925865&CampaignId=439892
ty://?Page=Product&ContentId=1925865&MerchantId=105064
ty://?Page=Search&Query=elbise

    Write an endpoint which converts deeplink to web URLs. The endpoint should get a web URL and return a converted deeplink.

- You will use rules which are explained in Task 2.
- You must store these requests and responses in persistent storage.
- You should design your table columns/document fields

`Requests`
   
ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064
  
ty://?Page=Product&ContentId=1925865
  
ty://?Page=Product&ContentId=1925865&CampaignId=439892
  
ty://?Page=Product&ContentId=1925865&MerchantId=105064
  
ty://?Page=Search&Query=elbise 
 
ty://?Page=Search&Query=%C3%BCt%C3%BC 
  
ty://?Page=Favorites 
  
ty://?Page=Orders

  `Responses`

https://www.shopme.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064

https://www.shopme.com/brand/name-p-1925865

https://www.shopme.com/brand/name-p-1925865?boutiqueId=439892

https://www.shopme.com/brand/namei-p-1925865?merchantId=105064

https://www.shopme.com/sr?q=elbise

https://www.shopme.com/sr?q=%C3%BCt%C3%BC

https://www.shopme.com 

https://www.shopme.com


**Running Application Tests:**

`./gradlew test`

**Running Application Using `Docker`:**

1-) `./gradlew build`

2-) `docker-compose up`

**Running Application Using `Gradle`:**

1-) `./gradlew bootRun`

**Trying Out Application Using `Swagger UI`:**

http://localhost:8080/swagger-ui.html#/link-converter-controller

**Trying Out Application Using `Postman`:**

Import 
`link-converter-app.postman_collection.json
`

## Author
Can Tezel
