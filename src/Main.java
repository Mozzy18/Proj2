import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws ParseException {
    runStartMenu();
  }

  public static void runStartMenu() {
    System.out.println();
    System.out.println(" ═-═-═-═-═-═ Добро пожаловать в Список дел ═-═-═-═-═-═ ");

    System.out.println(" =====================  〚 МЕНЮ 〛 ====================");
    System.out.println();
    commandList();
    }

    public static void commandList(){
      List<String> allOptions = new ArrayList<>();
      allOptions.add(". Добавить новое задание в список");
      allOptions.add(". Удалить задачу из списка");
      allOptions.add(". Изменить имя задачи и дату выполнения");
      allOptions.add(". Изменить статус задачи");
      allOptions.add(". Посмотреть список задач");
      allOptions.add(". Выход");

      int num = 0;
      for (String option : allOptions) {
        num++;
        System.out.println(num + option);
      }
      System.out.println("Введите номер пункта меню: ");

      Scanner scanner = new Scanner(System.in);
      switch (scanner.nextInt()) {
        case 1:
          System.out.println("Добавьте новую задачу в список дел");
          ToDoList.createTask();
          break;
        case 2:
          System.out.println("Вы собрались удалить задание из списка дел");
          ToDoList.deleteTask();
          break;
        case 3:
          System.out.println("Измениить статус задачи");
          ToDoList.changeTask();
          break;
        case  4: ToDoList.taskIsComplete();
          break;
        case 5:
          System.out.println("Выводим список");
          ToDoList.readTaskFromFile();
          break;
        case 6:
          System.out.println("До скорой встречи");
          return;
        default:
          System.out.println("Некорректный выбор. Попробуйте снова.\n");
          break;
      }
    }
  }
