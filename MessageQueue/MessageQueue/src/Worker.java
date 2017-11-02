import java.util.Random;

/**
 * Object to consume messages and maybe add more messages
 */
public class Worker extends Thread {

    private String name;
    private Queue queue;

    public Worker(String name, Queue queue) {
        this.name = name;
        this.queue = queue;
    }

    public void run() {
        String message;
        // Keep going until the queue is empty
        while ((message = queue.get()) != null) {
            System.out.println(this.name + " is twerkin hard on " + message);

            // IRL, messages would either come from different workers, or this
            // worker could queue up another similar job. However, for this v fake
            // example, just randomly decide whether to add another message.
            boolean needsMoreWork = (new Random()).nextBoolean();
            if (needsMoreWork) {
                queue.add("more " + message);
            }
        }
    }
}
