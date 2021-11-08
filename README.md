#Link Converter Task

Implementing a web service that helps others to convert shopme.com links between mobile and web applications. Web applications use URLs and mobile applications use deeplinks. Both applications use links to redirect specific locations inside applications. When you want to redirect across applications.

A quick example for URL and deeplink:

Web URL: https://www.shopme.com/butik/liste/erkek

Equivalent deeplink: ty://?Page=Home&SectionId=2

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
