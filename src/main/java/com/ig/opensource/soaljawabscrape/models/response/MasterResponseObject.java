package com.ig.opensource.soaljawabscrape.models.response;

/**
 * Created by Amir on 12/4/2019 2:01 PM
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MasterResponseObject {

    private String timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
    private String message = "N/A";
    private String errorCode = "N/A";
    private String errorDesc = "N/A";
    private Object t = null;

}
