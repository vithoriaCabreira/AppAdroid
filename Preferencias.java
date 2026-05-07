package br.edu.utfpr.vithoriacabreira.minhasobras;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {
    private static final String NOME = "config_app";
    private static final String ORDENAR = "ordenar";

    public static void salvarOrdenacao(Context context, boolean ordenar) {
        SharedPreferences prefs = context.getSharedPreferences(NOME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(ORDENAR, ordenar).apply();
    }

    public static boolean getOrdenacao(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(NOME, Context.MODE_PRIVATE);
        return prefs.getBoolean(ORDENAR, false);
    }
}