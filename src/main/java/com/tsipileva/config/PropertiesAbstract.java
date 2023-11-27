package com.tsipileva.config;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Абстрактный класс для файлов properties.
 * Отвечает за считывание и запись в файл.
 *
 * @author Ципилева Ксения
 * @version 1.0
 */
public abstract class PropertiesAbstract {
    private String path;
    private Properties properties;

    /**
     * Конструктор класса.
     * properties - Хранит ключ-значения конфигурационного файла.
     *
     * @param path Путь к файлу.
     */
    public PropertiesAbstract(String path) {
        this.path = path;
        this.properties = setProp();
    }

    /**
     * Метод, который загружает ключи-значения из файла.
     *
     * @return Возвращает ключи-значения.
     */
    private Properties setProp() {
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream(path)) {
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    /**
     * Метод для получения поля properties.
     *
     * @return Возвращает поле properties.
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Метод для возвращения всех ключей-значений.
     *
     * @return возвращает все коючи-значения.
     */
    public Map<String, String> getAllProperties() {
        Map<String, String> allProp = new HashMap<>();
        for (Object o : properties.keySet()) {
            String prop = (String) o;
            allProp.put(prop, properties.getProperty(prop));
        }

        return allProp;
    }

    /**
     * Метод для записи изменений в файл.
     */
    public void writeFile() {
        try {
            properties.store(new FileOutputStream(path), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
