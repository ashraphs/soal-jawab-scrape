package com.ig.opensource.soaljawabscrape.repositories;

import com.ig.opensource.soaljawabscrape.models.entities.ScrapWebsiteSoalJawab;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SoalJawabWebsiteRepository extends JpaRepository<ScrapWebsiteSoalJawab, Long> {

    Page<ScrapWebsiteSoalJawab> findByActiveIsTrueAndDeletedIsFalseAndTitleLike(Pageable pageable, String keyword);
}
