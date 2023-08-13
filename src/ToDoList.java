import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ToDoList {

  public static List<Task> allTasks;
  public static Scanner scanner = new Scanner(System.in);

  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_RESET = "\u001B[0m";
//  public static final String ANSI_GREEN = "\u001B[32m";

  public static void deleteTask() {
    setArrList();
    System.out.println("Введите имя задачи для удаления");
    String name = scanner.nextLine();
    for (Task task : allTasks) {
      if (task.getName().equals(name)) {
        allTasks.remove(task);
        System.out.println(task);
        addTaskToFile();
      }
    }
  }

  public static void sortTask() {
    setArrList();
    Collections.sort(allTasks);
    for (Task task : allTasks) {
      if (Boolean.valueOf(task.isComplete())) {
        System.out.println(ANSI_GREEN + task.getName() + " до " + task.getFinalDate() + ANSI_RESET);
      } else {
        System.out.println(task.getName() + " до " + task.getFinalDate());
      }
    }
  }

  public static void listFinalTask() {
    setArrList();

    List<Task> finalAllTasks = new ArrayList<>();
    List<Task> noFinalAllTask = new ArrayList<>();
    for (Task task : allTasks) {
      if (Boolean.valueOf(task.isComplete())) {
        finalAllTasks.add(task);
      } else {
        noFinalAllTask.add(task);
      }
    }
    System.out.println("Список выполненных задач:");
    for (Task task : finalAllTasks) {
      System.out.println(ANSI_GREEN + task.getName() + " до " + task.getFinalDate() + ANSI_RESET);

    }
    System.out.println("\nСписок не выполненных задач:");
    for (Task task : noFinalAllTask) {
      System.out.println(task.getName() + " до " + task.getFinalDate());
    }
  }

  public static void taskIsComplete() {
    setArrList();
    System.out.println("Введите имя задачи");
    String name = scanner.nextLine();
    for (Task task : allTasks) {
      if (task.getName().equals(name)) {
        System.out.println("Введите: 1(Завершенная) 2(В прогрессе)");
        int status = Integer.parseInt(scanner.nextLine());
        if (status == 1) {
          task.setComplete(true);
        } else {
          task.setComplete(false);
        }
        addTaskToFile();
      }
    }
  }

  public static void addTaskToFile() {
    try (PrintWriter writer = new PrintWriter("res/list.csv", "UTF-8")) {
      for (Task task : allTasks) {
        writer.println(task.toString());
      }
    } catch (FileNotFoundException e) {
      System.out.println("Файл не найден: " + e.getMessage());
      throw new RuntimeException(e);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<Task> setArrList() {
    allTasks = new ArrayList<>();
    File inputFile = new File("res/list.csv");
    Scanner scanner;
    try {
      scanner = new Scanner(inputFile);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        if (data.length > 3) {
          String[] finaleDate = data[2].split("-");
          String name = data[0];
          int year = Integer.parseInt(finaleDate[0]);
          int month = Integer.parseInt(finaleDate[1]);
          int day = Integer.parseInt(finaleDate[2]);
          LocalDate localDate = LocalDate.of(year, month, day);
          Boolean complete = Boolean.valueOf(data[3]);
          Task task = new Task(name, localDate, complete);
          allTasks.add(task);
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
    }
    return allTasks;
  }

  public static void createTask() {
    setArrList();
    System.out.println("Введите имя новой задачи");
    String name = scanner.nextLine().toLowerCase();
    if (name.isEmpty()) {
      System.out.println("Имя задачи не может быть пустым");
      return;
    }
    System.out.println("Введите дату окончания в формате 'yyyy.mm.dd'");
    String date = scanner.nextLine();
    if (date.isEmpty()) {
      System.out.println("Дата окончания не может быть пустой");
      return;
    }
    try {
      String[] finaleDate = date.split("\\.");
      if (finaleDate.length > 1) {
      }
      int year = Integer.parseInt(finaleDate[0]);
      int month = Integer.parseInt(finaleDate[1]);
      int day = Integer.parseInt(finaleDate[2]);
      Task newTask = new Task(name, LocalDate.of(year, month, day), false);
      System.out.println(newTask.toString());
      allTasks.add(newTask);
      addTaskToFile();
    } catch (NumberFormatException e) {
      System.out.println(e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }


  public static void readTaskFromFile() {
    setArrList();
    allTasks = setArrList();
    for (Task task : allTasks) {
      if (Boolean.valueOf(task.isComplete())) {
        System.out.println(ANSI_GREEN + task.getName() + " до " + task.getFinalDate() + ANSI_RESET);
      } else {
        System.out.println(task.getName() + " до " + task.getFinalDate());
      }

    }
  }

  public static void changeTask() {
    setArrList();
    System.out.println("Введите имя изменяемой задачи");
    String name = scanner.nextLine().toLowerCase();
    for (Task task : allTasks) {
      if (task.getName().equals(name)) {
        System.out.println("Введите новое имя изменяемой задачи");
        String newName = scanner.nextLine();
        task.setName(newName.toLowerCase());
        System.out.println("Введите дату окончания в формате 'yyyy.mm.dd'");
        String newDate = scanner.nextLine();
        String[] finaleDate = newDate.split("\\.");
        if (finaleDate.length > 1) {
          int year = Integer.parseInt(finaleDate[0]);
          int month = Integer.parseInt(finaleDate[1]);
          int day = Integer.parseInt(finaleDate[2]);
          task.setFinalDate(LocalDate.of(year, month, day));
        }
        addTaskToFile();

      }
    }
  }
}
