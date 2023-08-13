import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoList {

  public static List<Task> allTasks;

  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_RESET = "\u001B[0m";
//  public static final String ANSI_GREEN = "\u001B[32m";

  public static void deleteTask() {
    setArrList();
    System.out.println("Введите имя задачи для удаления");
    Scanner scanner = new Scanner(System.in);
    String name = scanner.nextLine();
    for (Task task : allTasks) {
      if (task.getName().equals(name)) {
        allTasks.remove(task);
        System.out.println(task);
        addTaskToFile();
      }
    }
  }

  public static void taskIsComplete() {
    setArrList();
    System.out.println("Введите имя задачи");
    Scanner scanner = new Scanner(System.in);
    String name = scanner.nextLine();
    for (Task task : allTasks) {
      if(task.getName().equals(name)){
        System.out.println("Введите: 1(Завершенная) 2(В прогрессе)");
        Scanner scanner1 = new Scanner(System.in);
        int status = Integer.parseInt(scanner1.nextLine());
        if(status == 1)task.setComplete(true);
        else task.setComplete(false);
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
          Boolean comlete = Boolean.valueOf(data[3]);
          Task task = new Task(name, localDate, comlete);
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
    Scanner scanner = new Scanner(System.in);
    String name = scanner.nextLine().toLowerCase();
    System.out.println("Введите дату оканчания в формате 'yyyy.mm.dd'");
    Scanner scanner1 = new Scanner(System.in);
    String date = scanner1.nextLine();
    String[] finaleDate = date.split("\\.");
    if (finaleDate.length > 1) {
      int year = Integer.parseInt(finaleDate[0]);
      int month = Integer.parseInt(finaleDate[1]);
      int day = Integer.parseInt(finaleDate[2]);
      Task newTask = new Task(name, LocalDate.of(year, month, day), false);
      System.out.println(newTask.toString());
      allTasks.add(newTask);
      addTaskToFile();
    }
  }


  public static void readTaskFromFile() {
    setArrList();
    allTasks = setArrList();
    for (Task task : allTasks) {
      if (Boolean.valueOf(task.isComplete())) {
        System.out.println(ANSI_GREEN + task.getName() + " до " + task.getFinalDate() + ANSI_RESET);
      } else
        System.out.println(task.getName() + " до " + task.getFinalDate());

    }
  }

  public static void changeTask() {
    setArrList();
    System.out.println("Введите имя изменяемой задачи");
    Scanner scanner1 = new Scanner(System.in);
    String name = scanner1.nextLine().toLowerCase();
    for (Task task : allTasks) {
      if (task.getName().equals(name)) {
        System.out.println("Введите new имя изменяемой задачи");
        Scanner scanner2 = new Scanner(System.in);
        String newName = scanner2.nextLine();
        task.setName(newName.toLowerCase());
        System.out.println("Введите дату оканчания в формате 'yyyy.mm.dd'");
        Scanner scanner3 = new Scanner(System.in);
        String newDate = scanner2.nextLine();
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
