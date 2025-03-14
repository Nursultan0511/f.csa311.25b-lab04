package edu.cmu.cs.cs214.rec02;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
 
 
/**
 * TODO:
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method
 * using mQueue = new LinkedIntQueue();
 *
 * 2.
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {
 
    private IntQueue mQueue;
    private List<Integer> testList;
 
    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // Use the ArrayIntQueue for testing
        mQueue = new ArrayIntQueue();  // Use for ArrayIntQueue
        // mQueue = new LinkedIntQueue();  // Uncomment to test LinkedIntQueue
        testList = new ArrayList<>(List.of(1, 2, 3));
    }
 
    @Test
    public void testIsEmpty() {
        assertTrue(mQueue.isEmpty());
    }
 
    @Test
    public void testNotEmpty() {
        mQueue.enqueue(5);
        assertFalse(mQueue.isEmpty());
    }
 
    @Test
    public void testPeekEmptyQueue() {
        assertNull(mQueue.peek());  // Queue is empty, so peek should return null
    }
 
    @Test
    public void testPeekNoEmptyQueue() {
        mQueue.enqueue(10);
        assertEquals(Integer.valueOf(10), mQueue.peek());  // Should return the first enqueued element
    }
 
    @Test
    public void testEnqueue() {
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }
 
    @Test
    public void testDequeue() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        assertEquals(Integer.valueOf(1), mQueue.dequeue());
        assertEquals(Integer.valueOf(2), mQueue.dequeue());
        assertNull(mQueue.dequeue());  // After dequeueing all elements, it should return null
    }
 
    @Test
    public void testEnsureCapacity() {
        // Test for the case when the queue size exceeds the initial capacity
        for (int i = 0; i < 15; i++) {
            mQueue.enqueue(i);
        }
        assertEquals(15, mQueue.size());  // Expect the size to be 15 after adding more than initial capacity
    }
 
    @Test
    public void testClear() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.clear();
        assertTrue(mQueue.isEmpty());  // After clear, the queue should be empty
        assertNull(mQueue.peek());  // Peek should return null as queue is cleared
    }
 
 
    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");
 
            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }
 
            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }
 
 
}