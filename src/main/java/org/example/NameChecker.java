package org.example;

import org.example.exceptions.DateException;
import org.example.exceptions.InitialSizeException;
import org.example.utils.MessageCreator;
import org.example.utils.MessageType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class NameChecker {

    public void checkUserData() {
        var in = new Scanner(System.in);
        var userData = getValidInitials(in);
        readDate(in, userData);
        userData.getInitials();
    }


    private void readDate(Scanner in, UserData userData) {
        while (true) {
            MessageCreator.sendMessage("Введите дату рождения в формате dd.MM.yyyy: ", MessageType.NEXT);
            try {
                userData.setAge(getBirthDate(in.nextLine()));
                break;
            }
            catch (Exception e) {
                MessageCreator.sendMessage(String.format("Вы получили исключение: %s", e.getMessage()), MessageType.NEXT);
                MessageCreator.sendMessage("Попробуйте снова!", MessageType.NEXT);
            }
        }
    }



    private LocalDate getBirthDate(String inputDate) {
        try {
            var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(inputDate, formatter);
        }
        catch (Exception e) {
            throw new DateException();
        }
    }


    private UserData getValidInitials(Scanner in) {
        while (true) {
            MessageCreator.sendMessage("Введите ваши инициалы (ФИО), разделите данные пробелом. Например Иванов Артем Игоревич.", MessageType.NEXT);
            try {
                return getValidUserData(in.nextLine());
            }
            catch (Exception e) {
                MessageCreator.sendMessage(String.format("Вы получили исключение: %s", e.getMessage()), MessageType.NEXT);
                MessageCreator.sendMessage("Попробуйте снова!", MessageType.NEXT);
            }
        }
    }


    private UserData getValidUserData(String inputData) {
        if (inputData.split(" ").length != 3) {
            throw new InitialSizeException();
        }
        return createUserData(inputData.split(" "));
    }

    private UserData createUserData(String[] nameParts) {
        var userData = new UserData();
        userData.setFirstName(nameParts[1]);
        userData.setLastName(nameParts[0]);
        userData.setPatronymic(nameParts[2]);
        return userData;
    }
}
