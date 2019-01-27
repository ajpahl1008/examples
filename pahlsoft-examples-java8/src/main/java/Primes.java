import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;


public class Primes {

    private void countUp (int number) {
        IntStream.range(1,number)
                .limit(21)
                .forEach(index -> System.out.println("Doing Something"));
    }

    public static boolean isPrime(int number) {
       // return (number > 1) &&
                IntStream.range(1, number).limit(21).forEach(index -> System.out.println(index));

        IntStream.range(2, number)
        .noneMatch(index -> number % index == 0);

        return true;
    }



    public static void main(String[] args) {
        List<Double> sqrtOfFirst100Primes =
                Stream.iterate(1, e -> e + 1)
                        .filter(Primes::isPrime)
                        .map(Math::sqrt)
                        .limit(100)
                        .collect(toList());

        System.out.println(
                String.format("Computer %d values, first is %g, last is %g",
                        sqrtOfFirst100Primes.size(),
                        sqrtOfFirst100Primes.get(0),
                        sqrtOfFirst100Primes.get(sqrtOfFirst100Primes.size() - 1)));
    }

}
