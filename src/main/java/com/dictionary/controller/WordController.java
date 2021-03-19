package com.dictionary.controller;

import com.dictionary.model.Word;
import com.dictionary.model.dto.WordDto;
import com.dictionary.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/word")
public class WordController {

    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping
    public ResponseEntity<WordDto> addWord(@RequestBody final WordDto wordDto) {
        Word word = wordService.addWord(Word.from(wordDto));
        return new ResponseEntity<>(WordDto.from(word), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WordDto>> getWords() {
        List<Word> words = wordService.getWords();
        List<WordDto> wordsDto = words.stream().map(WordDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(wordsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<WordDto> getWord(@PathVariable final Long id) {
        Word word = wordService.getWord(id);
        return new ResponseEntity<>(WordDto.from(word), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<WordDto> deleteWord(@PathVariable final Long id) {
        Word word = wordService.deleteWord(id);
        return new ResponseEntity<>(WordDto.from(word), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<WordDto> editWord(@PathVariable final Long id,
                                            @RequestBody final WordDto wordDto) {
        Word word = wordService.editWord(id, Word.from(wordDto));
        return new ResponseEntity<>(WordDto.from(word), HttpStatus.OK);
    }

    @PostMapping(value = "{wordId}/translates/{translateId}/add")
    public ResponseEntity<WordDto> addTranslateToWord(@PathVariable final Long wordId,
                                                      @PathVariable final Long translateId) {
        Word word = wordService.addTranslateToWord(wordId, translateId);
        return new ResponseEntity<>(WordDto.from(word), HttpStatus.OK);
    }

    @DeleteMapping(value = "{wordId}/translates/{translateId}/remove")
    public ResponseEntity<WordDto> removeTranslateFromWord(@PathVariable final Long wordId,
                                                           @PathVariable final Long translateId) {
        Word word = wordService.removeTranslateFromWord(wordId, translateId);
        return new ResponseEntity<>(WordDto.from(word), HttpStatus.OK);
    }
}
