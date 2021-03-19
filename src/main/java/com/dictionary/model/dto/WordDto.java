package com.dictionary.model.dto;

import com.dictionary.model.Word;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class WordDto {

    private Long id;
    private String phrase;
    private String language;
    @JsonProperty("translates")
    private List<TranslateDto> translatesDto = new ArrayList<>();

    public static WordDto from(Word word) {
        WordDto wordDto = new WordDto();
        wordDto.setId(word.getId());
        wordDto.setPhrase(word.getPhrase());
        wordDto.setLanguage(word.getLanguage());
        wordDto.setTranslatesDto(word.getTranslates().stream().map(TranslateDto::from).collect(Collectors.toList()));
        return wordDto;
    }
}
