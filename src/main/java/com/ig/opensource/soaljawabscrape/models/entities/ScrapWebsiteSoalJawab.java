package com.ig.opensource.soaljawabscrape.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "scrap_website_soal_jawab")
public class ScrapWebsiteSoalJawab {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    @JsonIgnore
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version = 0;

    @Column(name = "restricted", nullable = false)
    private Boolean restricted = false;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "author")
    private String author;

    @Column(name = "link")
    private String link;

    @Column(name = "title")
    private String title;

}
