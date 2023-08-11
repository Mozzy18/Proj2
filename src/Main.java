import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args)
      throws FileNotFoundException, UnsupportedEncodingException {
    //ToDoList toDoList = new ToDoList("Test", LocalDate.now());
    ToDoList.addNewTask();
    ToDoList.readTask();
    runStartMenu();
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
}
