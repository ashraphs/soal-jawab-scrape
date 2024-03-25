package com.ig.opensource.soaljawabscrape.models.entities.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

/**
 * Created by amir on 29/06/2018.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -5693162489720182075L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;


}
