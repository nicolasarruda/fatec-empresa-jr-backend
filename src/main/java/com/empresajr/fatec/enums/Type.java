package com.empresajr.fatec.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {

    USER(1),
    AUTHOR(2);

    private final int type;
}
