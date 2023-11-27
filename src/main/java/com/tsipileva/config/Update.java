package com.tsipileva.config;

import java.util.Map;
import java.util.HashMap;

/**
 * Класс, который хранит структуру обновления.
 *
 * @author Ципилева Ксения
 * @version 1.0
 */
public class Update {

    private String version;
    private Map<String, Map<String, String>> keys;

    /**
     * Конструктор класса по умолчанию.
     */
    public Update() {
        this.version = "";
        this.keys = new HashMap<>();
    }

    /**
     * Конструктор класса.
     *
     * @param version Версия обновления.
     * @param keys    Ключи, которые нужно обновить.
     */
    public Update(String version, Map<String, Map<String, String>> keys) {
        this.version = version;
        this.keys = keys;
    }

    /**
     * Установить новую версию.
     *
     * @param version Новая версия.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Установить новые ключи.
     *
     * @param keys Новые ключи.
     */
    public void setKeys(Map<String, Map<String, String>> keys) {
        this.keys = keys;
    }

    /**
     * Получить текущую версию обновления.
     *
     * @return возвращает версию обновления.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Получить ключи обновления.
     *
     * @return возвращает ключи обновления.
     */
    public Map<String, Map<String, String>> getKeys() {
        return keys;
    }
}
