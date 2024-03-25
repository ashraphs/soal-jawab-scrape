package com.ig.opensource.soaljawabscrape.controller;

import com.ig.opensource.soaljawabscrape.models.dto.ScrapWebsiteSourceRequestDTO;
import com.ig.opensource.soaljawabscrape.models.entities.ScrapWebsiteSoalJawab;
import com.ig.opensource.soaljawabscrape.models.response.MasterResponseObject;
import com.ig.opensource.soaljawabscrape.services.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/soaljawab")
public class SoalJawabController {

    @Autowired
    private WebsiteService websiteService;


    @PostMapping(value = "/get-websites", produces = "application/json")
    public Page<ScrapWebsiteSoalJawab> getAllWebsiteWithPagination(@RequestParam(defaultValue = "1") int pageNo,
                                                                   @RequestParam(defaultValue = "10") int pageSize,
                                                                   @RequestParam(defaultValue = "keyword") String keyword) {
        return websiteService.getWebsitesWithPagination(pageNo, pageSize, keyword);
    }

    @PostMapping(value = "/configuration/scrap", produces = "application/json")
    public MasterResponseObject scrapConfiguration(@RequestBody ScrapWebsiteSourceRequestDTO requestDTO) {
        return websiteService.scrapConfiguration(requestDTO);
    }


}
