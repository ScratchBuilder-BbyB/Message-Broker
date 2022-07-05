package utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutor {
  private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

  public static void submitTask(Runnable runnable){
    EXECUTOR_SERVICE.submit(runnable);
  }
}
