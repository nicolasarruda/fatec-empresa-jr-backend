package com.empresajr.fatec.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {

    NEOTECH(1),
    ADS(2),
    EVENTOS(3),
    GESTAO_AMBIENTAL(4),
    GESTAO_EMPRESARIAL(5),
    GTI(6),
    LOGISTICA(7),
    SISTEMAS_EMBARCADOS(8);

    private final int type;
}
