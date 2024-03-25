<h1 align="center">Welcome to soal-jawab-scrape 👋</h1>
<p>
  <a href="https://twitter.com/https:\/\/twitter.com\/amirbahar\_" target="_blank">
    <img alt="Twitter: https:\/\/twitter.com\/amirbahar\_" src="https://img.shields.io/twitter/follow/https:\/\/twitter.com\/amirbahar\_.svg?style=social" />
  </a>
</p>

> Soal-Jawab-Scrape is a search api inspiration from website https://zulkiflialbakri.com/category/soal-jawab-agama/ .This project developed using APIScrapper and Spring JAVA 17. This project aims to learn how scrape API technology work and also give user to search their answer based on API listed.


## Usage

```sh
mvn clean compile spring-boot:run -f
```

## Batch JOB
- Run every 3am to scrape all the link from website and save into table `scrap_website_source`
- After the scrape done, the data will be insert into table `scrap_website_soal_jawab`

## API
### 1. Add configuration
- Please add the link for website that will be scrape

```sh
curl --location 'http://localhost:8080/soaljawab/configuration/scrap' \
--data '{
"method": "CREATE",
"url": "https://zulkiflialbakri.com/category/soal-jawab-agama/page/2/"
}'
```

### 2. Search 
```sh
curl --location --request POST 'http://localhost:8080/soaljawab/get-websites?keyword=surah'

Response:
{
    "content": [
        {
            "id": "101cb370-522d-4ffe-ba5f-ab7350f625da",
            "active": true,
            "version": 0,
            "restricted": false,
            "createdDate": null,
            "createdBy": null,
            "lastModifiedDate": null,
            "lastModifiedBy": null,
            "author": null,
            "link": "https://zulkiflialbakri.com/mualaf-tidak-mahir-surah-al-fatihah-dalam-solat/",
            "title": "mualaf tidak mahir surah al fatihah dalam solat"
        },
        {
            "id": "b98e48f6-875b-4039-975d-49eeb013fae3",
            "active": true,
            "version": 0,
            "restricted": false,
            "createdDate": null,
            "createdBy": null,
            "lastModifiedDate": null,
            "lastModifiedBy": null,
            "author": null,
            "link": "https://zulkiflialbakri.com/makhraj-huruf-surah-al-fatihah/",
            "title": "makhraj huruf surah al fatihah"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 2,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
}
```



## Author

👤 **Amir.Bahar**

* Twitter: [@https:\/\/twitter.com\/amirbahar\_](https://twitter.com/https:\/\/twitter.com\/amirbahar\_)
* Github: [@ashraphs](https://github.com/ashraphs)
* LinkedIn: [@https:\/\/www.linkedin.com\/in\/amir-asyraf-83b940111\/](https://linkedin.com/in/https:\/\/www.linkedin.com\/in\/amir-asyraf-83b940111\/)

## Show your support

Give a ⭐️ if this project helped you!

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_