package com.dictionary.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phrase;
    private String language;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id")
    private List<Translate> translates = new ArrayList<>();

    public Word() {
    }

    public void addTranslate(Translate translate) {
        translates.add(translate);
    }

    public void removeTranslate(Translate translate) {
        translates.remove(translate);
    }
}
