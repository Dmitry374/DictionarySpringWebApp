package com.dictionary.model.dto;

import com.dictionary.model.Translate;
import lombok.Data;

@Data
public class TranslateDto {

    private Long id;
    private String phrase;
    private String language;

    public static TranslateDto from(Translate translate) {
        TranslateDto translateDto = new TranslateDto();
        translateDto.setId(translate.getId());
        translateDto.setPhrase(translate.getPhrase());
        translateDto.setLanguage(translate.getLanguage());
        return translateDto;
    }
}
