package com.dictionary.model.exception;

import java.text.MessageFormat;

public class TranslateNotFoundException extends RuntimeException {

    public TranslateNotFoundException(Long id) {
        super(MessageFormat.format("Could not find translate with id: {0}", id));
    }
}
