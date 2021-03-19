package com.dictionary.model.dto;

import com.dictionary.model.Translate;
import com.dictionary.model.Word;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WordDto {

    private Long id;
    private String phrase;
    private String language;
    private List<Translate> translates = new ArrayList<>();

    public static WordDto from(Word word) {
        WordDto wordDto = new WordDto();
        wordDto.setId(word.getId());
        wordDto.setPhrase(word.getPhrase());
        wordDto.setLanguage(word.getLanguage());
        wordDto.setTranslates(word.getTranslates());
        return wordDto;
    }
}
