package com.ufcg.psoft.vacineja.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtil {

    public static String converterKeysUnicas(String key) {
        return key.toUpperCase().trim();
    }

    public static String paraStringDeNumeros(String string) {
        return string.replaceAll("[^\\d]", "").trim();
    }

}
