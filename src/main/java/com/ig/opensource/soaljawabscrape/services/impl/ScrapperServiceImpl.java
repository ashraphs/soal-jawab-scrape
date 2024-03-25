package com.ig.opensource.soaljawabscrape.services.impl;

import com.ig.opensource.soaljawabscrape.models.entities.ScrapWebsiteSoalJawab;
import com.ig.opensource.soaljawabscrape.models.response.ScrapperResponseDTO;
import com.ig.opensource.soaljawabscrape.repositories.ScrapperWebsiteSourceRepository;
import com.ig.opensource.soaljawabscrape.repositories.SoalJawabWebsiteRepository;
import com.ig.opensource.soaljawabscrape.services.ScrapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ScrapperServiceImpl implements ScrapperService {

    @Autowired
    private ScrapperWebsiteSourceRepository scrapperWebsiteSourceRepository;
    @Autowired
    private SoalJawabWebsiteRepository soalJawabWebsiteRepository;

    @Value("${api.scraperapi.key}")
    private String apiKey;

    //    @Transactional
    @Override
    public void scrape() {
        log.info("Scraping Started");
        scrapperWebsiteSourceRepository.findAllByActiveIsTrueAndDeletedIsFalse().forEach(x -> {
            List<ScrapperResponseDTO> response = apiCallScrapper(x.getLink(), apiKey);
            log.info("response Scraped: {}", response);
            List<ScrapWebsiteSoalJawab> scrapWebsiteSoalJawabs = new ArrayList<>();
            log.info("Scraped: {}", scrapWebsiteSoalJawabs);

            response.forEach(y -> {
                ScrapWebsiteSoalJawab soalJawab = new ScrapWebsiteSoalJawab();
                soalJawab.setTitle(y.getTitle());
                soalJawab.setLink(y.getLink());
                scrapWebsiteSoalJawabs.add(soalJawab);
            });

            soalJawabWebsiteRepository.saveAll(scrapWebsiteSoalJawabs);
        });
    }

    private List<ScrapperResponseDTO> apiCallScrapper(String link, String apiKey) {
        List<ScrapperResponseDTO> scrapperResponses = new ArrayList<>();

        String url = "https://api.scraperapi.com/?api_key=" + apiKey + "&url=" + link;

        try {
            URL urlForGetRequest = new URL(url);
            String readLine;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                while ((readLine = in.readLine()) != null) {
                    if (readLine.contains("class=\"plain\" aria-label=\"#")) {

                        String linkRegex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
                        Pattern pattern = Pattern.compile(linkRegex);
                        Matcher matcher = pattern.matcher(readLine);

                        // Find URLs in the text and print them
                        while (matcher.find()) {
                            String urlMatch = matcher.group();
                            ScrapperResponseDTO scrapperResponse = new ScrapperResponseDTO();
                            scrapperResponse.setTitle(new URL(urlMatch).getPath().replaceAll("[\\\\/\\d]", "").replace('-', ' '));
                            scrapperResponse.setLink(urlMatch);
                            scrapperResponses.add(scrapperResponse);
                            log.info("URL detected: {}", urlMatch);
                        }
                    }
                }
                in.close();
                return scrapperResponses;
            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception ex) {
            log.error("Error in API Call", ex.getMessage());
        }

        return scrapperResponses;
    }


}
