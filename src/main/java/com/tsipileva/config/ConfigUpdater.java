package com.tsipileva.config;

import java.util.List;

/**
 * Данный класс обновляет конфигурационный и версионный файлы.
 *
 * @author Ципилева Ксения
 * @version 1.0
 */
public class ConfigUpdater {

    /**
     * Данный метод последовательно обновляет конфигурациооный
     * и версионный файлы.
     *
     * @param appVersion Версия приложения.
     * @param config     Конфигурация приложения.
     * @param updates    Список обновлений.
     */
    public static void allUpdatesConfig(Version appVersion, Config config, List<Update> updates) {
        updates.forEach(update -> {
            updateConfig(appVersion, config, update);
            System.out.println(config.getAllProperties());
        });
    }

    /**
     * Данный метод делает обновление конфигурациооного и версионного
     * файлов один раз и записывает обновления в соотвествующие файлы.
     *
     * @param appVersion Версия приложения.
     * @param config     Конфигурационния приложения.
     * @param update     Обновление приложения.
     */
    public static void updateConfig(Version appVersion, Config config, Update update) {
        update.getKeys().entrySet().forEach((entry) -> {
            switch (entry.getValue().get("update_type")) {
                case "add":
                    addConfig(config, entry.getKey(), entry.getValue().get("default_value"));
                    break;
                case "rename":
                    renameConfig(config, entry.getKey(), entry.getValue().get("value"));
                    break;
                case "delete":
                    deleteConfig(config, entry.getKey());
                    break;
            }
            config.writeFile();
            appVersion.getProperties().setProperty("app.version", update.getVersion());
            appVersion.writeFile();
        });

    }

    /**
     * Данный метод добавляет ключ и дефолтное значение в конфигурацию.
     *
     * @param config Конфигурация приложения.
     * @param key    Новый ключ конфигурации.
     * @param value  Дефолтное значение для ключа.
     */
    private static void addConfig(Config config, String key, String value) {
        if (config.getProperties().getProperty(key) == null) {
            config.getProperties().setProperty(key, value);
        }
    }

    /**
     * Данный метод переименовывает значение ключа конфигурации приложения.
     *
     * @param config Конфигурация приложения.
     * @param oldKey Старый ключ конфигурации.
     * @param newKey Новый ключ конфигурации.
     */
    private static void renameConfig(Config config, String oldKey, String newKey) {
        if (config.getProperties().getProperty(oldKey) != null) {
            config.getProperties().setProperty(newKey, config.getProperties().getProperty(oldKey));
            deleteConfig(config, newKey);
        }
    }

    /**
     * Данный метод удаляет ключ из конфигурации приложения.
     *
     * @param config Конфигурация приложения.
     * @param key    Ключ конфигурации.
     */
    private static void deleteConfig(Config config, String key) {
        if (config.getProperties().getProperty(key) != null) {
            config.getProperties().remove(key);
        }
    }
}
