package com.tsipileva.config;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для считывания обновлений.
 *
 * @author Ципилева Ксения
 * @version 1.0
 */
public class UpdateReader {

    private String path;
    private List<File> files;

    /**
     * Конструктор класса.
     *
     * @param path Путь до директории с файлами обвновлений.
     */
    public UpdateReader(String path) {
        this.path = path;
        this.files = getUpdateFiles(path);
    }

    /**
     * Получить файлы обновления.
     *
     * @param path Путь до директории с файлами обвновлений.
     * @return Метод возвращает список путей обновлений.
     */
    private List<File> getUpdateFiles(String path) {
        File dir = new File(path);
        return Arrays.asList(dir.listFiles());
    }

    /**
     * Получить список обновлений.
     *
     * @return Возвращает список обновлений.
     */
    public List<Update> getUpdates() {
        List<Update> updates = files.stream()
                .map(this::getUpdate)
                .collect(Collectors.toList());
        return updates;
    }

    /**
     * Метод, который считывает из файла обновление.
     *
     * @param file Путь до файла обновления.
     * @return Возвращает обновление.
     */
    private Update getUpdate(File file) {
        Gson gson = new Gson();
        Update update = new Update();

        try (Reader reader = new FileReader(file.getPath())) {
            update = gson.fromJson(reader, Update.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return update;
    }
}
