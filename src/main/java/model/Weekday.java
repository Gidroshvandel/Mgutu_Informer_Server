package model;

public enum Weekday {

    Monday("Понедельник"),
    Tuesday("Вторник"),
    Wednesday("Среда"),
    Thursday("Четверг"),
    Friday("Пятница"),
    Saturday("Суббота"),
    Sunday("Воскресение");

    private String name;

    Weekday(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

