package workshop;
import java.util.*;
import java.util.concurrent.*;
public final class Main {
    public static void main(String[] args) {
        System.out.println("Лабораторна №7. Lambda workshop (Java 8+)");
        System.out.println(" Завдання 1.1. Перші літери слів ");
        List<String> words = Arrays.asList("Java", "Streams", "Lambda", "Workshop");
        System.out.println("Слова: " + words);
        System.out.println("Результат: " + LambdaTasks.firstLetters(words));
        System.out.println("\n Завдання 1.2. Факторіал двома способами ");
        int n = 6;
        System.out.println("n=" + n + ", iterate/limit/reduce = " + LambdaTasks.factorialIterate(n));
        System.out.println("n=" + n + ", rangeClosed/reduce = " + LambdaTasks.factorialRange(n));
        System.out.println("\n Завдання 2.1. Мови команди ");
        List<Developer> team = Arrays.asList(
            new Developer("Анна", Arrays.asList("Java", "Kotlin", "SQL")),
            new Developer("Богдан", Arrays.asList("Java", "Python")),
            new Developer("Марія", Arrays.asList("Python", "JavaScript")));
        DeveloperService service = new DeveloperService();
        team.forEach(System.out::println);
        System.out.println("Усі мови без повторів: " + service.getAllLanguages(team));
        System.out.println("\n Завдання 2.2. Додаткові перевірені операції над моделлю ");
        System.out.println("Java-розробники: " + service.getDevelopersByLanguage(team, "Java"));
        System.out.println("Кількість розробників за мовою: " + service.countDevelopersByLanguage(team));
        System.out.println("Групування: " + service.groupDevelopersByLanguage(team));
        System.out.println("\n Завдання 3.1. Послідовно vs CompletableFuture ");
        List<Integer> input = Arrays.asList(1,2,3,4);
        long start = System.nanoTime();
        List<Integer> seq = ParallelTasks.sequential(input, 200);
        long sequentialMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        try {
            start = System.nanoTime();
            List<Integer> par = ParallelTasks.concurrent(input, 200, executor);
            long parallelMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
            System.out.println("Послідовно: " + seq + ", час: " + sequentialMs + " мс");
            System.out.println("Паралельно: " + par + ", час: " + parallelMs + " мс");
            System.out.println("Результати однакові: " + seq.equals(par));
        } finally { executor.shutdown(); }
        System.out.println("\nТести: запусти клас workshop.WorkshopTests.");
    }
}
