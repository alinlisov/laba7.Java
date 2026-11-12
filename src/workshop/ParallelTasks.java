package workshop;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;
public final class ParallelTasks {
    private ParallelTasks() {}
    public static List<Integer> sequential(List<Integer> values, long delayMs) {
        return values.stream().map(v -> expensiveSquare(v, delayMs)).collect(Collectors.toList());
    }
    // Task 3.1:
    public static List<Integer> concurrent(List<Integer> values, long delayMs, Executor executor) {
        List<CompletableFuture<Integer>> tasks = values.stream()
                .map(v -> CompletableFuture.supplyAsync(() -> expensiveSquare(v, delayMs), executor))
                .collect(Collectors.toList());
        CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0])).join();
        return tasks.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
    private static int expensiveSquare(int value, long delayMs) {
        if (delayMs < 0) throw new IllegalArgumentException("delayMs must be >= 0");
        try { Thread.sleep(delayMs); }
        catch(InterruptedException e) { Thread.currentThread().interrupt(); throw new CompletionException(e); }
        return Math.multiplyExact(value, value);
    }
}
