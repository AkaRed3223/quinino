package br.com.quinino.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Plan {

    private String name;
    private int minutes;
}
