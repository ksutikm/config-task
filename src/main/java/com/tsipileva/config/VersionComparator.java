package com.tsipileva.config;

import java.util.Comparator;
import java.util.List;

/**
 * Класс для сравнения двух версий.
 *
 * @author Ципилева Ксения
 * @version 1.0
 */
public class VersionComparator implements Comparator<String> {

    /**
     * Переопределение метода сравнения версий.
     *
     * @param o1 Первая версия.
     * @param o2 Вторая версия.
     * @return
     */
    @Override
    public int compare(String o1, String o2) {
        List<Integer> v1 = VersionUtils.VersionConversion(o1);
        List<Integer> v2 = VersionUtils.VersionConversion(o2);

        if (v1.get(0) != v2.get(0)) {
            return v1.get(0) - v2.get(0);
        }

        if (v1.get(1) != v2.get(1)) {
            return v1.get(1) - v2.get(1);
        }

        return v1.get(2) - v2.get(2);
    }


}
