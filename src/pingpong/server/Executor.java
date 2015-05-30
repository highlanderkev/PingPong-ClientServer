package pingpong.server;

import pingpong.debug.Debug;

import java.util.concurrent.*;

/**
 * An Executor class for executing tasks.
 * @author Alan Jeffrey
 * @version 1.0.1
 */

public interface Executor {

    /**
     * Try to execute a given task.
     * The task will be run, if system resources permit.
     * If the system is too busy, then the task will be cancelled.
     * @param command the task to execute
     */
    public void execute (Runnable command);

    /**
     * An executor.
     */
    public Executor singleton = new ExecutorImpl ();

}

/**
 * Enhancements to implementation by Kevin Westropp
 */
class ExecutorImpl extends ThreadPoolExecutor implements Executor  {

    // Extends ThreadPoolExecutor with Custom Business Logic
    private final static int CORE_POOL_SIZE = 10;
    private final static int MAX_POOL_SIZE = 50;
    private final static long KEEP_ALIVE_TIME = 30;
    private final static TimeUnit SECONDS_UNIT = TimeUnit.SECONDS;
    private final static BlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<Runnable>(100, true);

    public ExecutorImpl(){
        super(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, SECONDS_UNIT, WORK_QUEUE);
        super.setRejectedExecutionHandler(new DiscardPolicy());
    }

    @Override
    public void execute (Runnable command) {
        Debug.out.println ("Executor.execute: Starting");

        /*final Thread thread = new Thread (command);
        thread.start ();*/
        super.execute(command);

        Debug.out.println("Executor.execute: Returning");
    }

}
