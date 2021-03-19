package com.dictionary.model;

import com.dictionary.model.dto.TranslateDto;
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

    public static Translate from(TranslateDto translateDto) {
        Translate translate = new Translate();
        translate.setPhrase(translateDto.getPhrase());
        translate.setLanguage(translateDto.getLanguage());
        return translate;
    }
}
