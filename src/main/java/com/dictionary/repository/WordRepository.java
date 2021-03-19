package com.dictionary.repository;

import com.dictionary.model.Word;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {

    @Query("from Word where language = :language")
    List<Word> getWordsByLanguage(@Param("language") String language);
}
