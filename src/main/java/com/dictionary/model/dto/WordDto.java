package com.dictionary.model.dto;

import com.dictionary.model.Word;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class WordDto {

    private Long id;
    private String phrase;
    private String language;
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
