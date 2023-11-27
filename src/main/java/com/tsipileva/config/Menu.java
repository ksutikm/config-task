package com.tsipileva.config;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Данный класс отвечает за меню приложения.
 *
 * @author Ципилева Ксения
 * @version 1.0
 */
public class Menu {

    private Scanner scanner;
    private String menu;

    /**
     * Константы путей для конфигурационного, версионного файлов и обновлений.
     */
    final String PATH_UPDATES = "src/main/resources/Updates";
    final String PATH_CONFIG = "src/main/resources/config.properties";
    final String PATH_VERSION = "src/main/resources/version.properties";

    /**
     * Конструктор класса Menu.
     * <p>
     * scanner - Отвечает за ввод пользователя.
     * menu - Создает меню для пользователя.
     */
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.menu = createMenu();
    }

    /**
     * Метод, который запускает работу меню.
     */
    public void startMenu() {
        System.out.println(menu);

        while (true) {
            int command = -1;

            try {
                System.out.print("Введите номер команды: ");
                command = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Завершение приложения");
                System.exit(0);
            }

            switch (command) {
                case 1:
                    System.out.println(menu);
                    break;
                case 2:
                    printCurrentVersion();
                    break;
                case 3:
                    printAvailableUpdates();
                    break;
                case 4:
                    System.out.print("Введите версию в формате \"число.число.число\" до которой хотите обновиться: ");
                    scanner.nextLine();
                    String version = scanner.nextLine();
                    if (version.matches("[0-9]+\\.[0-9]+\\.[0-9]+")) {
                        printUpdateToVersion(version);
                    } else {
                        System.out.println("Номер версии " + version + " некорректен");
                    }
                    break;
                case 5:
                    printUpdateToAllVersion();
                    break;
                default:
                    System.out.println("Завершение приложения");
                    System.exit(0);
            }
        }
    }

    /**
     * Метод, которыый печатает в консоль текущую версию программы.
     */
    private void printCurrentVersion() {
        Version appVersion = new Version(PATH_VERSION);
        System.out.println("Текущая версия приложения: " + appVersion.getProperties().getProperty("app.version"));
    }

    /**
     * Метод, который печатает в консоль доступные версии обновления.
     */
    private void printAvailableUpdates() {
        Version appVersion = new Version(PATH_VERSION);
        StringBuilder answer = new StringBuilder()
                .append("Доступные версии для обновления:");
        StringBuilder versions = new StringBuilder();
        UpdateReader updateReader = new UpdateReader(PATH_UPDATES);
        List<String> updateVersions = UpdateUtils.getUpdateVersions(appVersion.getProperties().getProperty("app.version"),
                updateReader.getUpdates());
        if (updateVersions.size() == 0) {
            System.out.println("Нет версий для обновления");
        } else {
            updateVersions.stream()
                    .forEach(updateVersion -> versions.append("\nВерсия " + updateVersion));
            System.out.println(answer.append(versions).toString());
        }
    }

    /**
     * Метод, который позволяет обновлять приложение до определенной версии.
     *
     * @param version Версия, до которой нужно обновить приложение.
     */
    private void printUpdateToVersion(String version) {
        Version appVersion = new Version(PATH_VERSION);
        Config config = new Config(PATH_CONFIG);
        UpdateReader updateReader = new UpdateReader(PATH_UPDATES);

        List<Update> updates = updateReader.getUpdates().stream()
                .filter(u -> VersionUtils.upperVersion(u.getVersion(), appVersion.getProperties().getProperty("app.version")))
                .filter(u -> VersionUtils.equalsVersion(u.getVersion(), version))
                .collect(Collectors.toList());

        if (!updates.isEmpty()) {
            ConfigUpdater.allUpdatesConfig(appVersion, config, updates);
            System.out.println("Обновилось успешно до версии " + version);
        } else {
            System.out.println("Версия не доступна для обновления");
        }
    }

    /**
     * Метод, который обновляет приложение до последней версии.
     */
    private void printUpdateToAllVersion() {
        Version appVersion = new Version(PATH_VERSION);
        Config config = new Config(PATH_CONFIG);
        UpdateReader updateReader = new UpdateReader(PATH_UPDATES);

        List<Update> updates = updateReader.getUpdates().stream()
                .filter(u -> VersionUtils.upperVersion(u.getVersion(), appVersion.getProperties().getProperty("app.version")))
                .collect(Collectors.toList());

        if (!updates.isEmpty()) {
            ConfigUpdater.allUpdatesConfig(appVersion, config, updates);
            System.out.println("Успешно обвновилось до последней версии");
        } else {
            System.out.println("Версия приложения уже актуальная");
        }
    }

    /**
     * Метод, который состоавляет меню приложения.
     *
     * @return
     */
    private String createMenu() {
        return new StringBuilder()
                .append("Список команд:\n")
                .append("1 - Меню приложения\n")
                .append("2 - Текущая версия приложения\n")
                .append("3 - Доступные обновления\n")
                .append("4 - Обновиться до определенной версии\n")
                .append("5 - Обновиться до последней версии\n")
                .append("Другое - Выход")
                .toString();
    }
}
