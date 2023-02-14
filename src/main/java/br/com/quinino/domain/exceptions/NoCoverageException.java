package br.com.quinino.domain.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NoCoverageException extends Throwable {

    private String message;
}
