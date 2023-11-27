package com.tsipileva.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитный класс для Version.
 *
 * @author Ципилева Ксения
 * @version 1.0
 */
public class VersionUtils {

    /**
     * Преобразование версии в список чисел.
     *
     * @param version Строка версии.
     * @return Возвращает преобразованную версию
     * в список чисел.
     */
    public static List<Integer> VersionConversion(String version) {
        List<Integer> newVersion = new ArrayList<>();
        try {
            newVersion.addAll(Arrays.asList(version.split("\\."))
                    .stream()
                    .map(Integer::valueOf)
                    .collect(Collectors.toList()));
        } catch (NumberFormatException e) {
            System.out.println("Номер версии " + version + " некорректен");
        }

        return newVersion;
    }

    /**
     * Метод для фильтрации, который сравнивает две версии.
     *
     * @param v1 Первая версия.
     * @param v2 Вторая версия.
     * @return Возвращает true, если первая версия больше второй,
     * иначе - false.
     */
    public static Boolean upperVersion(String v1, String v2) {
        return new VersionComparator().compare(v1, v2) > 0;
    }

    /**
     * Метод для фильтрации двух версий.
     *
     * @param v1 Первая версия.
     * @param v2 Вторая версия.
     * @return Возвращает true, если версии равны, иначе - false.
     */
    public static Boolean equalsVersion(String v1, String v2) {
        return new VersionComparator().compare(v1, v2) == 0;
    }
}
