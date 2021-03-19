package com.dictionary.service;

import com.dictionary.model.Translate;
import com.dictionary.model.Word;
import com.dictionary.model.exception.TranslateIsAlreadyAssignedException;
import com.dictionary.model.exception.WordNotFoundException;
import com.dictionary.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public List<Word> getWords(String language, String phrase) {
        return new ArrayList<>(wordRepository.getWordsByLanguage(language, phrase));
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

    @Transactional
    public Word editWord(Long id, Word word) {
        Word wordToEdit = getWord(id);
        wordToEdit.setPhrase(word.getPhrase());
        wordToEdit.setLanguage(word.getLanguage());
        return wordToEdit;
    }

    @Transactional
    public Word addTranslateToWord(Long wordId, Long translateId) {
        Word word = getWord(wordId);
        Translate translate = translateService.getTranslate(translateId);
        if (Objects.nonNull(translate.getWord())) {
            throw new TranslateIsAlreadyAssignedException(translateId, translate.getWord().getId());
        }
        word.addTranslate(translate);
        return word;
    }

    @Transactional
    public Word removeTranslateFromWord(Long wordId, Long translateId) {
        Word word = getWord(wordId);
        Translate translate = translateService.getTranslate(translateId);
        word.removeTranslate(translate);
        return word;
    }
}
