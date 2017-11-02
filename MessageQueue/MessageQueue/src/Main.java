import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Get input
        Scanner scanner = new Scanner(System.in);

        String[] workerNames = collectWorkerNamesFromInput(scanner);
        System.out.println();
        String[] initialTasks = collectInitialTasksFromInput(scanner);

        scanner.close();

        // Set up the queue
        Queue queue = new Queue();
        queue.addAll(initialTasks);

        // Do the work
        Worker[] workers = createWorkers(workerNames, queue);
        startWorkers(workers);
    }

    private static String[] collectInitialTasksFromInput(Scanner scanner) {
        System.out.println("What tasks should we start with? (comma separated list)");
        String workersRaw = scanner.nextLine();
        return workersRaw.split(",");
    }

    private static String[] collectWorkerNamesFromInput(Scanner scanner) {
        System.out.println("What should your workers be named? (comma separated list)");
        String workersRaw = scanner.nextLine();
        return workersRaw.split(",");
    }

    private static Worker[] createWorkers(String[] workerNames, Queue queue) {
        Worker[] workers = new Worker[workerNames.length];
        for (int i = 0; i < workerNames.length; i ++) {
            workers[i] = new Worker(workerNames[i], queue);
        }

        return workers;
    }

    private static void startWorkers(Worker[] workers) {
        for (Worker worker : workers) {
            worker.start();
        }
    }
}
