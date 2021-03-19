package com.dictionary.model.exception;

import java.text.MessageFormat;

public class WordNotFoundException extends RuntimeException {

    public WordNotFoundException(final Long id) {
        super(MessageFormat.format("Could not find word with id: {0}", id));
    }
}
