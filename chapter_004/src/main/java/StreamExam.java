import java.util.Arrays;

public class StreamExam {

    int filterArray(int[] array) {
        return Arrays
            .stream(array)
            .filter(v -> v % 2 == 0)
            .map(v -> v * v)
            .sum();
    }
}

