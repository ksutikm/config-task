package com.tsipileva.config;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитный класс для Update.
 *
 * @author Ципилева Ксения
 * @version 1.0
 */
public class UpdateUtils {

    /**
     * Получить версии из обвновлений.
     *
     * @param appVersion Текущая версия обновления.
     * @param updates    Список обновлений.
     * @return Возвращает список версий из обновлений.
     */
    public static List<String> getUpdateVersions(String appVersion, List<Update> updates) {
        return updates.stream()
                .map(Update::getVersion)
                .filter(version -> !version.equals(""))
                .filter(version -> VersionUtils.upperVersion(version, appVersion))
                .sorted(new VersionComparator())
                .collect(Collectors.toList());
    }
}
