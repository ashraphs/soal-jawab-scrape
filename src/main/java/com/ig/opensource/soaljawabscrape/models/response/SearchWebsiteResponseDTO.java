package com.ig.opensource.soaljawabscrape.models.response;

import lombok.Data;

import java.util.List;

@Data
public class SearchWebsiteResponseDTO {
    private String keyword;
    private List<WebsiteResponseDTO> websiteResponseDTOS;

}
