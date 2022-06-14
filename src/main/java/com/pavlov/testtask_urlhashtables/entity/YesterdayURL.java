package com.pavlov.testtask_urlhashtables.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
public class YesterdayURL {

    @Id
    private String url;

    private String htmlCode;
}
