import java.util.Scanner;

public enum MenuCommand {

  ADD(1, "Добавить новое задание в список"),
  DELETE(2, "Удалить задачу из списка"),
  CHANGE(3, "Изменить имя задачи и дату выполнения"),
  COMPLETE(4, "Изменить статус задачи"),
  READ(5, "Посмотреть список задач"),
  SORT(6, "Сортировать список задач по датам реализации"),
  COMPLETED(7, "Сортировать задачи на выполненные и нет"),
  EXIT(0, "Выйти из системы"),
  UNEXPECTED(10, "");


  private final int num;
  private final String s;

  MenuCommand(int num, String s) {
    this.num = num;
    this.s = s;
  }


  public static void menu() {
    System.out.println();
    System.out.println(" ═-═-═-═-═-═ Добро пожаловать в Список дел ═-═-═-═-═-═ ");

    System.out.println(" =====================  〚 МЕНЮ 〛 ====================\n");

    for (MenuCommand command : values()) {
      if (command != UNEXPECTED) {
        System.out.println(command.num + ". " + command.s);
      }
    }
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
            System.out.println("Вы выбрали: " + ADD.s);
            ToDoList.createTask();
            selectedCommand = ADD;
            break;

          case 2:
            System.out.println("Вы выбрали: " + DELETE.s);
            ToDoList.deleteTask();
            selectedCommand = DELETE;
            break;

          case 3:
            System.out.println("Вы выбрали: " + CHANGE.s);
            ToDoList.changeTask();
            selectedCommand = CHANGE;
            break;

          case 4:
            System.out.println("Вы выбрали: " + COMPLETE.s);
            ToDoList.taskIsComplete();
            selectedCommand = COMPLETE;
            break;

          case 5:
            System.out.println("Вы выбрали: " + READ.s);

            ToDoList.readTaskFromFile();
            selectedCommand = READ;
            break;

          case 6:
            System.out.println("Вы выбрали: " + SORT.s);
            ToDoList.sortTask();
            selectedCommand = SORT;
            break;

          case 7:
            System.out.println("Вы выбрали: " + COMPLETED.s);
            ToDoList.listFinalTask();
            selectedCommand = COMPLETED;
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
        System.err.println("Некорректный выбор. Введите корректное значение.\n");
        scanner.nextLine();
      }
    }
    return selectedCommand;
  }
}
