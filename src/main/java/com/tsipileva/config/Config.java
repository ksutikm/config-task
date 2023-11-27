package com.tsipileva.config;

/**
 * Данный класс насследуется от PropertiesAbstract, который
 * позволяет работать с конфигурационным файлом.
 *
 * @author Ципилева Ксения
 * @version 1.0
 */
public class Config extends PropertiesAbstract {

    /**
     * Конструктор класса Config.
     *
     * @param path Путь до файла config.properties.
     */
    public Config(String path) {
        super(path);
    }
}
