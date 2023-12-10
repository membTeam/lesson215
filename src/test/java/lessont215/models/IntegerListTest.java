package lessont215.models;

import lesson215.exception.ErrStringListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;

import models.IntegerList;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

public class IntegerListTest {

    private IntegerList integerList;

    private void filingList(IntegerList list, int numAdd) {
        int min = 100000;
        int max = 200000;
        int diff = max - min;

        Random random = new Random();
        for (var index = 0; index < numAdd; index++) {
            int nextInt = random.nextInt(diff + 1) + min;
            list.add(nextInt);
        }
    }

    @BeforeEach
    private void setUp() {
        integerList = new IntegerList();
    }

    // ------------------------------

    @ParameterizedTest
    @ValueSource(ints = {0, 1000})
    public void removeForIndex(int index) {
        filingList(integerList, 5);

        if (index == 1000) {
            assertThrows(ErrStringListException.class, () -> integerList.remove(index));
        } else {
            var oldSize = integerList.getSize();
            var lastIndex = integerList.getSize() - 1;
            var valLast = integerList.get(lastIndex);

            integerList.remove(0);

            assertEquals(oldSize - 1, integerList.getSize());
            assertEquals(valLast, integerList.get(integerList.getSize() - 1));
        }

    }


    @Test
    public void addWithIndex() {
        filingList(integerList, 5);

        System.out.println(integerList);

        var oldData = integerList.get(4);
        integerList.add(0, 1000);

        System.out.println(integerList);

        var newSize = integerList.getSize();
        assertEquals(6, integerList.getSize());

        assertEquals(oldData, integerList.get(newSize -1));

    }

    @Test
    public void add() {

        filingList(integerList, 5);
        assertEquals(5, integerList.getSize());

        integerList.add(1000);
        integerList.add(2000);

        assertEquals(7, integerList.getSize());
    }

}
