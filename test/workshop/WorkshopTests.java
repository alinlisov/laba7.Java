package workshop;
import java.util.*;
import java.util.concurrent.*;
public final class WorkshopTests {
    private static int passed=0;
    private static void eq(Object expected,Object actual,String name){
        if(!Objects.equals(expected,actual)) throw new AssertionError(name+": expected="+expected+", actual="+actual);
        System.out.println("PASS: "+name); passed++;
    }
    private static void fails(Runnable action,String name){
        try {action.run(); throw new AssertionError("No exception for "+name);}
        catch(IllegalArgumentException expected){System.out.println("PASS: "+name);passed++;}
    }
    public static void main(String[] args){
        eq("JSLW",LambdaTasks.firstLetters(Arrays.asList("Java","Streams","Lambda","Workshop")),"1.1 standard");
        eq("",LambdaTasks.firstLetters(Collections.emptyList()),"1.1 empty list");
        eq("AB",LambdaTasks.firstLetters(Arrays.asList("Apple", "", null,"Banana")),"1.1 skip empty/null strings");
        eq(1L,LambdaTasks.factorialIterate(0),"1.2 iterate 0!");
        eq(1L,LambdaTasks.factorialRange(0),"1.2 range 0!");
        eq(720L,LambdaTasks.factorialIterate(6),"1.2 iterate 6!");
        eq(720L,LambdaTasks.factorialRange(6),"1.2 range 6!");
        eq(2432902008176640000L,LambdaTasks.factorialRange(20),"1.2 limit 20!");
        fails(()->LambdaTasks.factorialIterate(21),"1.2 reject overflow");
        fails(()->LambdaTasks.factorialRange(-1),"1.2 reject negative");
        eq("51090942171709440000",LambdaTasks.factorialBig(21).toString(),"1.2 BigInteger 21!");
        DeveloperService svc = new DeveloperService();
        List<Developer> team=Arrays.asList(new Developer("A",Arrays.asList("Java","SQL","Java")),
               new Developer("B",Arrays.asList("Python","Java")),new Developer("C",Arrays.asList("Python")));
        eq(Arrays.asList("Java","SQL","Python"),svc.getAllLanguages(team),"2.1 flatten distinct order");
        eq(Collections.emptyList(),svc.getAllLanguages(Collections.emptyList()),"2.1 empty team");
        eq(Arrays.asList("A","B"),svc.getDevelopersByLanguage(team,"Java"),"2.2 filter developers");
        Map<String,Long> counts=svc.countDevelopersByLanguage(team);
        eq(2L,counts.get("Java"),"2.2 count Java without within-person duplicates");
        eq(2L,counts.get("Python"),"2.2 count Python");
        eq(Arrays.asList("A","B"),svc.groupDevelopersByLanguage(team).get("Java"),"2.2 grouping");
        List<Integer> input=Arrays.asList(1,2,3,4);
        ExecutorService pool=Executors.newFixedThreadPool(4);
        try {
            eq(Arrays.asList(1,4,9,16),ParallelTasks.sequential(input,0),"3.1 sequential values");
            eq(ParallelTasks.sequential(input,0),ParallelTasks.concurrent(input,0,pool),"3.1 equal parallel results");
            eq(Collections.emptyList(),ParallelTasks.concurrent(Collections.emptyList(),0,pool),"3.1 empty input");
        } finally{pool.shutdown();}
        System.out.println("\nУСПІШНО: "+passed+" тестів із "+passed);
    }
}
