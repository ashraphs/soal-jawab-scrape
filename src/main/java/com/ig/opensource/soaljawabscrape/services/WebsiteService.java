package com.ig.opensource.soaljawabscrape.services;


import com.ig.opensource.soaljawabscrape.models.dto.ScrapWebsiteSourceRequestDTO;
import com.ig.opensource.soaljawabscrape.models.entities.ScrapWebsiteSoalJawab;
import com.ig.opensource.soaljawabscrape.models.response.MasterResponseObject;
import org.springframework.data.domain.Page;

public interface WebsiteService {

    Page<ScrapWebsiteSoalJawab> getWebsitesWithPagination(int pageNo, int pageSize, String keyword);

    MasterResponseObject scrapConfiguration(ScrapWebsiteSourceRequestDTO requestDTO);

}
