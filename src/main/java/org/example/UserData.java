package org.example;

import org.example.utils.MessageCreator;
import org.example.utils.MessageType;

import java.time.LocalDate;
import java.time.Period;

public class UserData {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String gender;
    private Integer age;

    private Integer calculateAge(LocalDate birthDate) {
        var currentDate = LocalDate.now();
        var period = Period.between(birthDate, currentDate);
        return period.getYears();
    }

    private void readGender() {
        if (patronymic.endsWith("овна") || patronymic.endsWith("евна")) {
             setGender("Женский");
        } else if (patronymic.endsWith("ович") || patronymic.endsWith("евич")) {
            setGender("Мужской");
        } else {
            setGender("ОПРЕДЕЛИТЬ_НЕ_УДАЛОСЬ");
        }
    }

    private String getAgeWithDeclension() {
        var ageStr = Integer.toString(age);
        int lastDigit = age % 10;
        int lastTwoDigits = age % 100;

        if (lastDigit == 1 && lastTwoDigits != 11) {
            return age + " год";
        } else if (lastDigit >= 2 && lastDigit <= 4 && (lastTwoDigits < 10 || lastTwoDigits >= 20)) {
            return age + " года";
        } else {
            return age + " лет";
        }
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(LocalDate date) {
        this.age = calculateAge(date);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        readGender();
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void getInitials() {
        MessageCreator.sendMessage(String.format("Инициалы: %s %s %s", firstName, lastName, patronymic), MessageType.NEXT);
        MessageCreator.sendMessage(String.format("Пол: %s", gender), MessageType.NEXT);
        MessageCreator.sendMessage(String.format("Возраст: %d", age), MessageType.NEXT);
    }
}
