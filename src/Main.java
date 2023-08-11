import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws ParseException {

    Scanner scanner = new Scanner(System.in);
    readCommand(scanner);

  }

  public static void runStartMenu() {
    System.out.println();
    System.out.println(" ═-═-═-═-═-═ Добро пожаловать в Список дел ═-═-═-═-═-═ ");

    System.out.println(" =====================  〚 МЕНЮ 〛 ====================");
    System.out.println();
    List<String> allOptions = new ArrayList<>();

    allOptions.add(". Добавить новое задание в список");

    allOptions.add(". Удалить задание из списка");

    allOptions.add(". Изменить задание или дату выполнения");

    allOptions.add(". Выход");

    int num = 0;
    int choice;
    for (String option : allOptions) {
      num++;
      System.out.println(num + option);
    }
    System.out.println();
    System.out.println("Введите номер пункта меню: ");
    System.out.println();

    Scanner scanner = new Scanner(System.in);
    System.out.println(scanner);
    choice = scanner.nextInt();
    System.out.println(choice);

  }

  public static void readCommand(Scanner scanner) throws ParseException {

    boolean isRun = true;
    while (isRun) {
      runStartMenu();
      if (scanner.hasNext()) {
        int userChoice = scanner.nextInt();
        scanner.nextLine();
        switch (userChoice) {
          case 1:
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Добавьте новую задачу в список дел");

            ToDoList.addNewTaskToFile(scanner);
            ToDoList.readTask();
            Task.readFromCsv("res/list.csv");
            break;
          case 2:
            System.out.println("Вы собрались удалить задание из списка дел");
            ToDoList.readTaskFromFile();
            break;
          case 3:
            System.out.println("Вы собрались изменить задание или дату в списке дел");
            break;
          case 4:
            isRun = false;
            System.out.println("До скорой встречи");

            break;
          default:
            System.out.println("Некорректный выбор. Попробуйте снова.\n");
            break;
        }
      } else {
        System.err.println("no correct");

      }
    }
  }
}
