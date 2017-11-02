import java.util.*;

/**
 * Queue wrapper for a linked list, so that read/writes to the queue
 * are synchronized between threads, and only allowing gets from the
 * front and additions to the back.
 */
public class Queue {
    // Even though this is final, elements within the object can be modified.
    // However, this variable cannot be reassigned to another object.
    private final List<String> QUEUE;

    public Queue() {
        // Create a list that is thread safe (ie you can place a lock on it),
        // backed by a linked list.
        this.QUEUE = Collections.synchronizedList(new LinkedList<String>());
    }

    /**
     * Reads should occur synchronously so that the same message is
     * not read multiple times.
     * @return The next message to be processed (the oldest or first one added)
     */
    public String get() {
        synchronized (QUEUE) {
            if (QUEUE.size() > 0) {
                return QUEUE.remove(0);
            }
            return null;
        }
    }

    /**
     * Writes should occur synchronously to  guarantee that a
     * message is not accidentally overwritten.
     * @param message The next message to add to the queue, placed at the end
     */
    public void add(String message) {
        synchronized (QUEUE) {
            QUEUE.add(message);
        }
    }

    /**
     * Writes should occur synchronously to  guarantee that a
     * message is not accidentally overwritten.
     *
     * NOTE: I'm not using this in a thread in this implementation,
     * but if I were, this would be required.
     * @param messages An array of messages to be placed at the end of the queue
     */
    public void addAll(String[] messages) {
        synchronized (QUEUE) {
            QUEUE.addAll(Arrays.asList(messages));
        }
    }
}
