package workshop;
import java.util.*;
import java.util.stream.*;
import java.math.BigInteger;
public final class LambdaTasks {
    private LambdaTasks() {}
    // Task 1.1
    public static String firstLetters(List<String> words) {
        Objects.requireNonNull(words);
        StringBuilder builder = new StringBuilder();
        words.stream().filter(Objects::nonNull).filter(w -> !w.isEmpty())
                .forEach(w -> builder.append(w.charAt(0)));
        return builder.toString();
    }
    // Task 1.2
    public static long factorialIterate(int n) {
        checkFactorialArg(n);
        return LongStream.iterate(1, x -> x + 1).limit(n)
                .reduce(1L, Math::multiplyExact);
    }
    // Task 1.2, option 2
    public static long factorialRange(int n) {
        checkFactorialArg(n);
        return LongStream.rangeClosed(1, n).reduce(1L, Math::multiplyExact);
    }
    // Optional:
    public static BigInteger factorialBig(int n) {
        if(n < 0) throw new IllegalArgumentException("n must be >= 0");
        return IntStream.rangeClosed(1, n).mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
    private static void checkFactorialArg(int n) {
        if(n < 0 || n > 20) throw new IllegalArgumentException("long factorial requires 0 <= n <= 20");
    }
}
