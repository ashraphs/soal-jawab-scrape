package com.ig.opensource.soaljawabscrape.models.dto;

import lombok.Data;

@Data
public class ScrapWebsiteSourceRequestDTO {
    private String method;
    private String url;
    private int startIndex;
    private int endIndex;
}
