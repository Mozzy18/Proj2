import java.util.Scanner;

public enum MenuCommand {
  EXIT("Выйти из системы"),
  ADD("Добавить новое задание в список"),
  DELETE("Удалить задачу из списка"),
  CHANGE("Изменить имя задачи и дату выполнения"),
  COMPLETE("Изменить статус задачи"),
  READ("Посмотреть список задач"),
  UNEXPECTED("");


  private final String s;

  MenuCommand(String s) {
    this.s = s;
  }


  public String getS() {
    return s;
  }

  public static void menu() {
    System.out.println();
    System.out.println(" ═-═-═-═-═-═ Добро пожаловать в Список дел ═-═-═-═-═-═ ");

    System.out.println(" =====================  〚 МЕНЮ 〛 ====================\n");
    System.out.println("1. Добавить новое задание в список");
    System.out.println("2. Удалить задачу из списка");
    System.out.println("3. Изменить имя задачи и дату выполнения");
    System.out.println("4. Изменить статус задачи");
    System.out.println("5. Посмотреть список задач");
    System.out.println("0. Выход");
  }

  public static MenuCommand commandList() {

    Scanner scanner = new Scanner(System.in);
    boolean isRun = true;
    MenuCommand selectedCommand = null;
    while (isRun) {
      menu();
      System.out.println("Введите номер пункта меню: ");
      if (scanner.hasNextInt()) {
        int command = scanner.nextInt();
        scanner.nextLine();
        System.out.println("------------------------------------------------------");

        switch (command) {
          case 1:
            System.out.println("Вы выбрали: ");
            System.out.println("добавить новое задание в список");
            ToDoList.createTask();
            selectedCommand = ADD;
            break;

          case 2:
            System.out.println("Вы выбрали: ");
            System.out.println("удалить задачу из списка");
            ToDoList.deleteTask();
            selectedCommand = DELETE;
            break;

          case 3:
            System.out.println("Вы выбрали: ");
            System.out.println("изменить имя задачи и дату выполнения");
            ToDoList.changeTask();
            selectedCommand = CHANGE;
            break;

          case 4:
            System.out.println("Вы выбрали: ");
            System.out.println("изменить статус задачи");
            ToDoList.taskIsComplete();
            selectedCommand = COMPLETE;
            break;

          case 5:
            System.out.println("Вы выбрали: ");
            System.out.println("Посмотреть список задач");
            ToDoList.readTaskFromFile();
            selectedCommand = READ;
            break;

          case 0:
            System.out.println("До скорой встречи");
            selectedCommand = EXIT;
            isRun = false;
            break;

          default:
            selectedCommand = UNEXPECTED;
            break;

        }
      } else {
        System.err.println("Некорректный выбор. Попробуйте снова.\n");
        scanner.nextLine();
      }
    }
    return selectedCommand;
  }
}
