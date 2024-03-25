package com.ig.opensource.soaljawabscrape.repositories;

import com.ig.opensource.soaljawabscrape.models.entities.ScrapWebsiteSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScrapperWebsiteSourceRepository extends JpaRepository<ScrapWebsiteSource, Long> {

    List<ScrapWebsiteSource> findAllByActiveIsTrueAndDeletedIsFalse();

    Optional<ScrapWebsiteSource> findByActiveIsTrueAndDeletedIsFalseAndLink(String link);
}
