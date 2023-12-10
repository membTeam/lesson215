package lessont215.models;

import lesson215.exception.ErrStringListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;

import models.IntegerList;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.stream.IntStream;

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

    private int[] getRange() {
        return new int[]{0,
                integerList.getSize() - 2,
                integerList.getSize() - 1,
                -1, integerList.getCapacity() + 1 };
    }

    @BeforeEach
    private void setUp() {
        integerList = new IntegerList();
    }

    // ------------------------------

    @Test
    public void equals() {
        filingList(integerList, 5000);

        var clone = integerList.getClone();
        var size = integerList.getSize();

        var val0 =  clone[0];
        clone[0] = clone[size - 1];
        clone[size-1] = val0;

        var result = integerList.equals(clone);

        assertTrue(result);
    }

    @Test
    public void equalsDifferValueInLastIndex() {
        filingList(integerList, 5000);

        var clone = integerList.getClone();
        var size = integerList.getSize();

        clone[size-1] = 100;

        var result = integerList.equals(clone);
        assertFalse(result);
    }

    @Test
    public void equalsNotEqualsSize() {
        filingList(integerList, 5000);

        var clone = integerList.getClone();
        var size = integerList.getSize();

        clone[size] = 100;

        var result = integerList.equals(clone);
        assertFalse(result);
    }

    @Test
    public void contains() {
        filingList(integerList, 10000);

        var valForFind = integerList.get(500);
        var contains = integerList.contains(valForFind);

        assertTrue(contains);
    }

    @Test
    public void sortArrInteger() {
        filingList(integerList, 5);

        integerList.sortArrInteger();
        Integer valStart = integerList.get(0);
        for (var index = 1; index < integerList.getSize(); index++) {
            Integer valByIndex = integerList.get(index);
            assertTrue(valStart < valByIndex);

            valStart = valByIndex;
        }
    }

    @Test
    public void indexOf() {
        filingList(integerList, 5);

        int[] intRange = getRange();

        for (var index = 0; index < intRange.length; index++  ) {

            int indexRange = intRange[index];

            if (integerList.isIndexCorrect(indexRange)) {
                final int value = integerList.get(indexRange);
                var indexValue = integerList.indexOf(value);

                assertEquals(indexRange, indexValue);
            } else {
                assertThrows(ErrStringListException.class, ()-> integerList.get(indexRange));
            }
        }
    }

    @Test
    public void lastIndexOf() {
        filingList(integerList, 5);

        int[] intRange = getRange();

        for (var index = 0; index < intRange.length; index++  ) {

            int indexRange = intRange[index];

            if (integerList.isIndexCorrect(indexRange)) {
                final int value = integerList.get(indexRange);
                var indexValue = integerList.lastIndexOf(value);

                assertEquals(indexRange, indexValue);
            } else {
                assertThrows(ErrStringListException.class, ()-> integerList.get(indexRange));
            }
        }
    }

    @Test
    public void getClone() {

        filingList(integerList, 5);

        var resClone = integerList.getClone();

        assertEquals(integerList.getCapacity(), resClone.length );
    }

    @Test
    public void remove() {
        filingList(integerList, 5);

        var startSize = integerList.getSize();
        var firstVal = integerList.get(0);
        var lastVal = integerList.get(integerList.getSize()-1);

        var regionVal = new Integer[]{firstVal, lastVal, 100};
        for (var index = 0; index < regionVal.length; index++) {
            var valueRegin = regionVal[index];

            if (valueRegin != 100) {
                var removeVal = integerList.remove(valueRegin);

                assertTrue(integerList.indexOf(valueRegin) < 0);
                assertEquals(--startSize, integerList.getSize());
                assertEquals(valueRegin, removeVal);

            } else {
                assertThrows(ErrStringListException.class, ()-> integerList.remove(valueRegin));
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 0, 1000})
    public void removeByIndex(int index) {
        filingList(integerList, 5);

        if (index == 1000) {
            assertThrows(ErrStringListException.class, () -> integerList.removeByIndex(index));
        } else {
            var oldSize = integerList.getSize();
            var valDeleted = integerList.get(index);

            integerList.removeByIndex(index);

            assertEquals(--oldSize, integerList.getSize());
            assertEquals(-1, integerList.indexOf(valDeleted));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 0, 1000})
    public void addWithIndex(int index) {
        filingList(integerList, 5);

        var sizeOld = integerList.getSize();

        if (index == 1000) {
            assertThrows(ErrStringListException.class, () -> integerList.add(index, 1000));
        } else {
            var valBeforAdd = integerList.get(index);
            var resAdd = integerList.add(index, 1000);

            assertEquals(1000, resAdd);
            assertEquals(++sizeOld, integerList.getSize());
            assertEquals(valBeforAdd, integerList.get(index+1));
        }
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
