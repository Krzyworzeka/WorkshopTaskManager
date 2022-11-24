package pl.coderslab;

public class TaskManager {

    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};

    public static void main(String[] args) {
        String[][] tasks;
        tasks = getTaskList();

        printOption(OPTIONS);
    }

    public static void printOption(String[] options) {
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option:" + ConsoleColors.RESET);
        for (String option: options){
            System.out.println(option);
        }
    }

    public static String[][] getTaskList(){
        String[][] tasks;
        return tasks;
    }
}
