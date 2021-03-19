package com.dictionary.controller;

import com.dictionary.model.Translate;
import com.dictionary.model.dto.TranslateDto;
import com.dictionary.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/translate")
public class TranslateController {

    private final TranslateService translateService;

    @Autowired
    public TranslateController(TranslateService translateService) {
        this.translateService = translateService;
    }

    @PostMapping
    public ResponseEntity<TranslateDto> addItem(@RequestBody final TranslateDto translateDto) {
        Translate translate = translateService.addTranslate(Translate.from(translateDto));
        return new ResponseEntity<>(TranslateDto.from(translate), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TranslateDto>> getTranslates() {
        List<Translate> translates = translateService.getTranslates();
        List<TranslateDto> translatesDto = translates.stream().map(TranslateDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(translatesDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<TranslateDto> getTranslate(@PathVariable final Long id) {
        Translate translate = translateService.getTranslate(id);
        return new ResponseEntity<>(TranslateDto.from(translate), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<TranslateDto> deleteTranslate(@PathVariable final Long id) {
        Translate translate = translateService.deleteTranslate(id);
        return new ResponseEntity<>(TranslateDto.from(translate), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<TranslateDto> editTranslate(@PathVariable final Long id,
                                                      @RequestBody final TranslateDto translateDto) {
        Translate editTranslate = translateService.editTranslate(id, Translate.from(translateDto));
        return new ResponseEntity<>(TranslateDto.from(editTranslate), HttpStatus.OK);
    }
}
