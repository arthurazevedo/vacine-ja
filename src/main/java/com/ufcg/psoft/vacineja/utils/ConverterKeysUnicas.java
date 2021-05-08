package com.ufcg.psoft.vacineja.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConverterKeysUnicas {

    public static String convert(String key) {
        return key.toUpperCase().trim();
    }

}
