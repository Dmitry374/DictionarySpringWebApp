package com.dictionary.service;

import com.dictionary.model.Translate;
import com.dictionary.model.exception.TranslateNotFoundException;
import com.dictionary.repository.TranslateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TranslateService {

    private final TranslateRepository translateRepository;

    @Autowired
    public TranslateService(TranslateRepository translateRepository) {
        this.translateRepository = translateRepository;
    }

    public Translate addTranslate(Translate translate) {
        return translateRepository.save(translate);
    }

    public List<Translate> getTranslates() {
        return StreamSupport
                .stream(translateRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Translate getTranslate(Long id) {
        return translateRepository.findById(id).orElseThrow(() ->
                new TranslateNotFoundException(id));
    }

    public Translate deleteTranslate(Long id) {
        Translate translate = getTranslate(id);
        translateRepository.delete(translate);
        return translate;
    }

    @Transactional
    public Translate editTranslate(Long id, Translate translate) {
        Translate translateToEdit = getTranslate(id);
        translateToEdit.setPhrase(translate.getPhrase());
        translateToEdit.setLanguage(translate.getLanguage());
        return translateToEdit;
    }
}
