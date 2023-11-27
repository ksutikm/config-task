package com.tsipileva.config;

/**
 * Данный класс насследуется от PropertiesAbstract, который
 * позволяет работать с версионным файлом.
 *
 * @author Ципилева Ксения
 * @version 1.0
 */
public class Version extends PropertiesAbstract {

    /**
     * Конструктор класса Version.
     *
     * @param path Путь до файла version.properties.
     */
    public Version(String path) {
        super(path);
    }
}
