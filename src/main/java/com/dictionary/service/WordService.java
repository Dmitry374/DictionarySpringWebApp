package com.dictionary.service;

import com.dictionary.model.Translate;
import com.dictionary.model.Word;
import com.dictionary.model.exception.WordNotFoundException;
import com.dictionary.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WordService {

    private final WordRepository wordRepository;
    private final TranslateService translateService;

    @Autowired
    public WordService(WordRepository wordRepository, TranslateService translateService) {
        this.wordRepository = wordRepository;
        this.translateService = translateService;
    }

    public Word addWord(Word word) {
        return wordRepository.save(word);
    }

    public List<Word> getWords() {
        return StreamSupport
                .stream(wordRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Word getWord(Long id) {
        return wordRepository.findById(id).orElseThrow(() ->
                new WordNotFoundException(id));
    }

    public Word deleteWord(Long id) {
        Word word = getWord(id);
        wordRepository.delete(word);
        return word;
    }

    public Word editWord(Long id, Word word) {
        Word wordToEdit = getWord(id);
        wordToEdit.setPhrase(word.getPhrase());
        wordToEdit.setLanguage(word.getLanguage());
        return wordToEdit;
    }

    public Word addTranslateToWord(Long wordId, Long translateId) {
        Word word = getWord(wordId);
        Translate translate = translateService.getTranslate(translateId);
        word.addTranslate(translate);
        return word;
    }

    public Word removeTranslateFromWord(Long wordId, Long translateId) {
        Word word = getWord(wordId);
        Translate translate = translateService.getTranslate(translateId);
        word.removeTranslate(translate);
        return word;
    }
}
