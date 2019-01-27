import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class RangeExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        IntStream.range(0, 10)
                .forEach(index ->
                        executorService.submit(() -> System.out.println("Running task " + index)));

        System.out.println("Tasks started...");
        executorService.shutdown();

        IntStream.range(0,100)
                .forEach(i ->
                        System.out.print(Integer.toString(i)));

    }


}
