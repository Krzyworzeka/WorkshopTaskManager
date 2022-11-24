package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    static final String FILE_NAME = "tasks.csv";
    static final String FILE_DELIMITER = ",";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static final File file = new File(FILE_NAME);
    static String[][] tasks;

    public static void main(String[] args) {
        tasks = getTaskList();
        whatNext();
    }

    public static void printOption(String[] options) {
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option:" + ConsoleColors.RESET);
        for (String option : options) {
            System.out.println(option);
        }
    }

    public static void whatNext() {
        printOption(OPTIONS);
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            switch (scan.next()) {
                case "exit":
                    saveToFile(FILE_NAME, tasks);
                    System.out.println(ConsoleColors.RED + "See you!!!");
                    System.exit(0);
                    break;
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask();
                    break;
                case "list":
                    listTasks(tasks);
                    break;
                default:
                    System.out.println("Incorrect value!");
            }
            printOption(OPTIONS);
        }
    }

    public static void saveToFile(String fileName, String[][] tasksList) {
        Path path = Path.of(FILE_NAME);

        try {
            Files.write(path, new ArrayList<>());
            for (String[] line : tasksList) {
                Files.writeString(path, String.join(",", line) + "\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void addTask() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please add task description:");
        String desc = scan.nextLine();
        System.out.println("Please add task due date:");
        String date = scan.nextLine();
        System.out.println("Is your task important: ");
        String important = scan.nextLine();
        tasks = Arrays.copyOf(tasks, tasks.length+1);
        tasks[tasks.length -1] = new String[3];
        tasks[tasks.length -1][0] = desc;
        tasks[tasks.length -1][1] = date;
        tasks[tasks.length -1][2] = important;
    }

    public static void removeTask() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of tha task to killed");
        while (!scan.hasNextInt()) {
            System.out.println("Enter the number of the task to killed");
        }
        int id = scan.nextInt();
        if (id <= tasks.length && id > 0) {
            tasks = ArrayUtils.remove(tasks, id-1);
        } else System.out.println("No task with this number");

    }

    public static void listTasks(String[][] tab) {
        int i = 0;
        for (String[] line : tab) {
            i++;
            System.out.println(i + " : " + String.join(" ", line));
        }
    }

    public static String[][] getTaskList() {
        String[][] tasks = new String[0][3];
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String fileLine;
            while ((fileLine = br.readLine()) != null) {
                String[] values = fileLine.split(FILE_DELIMITER);
                tasks = Arrays.copyOf(tasks, tasks.length + 1);
                tasks[tasks.length - 1] = values.clone();

            }
        } catch (IOException e) {
            e.getMessage();
        }

        return tasks;
    }

}
