import java.util.Scanner;

public class Encripter {
    public static void main(String[] args) {
        System.out.println("Шифрование персональных данных");
        Scanner inputData = new Scanner(System.input.data);

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
        }
    }
}
