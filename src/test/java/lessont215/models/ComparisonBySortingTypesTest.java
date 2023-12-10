package lessont215.models;

import models.IntegerList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ComparisonBySortingTypesTest {

    private IntegerList integerList;


    @BeforeEach
    private void setUp() {
        integerList = new IntegerList(5);
    }

    private Integer[] filingList() {
        int min = 100000;
        int max = 200000;
        int diff = max - min;

        var arr = new Integer[100000];

        Random random = new Random();
        for (var index = 0; index < arr.length; index++) {
            int nextInt = random.nextInt(diff + 1) + min;

            arr[index] = nextInt;
        }

        return arr;
    }

    @Test
    public void sortBubble() {
        var arr = filingList();

        long start = System.currentTimeMillis();
        integerList.sortBubble(arr);

        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void sortSelection() {
        var arr = filingList();

        long start = System.currentTimeMillis();
        integerList.sortSelection(arr);

        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void sortInsertion() {
        var arr = filingList();

        long start = System.currentTimeMillis();
        integerList.sortInsertion(arr);

        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void sortArrInteger() {
        var arr = filingList();

        long start = System.currentTimeMillis();
        integerList.sortArrInteger(arr);

        System.out.println(System.currentTimeMillis() - start);
    }
}
