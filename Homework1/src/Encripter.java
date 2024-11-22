//public class Efectiveness_Agent extends Agent {
//    public static void main(String[] args) {
//        JFrame window = new JFrame("Рентабельность работы предприятия");
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setVisible(true);
//    }
//}

import java.util.Scanner;

public class Encripter {
    public static void main(String[] args) {
        System.out.println("Шифрование персональных данных");
        Scanner inputData = new Scanner(System.in);

        System.out.println("Press F to pay respect, Press Z to get respect");

        int inputNumber = inputData.nextInt();

        if (inputNumber == 1) {
            System.out.println("Введите всё");
            String key = inputData.next();
            System.out.println("Введите Дату/ИмяФамилию/КодТоварнойПродукции");
            String UnitCode = inputData.next();
            String encyptUnitCode = EncryptCode(UnitCode, key);
            System.out.println("Сообщение после шифрования: " + encyptUnitCode);
        }
        else if (inputNumber == 2) {
            System.out.println("Введите Дату/ИмяФамилию/КодТоварнойПродукции");
            String key = inputData.next();
            System.out.println("Введите код транзакции");
            String decriptedUnitCode = inputData.next();
            String decryptTransaction = DecryptCode(decriptedUnitCode, key);
            System.out.println("Сообщение после дешифрования: "+ decryptTransaction);
        }

        else {
            System.out.println("Ошибка ввода");
        }
    }

    public static String EncryptCode(String code, String key) {
        StringBuilder encryptUnitCode = new StringBuilder();
        int keyLength = key.length();
        for (int i = 0; i < code.length(); ++i) {
            char l = code.charAt(i);
            char nextChar = (char)(l + key.charAt(i % keyLength));
            encryptUnitCode.append(nextChar);
        }
        return encryptUnitCode.toString();
    }

    public static String DecryptCode(String code, String key) {
        StringBuilder decryptUnitCode = new StringBuilder();
        int keyLength = key.length();
        for (int i = 0; i < code.length(); ++i) {
            char l = code.charAt(i);
            char nextChar = (char)(l - key.charAt(i % keyLength));
            decryptUnitCode.append(nextChar);
        }
        return decryptUnitCode.toString();
    }
}

