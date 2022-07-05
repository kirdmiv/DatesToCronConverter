package com.ivanov.kirill;

public class DatesToCronConverter {
    DatesToCronConverter() {
    }

    String convert(Iterable<String> dates) {
        return "";
    }

    String getImplementationInfo() {
        String fio = "Иванов Кирилл Дмитриевич";
        String className = DatesToCronConverter.class.getName();
        String packageName = DatesToCronConverter.class.getPackageName();
        String githubLink = "https://github.com/kirdmiv";
        return String.join("\n", fio, className, packageName, githubLink);
    }
}
