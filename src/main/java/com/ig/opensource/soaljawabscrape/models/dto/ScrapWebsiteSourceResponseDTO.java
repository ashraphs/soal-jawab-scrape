package com.ig.opensource.soaljawabscrape.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScrapWebsiteSourceResponseDTO {
    private String status;
    private Object data;
}
