package com.dictionary.repository;

import com.dictionary.model.Translate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslateRepository extends CrudRepository<Translate, Long> {
}
