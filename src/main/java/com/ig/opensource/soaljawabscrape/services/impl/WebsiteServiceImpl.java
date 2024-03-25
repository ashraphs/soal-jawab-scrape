package com.ig.opensource.soaljawabscrape.services.impl;

import com.ig.opensource.soaljawabscrape.models.dto.ScrapWebsiteSourceRequestDTO;
import com.ig.opensource.soaljawabscrape.models.dto.ScrapWebsiteSourceResponseDTO;
import com.ig.opensource.soaljawabscrape.models.entities.ScrapWebsiteSoalJawab;
import com.ig.opensource.soaljawabscrape.models.entities.ScrapWebsiteSource;
import com.ig.opensource.soaljawabscrape.models.response.MasterResponseObject;
import com.ig.opensource.soaljawabscrape.repositories.ScrapperWebsiteSourceRepository;
import com.ig.opensource.soaljawabscrape.repositories.SoalJawabWebsiteRepository;
import com.ig.opensource.soaljawabscrape.services.ScrapperService;
import com.ig.opensource.soaljawabscrape.services.WebsiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class WebsiteServiceImpl implements WebsiteService {

    @Autowired
    private ScrapperWebsiteSourceRepository scrapperWebsiteSourceRepository;
    @Autowired
    private SoalJawabWebsiteRepository soalJawabWebsiteRepository;
    @Autowired
    private ScrapperService scrapperService;


    @Override
    public Page<ScrapWebsiteSoalJawab> getWebsitesWithPagination(int pageNo, int pageSize, String keyword) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return soalJawabWebsiteRepository.findByActiveIsTrueAndDeletedIsFalseAndTitleLike(pageable, "%" + keyword + "%");
    }

    @Override
    public MasterResponseObject scrapConfiguration(ScrapWebsiteSourceRequestDTO requestDTO) {
        ScrapWebsiteSourceResponseDTO scrapWebsiteSourceResponseDTO = new ScrapWebsiteSourceResponseDTO();
        switch (requestDTO.getMethod()) {
            case "LIST" -> scrapWebsiteSourceResponseDTO = listWebsiteSource();
            case "CREATE" -> scrapWebsiteSourceResponseDTO = createWebsiteSource(requestDTO);
            case "CREATE-REPEAT" -> scrapWebsiteSourceResponseDTO = createWebsiteSourceInRepetition(requestDTO);
            case "UPDATE" -> scrapWebsiteSourceResponseDTO = updateWebsiteSource(requestDTO);
            case "DELETE" -> scrapWebsiteSourceResponseDTO = deleteWebsiteSource(requestDTO);
            default -> {
                return MasterResponseObject.builder()
                        .t(new ScrapWebsiteSourceResponseDTO("Method not found", null))
                        .build();
            }
        }

        return MasterResponseObject.builder()
                .t(scrapWebsiteSourceResponseDTO)
                .build();
    }

    private ScrapWebsiteSourceResponseDTO listWebsiteSource() {
        return new ScrapWebsiteSourceResponseDTO("success", scrapperWebsiteSourceRepository.findAllByActiveIsTrueAndDeletedIsFalse());
    }

    private ScrapWebsiteSourceResponseDTO createWebsiteSource(ScrapWebsiteSourceRequestDTO requestDTO) {
        if (scrapperWebsiteSourceRepository.findByActiveIsTrueAndDeletedIsFalseAndLink(requestDTO.getUrl()).isPresent()) {
            new ScrapWebsiteSourceResponseDTO("duplicate", null);
        }
        ScrapWebsiteSource scrapWebsiteSource = new ScrapWebsiteSource();
        scrapWebsiteSource.setLink(requestDTO.getUrl());
        scrapperWebsiteSourceRepository.save(scrapWebsiteSource);

        return new ScrapWebsiteSourceResponseDTO("success", null);
    }

    private ScrapWebsiteSourceResponseDTO updateWebsiteSource(ScrapWebsiteSourceRequestDTO requestDTO) {
        Optional<ScrapWebsiteSource> scrapWebsiteSource = scrapperWebsiteSourceRepository.findByActiveIsTrueAndDeletedIsFalseAndLink(requestDTO.getUrl());
        if (scrapWebsiteSource.isEmpty()) {
            new ScrapWebsiteSourceResponseDTO("not exist", null);
        }

        if (scrapWebsiteSource.isPresent()) {
            scrapWebsiteSource.get().setLink(requestDTO.getUrl());
            scrapperWebsiteSourceRepository.save(scrapWebsiteSource.get());
        }

        return new ScrapWebsiteSourceResponseDTO("success", null);
    }

    private ScrapWebsiteSourceResponseDTO deleteWebsiteSource(ScrapWebsiteSourceRequestDTO requestDTO) {
        Optional<ScrapWebsiteSource> scrapWebsiteSource = scrapperWebsiteSourceRepository.findByActiveIsTrueAndDeletedIsFalseAndLink(requestDTO.getUrl());
        if (scrapWebsiteSource.isEmpty()) {
            new ScrapWebsiteSourceResponseDTO("not exist", null);
        }

        scrapWebsiteSource.ifPresent(websiteSource -> scrapperWebsiteSourceRepository.delete(websiteSource));

        return new ScrapWebsiteSourceResponseDTO("success", null);
    }

    private ScrapWebsiteSourceResponseDTO createWebsiteSourceInRepetition(ScrapWebsiteSourceRequestDTO requestDTO) {
        if (scrapperWebsiteSourceRepository.findByActiveIsTrueAndDeletedIsFalseAndLink(requestDTO.getUrl()).isPresent()) {
            new ScrapWebsiteSourceResponseDTO("duplicate", null);
        }

        for (int i = 1; i < requestDTO.getEndIndex(); i++) {
            ScrapWebsiteSource scrapWebsiteSource = new ScrapWebsiteSource();
            scrapWebsiteSource.setLink(requestDTO.getUrl().concat(i + "/"));
            scrapperWebsiteSourceRepository.save(scrapWebsiteSource);

            log.info("scrapWebsiteSource: {}", scrapWebsiteSource);

            if (i == requestDTO.getEndIndex() + 2) {
                break;
            }
        }

        return new ScrapWebsiteSourceResponseDTO("success", null);
    }


}
