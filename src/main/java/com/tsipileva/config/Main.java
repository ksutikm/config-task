package com.tsipileva.config;

/**
 * Основной класс приложения, который производит ее запуск.
 * Данное приложение реализует консольную программу, которая
 * обновляет конфигурациооный и версионный файлы.
 *
 * @author Ципилева Ксения
 * @version 1.0
 */
public class Main {

    /**
     * Основной метод, который запускает меню программы.
     *
     * @param args Не используется.
     */
    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.startMenu();
    }
}
