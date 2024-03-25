package com.ig.opensource.soaljawabscrape.jobs;

import com.ig.opensource.soaljawabscrape.services.ScrapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class ScrapsJobScheduler {

    @Autowired
    private ScrapperService scrapperService;


    // Schedule task to run every Monday at 3:00 AM in Kuala Lumpur timezone
    @Scheduled(cron = "0 0 3 ? * MON", zone = "Asia/Kuala_Lumpur")
    public void weeklyJobWebScrapeTask() {
        log.info("START :: Running weekly web scrape task");
        scrapperService.scrape();
        log.info("END :: Running weekly web scrape task");

    }
}
