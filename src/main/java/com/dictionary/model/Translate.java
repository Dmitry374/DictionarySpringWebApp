package com.dictionary.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Translate")
public class Translate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phrase;
    private String language;

    public Translate() {
    }
}
