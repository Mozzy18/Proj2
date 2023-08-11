import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.xml.crypto.Data;

public class ToDoList {

  private List <Task> tasks = new ArrayList<>();
  private static String fileName;// = Task.readFromCsv("res/list.csv").toString();
  SimpleDateFormat format = new SimpleDateFormat();

  public ToDoList() {
  }



  public static void addNewTaskToFile(Scanner scanner) throws ParseException {
    List<String> tasks;
    tasks =  setArrList();

    System.out.println("Добавьте новую задачу");
    String nameTask = scanner.nextLine();
     System.out.println("Дата реализации задачи в формате: yyyy.MM.dd ");
    String data = scanner.nextLine();
    //LocalDate finalData = LocalDate.parse(data);
    //Task task = new Task(nameTask, finalData)



    String[] finalData = data.split(".");
    int year = Integer.parseInt(finalData[0]);
    int month = Integer.parseInt(finalData[1]);
    int day = Integer.parseInt(finalData[2]);
    Task toDoList = new Task(nameTask,LocalDate.of(year,month,day));


    tasks.add(toDoList.toString());
    try (PrintWriter writer = new PrintWriter("res/list.csv", "UTF-8")) {
      for (String task: tasks){
        writer.println(task.toString());

      }
      writer.println(toDoList.toString());
    } catch (FileNotFoundException e) {
      System.out.println("Файл не найден: " + e.getMessage());
      throw new RuntimeException(e);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<String> setArrList() {
    List<String> allTasks = new ArrayList<>();
    File inputFile = new File("res/list.csv");
    Scanner scanner;
    try {
      scanner = new Scanner(inputFile);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        allTasks.add(line);
        // в зависимости от структуры нашего файла - пишем здесь проверку
        //для введенной строки или делим ее сабстрингом(задаем сепаратор и парсим дату)

      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
    }

    return allTasks;
  }

 public static void addNewTask()
     throws FileNotFoundException, UnsupportedEncodingException {
   ToDoList toDoList = new ToDoList();
   PrintWriter writer = new PrintWriter("res/list.csv", "UTF-8");
   writer.println(toDoList);
   writer.close();
 }

 //public static void addNewTaskToFile() {
 //  ToDoList toDoList = new ToDoList();
 //  try (PrintWriter writer = new PrintWriter("res/list.csv", "UTF-8")) {
 //    writer.println(toDoList.toString());
 //  } catch (FileNotFoundException e) {
 //    System.out.println("Файл не найден: " + e.getMessage());
 //    throw new RuntimeException(e);
 //  } catch (UnsupportedEncodingException e) {
 //    throw new RuntimeException(e);
 //  }
 //}


  public static void readTaskFromFile() {
    File inputFile = new File("res/list.csv");
    Scanner scanner;
    try {
      scanner = new Scanner(inputFile);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        System.out.println(line);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
    }
  }
  public void readTasksFromFile() {
    try (Scanner scanner = new Scanner(new File(fileName))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        Task task = (Task) Task.readFromCsv(line);
        tasks.add(task);
      }
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    }
  }


  public static void readTask() {

    File inputFile = new File("res/list.csv");
    Scanner scanner;
    try {
      scanner = new Scanner(inputFile);

    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());

      return;
    }
    System.out.println(scanner);
  }
}
