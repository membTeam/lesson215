package lessont215.models;

import models.IntegerList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class SortedByRecursivTest {

    private IntegerList integerList;

    private Integer[] filingArrInteger(int capacity) {
        int min = 100000;
        int max = 200000;
        int diff = max - min;

        Integer[] arrInteger = new Integer[capacity];
        Random random = new Random();
        for (var index = 0; index < capacity; index++) {
            int nextInt = random.nextInt(diff + 1) + min;

            arrInteger[index] = nextInt;
        }

        return arrInteger;
    }

    @BeforeEach
    private void setUp() {
        integerList = new IntegerList(5);
    }

    @Test
    public void sortArrIntegerByRecurs() {
        var arrInteger = filingArrInteger(1000);

        var resSorted = integerList.sortArrIntegerByRecurs(arrInteger);

    }
}
