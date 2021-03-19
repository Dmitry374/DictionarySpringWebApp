package com.dictionary.model.exception;

import java.text.MessageFormat;

public class TranslateIsAlreadyAssignedException extends RuntimeException {

    public TranslateIsAlreadyAssignedException(final Long translateId, final Long wordId) {
        super(MessageFormat.format("Translate: {0} is already assigned to word: {1}", translateId, wordId));
    }
}
