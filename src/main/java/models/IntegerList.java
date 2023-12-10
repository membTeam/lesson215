package models;

import lesson215.exception.ErrStringListException;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class IntegerList implements IntegerListAPI {
    private Integer[] arrInteger;

    @Getter private int capacity = 20;

    @Getter
    private int size;

    private double fillFactor = 0.75;

    public IntegerList() {
        initialDefault();
    }

    public IntegerList(int capacity) {
        this.capacity = capacity;
        initialDefault();
    }


    @SneakyThrows(ErrStringListException.class)
    private void runException(String err) {
        throw new ErrStringListException(err);
    }

    private void initialDefault() {
        arrInteger = new Integer[capacity];
        size = 0;
    }

    private void verifyData(Integer value) {
        if (value == null) {
            runException("null значение");
        }
    }
    public void verifyIndex(int index) {
        if (index < 0 || index >= size ) {
            runException("Индекс за пределом допустимого значения");
        }
    }

    public boolean isIndexCorrect(int index) {
        if (index < 0 || index >= size ) {
            return false;
        }

        return true;
    }

    private void ifNecessaryExpand(int sizeAdd) {
        int newSize = size + sizeAdd;
        double newFillFactor = (newSize / (double) capacity);

        if (newFillFactor > fillFactor) {
            var bufIndex = new Object(){  public int index = 0;  };
            int newCapacity = (int) (newSize * (1 + fillFactor));

            var newArray = new Integer[newCapacity];
            Stream.of(arrInteger).limit(size).forEach(str ->
                    newArray[bufIndex.index] = arrInteger[bufIndex.index++]);

            arrInteger = newArray;
            capacity = newCapacity;
        }
    }

    private int lastIndex() {
        return size == 0 ? -1: size - 1;
    }

    // -------------------------------------


    @Override
    public String toString() {
        return Arrays.toString(arrInteger);
    }

    @Override
    public Integer add(Integer value) {
        return append(value);
    }

    @Override
    public Integer add(int index, Integer value) {
        verifyData(value);
        verifyIndex(index);

        ifNecessaryExpand(1);
        offsetRight(index);

        arrInteger[index] = value;

        return arrInteger[index];
    }

    @Override
    public Integer append(Integer value) {
        verifyData(value);

        ifNecessaryExpand(1);

        arrInteger[size++] = value;

        return value;
    }

    @Override
    public void offsetRight(int indexOffSet) {
        verifyIndex(indexOffSet);

        append(-1);
        for (var index = size -1; index > indexOffSet; index--) {
            arrInteger[index] = arrInteger[index - 1];
        }

        arrInteger[indexOffSet] = -1;
    }

    @Override
    public void offsetLeft(int indexOffSet) {
        verifyIndex(indexOffSet);

        for (var index = indexOffSet; index < size - 1; index++) {
            arrInteger[index] = arrInteger[index + 1];
        }

        arrInteger[size-1] = null;

        size--;
    }

    @Override
    public Integer set(int index, Integer value) {
        return null;
    }

    @Override
    public Integer remove(Integer value) {

        int index = indexOf(value);
        if (index < 0) {
            runException("Нет данных");
        }

        return removeByIndex(index);
    }

    @Override
    public Integer removeByIndex(int index) {
        verifyIndex(index);

        var resultRemove = get(index);
        offsetLeft(index);

        return resultRemove;
    }

    @Override
    public boolean contains(Integer value) {
        sortArrInteger();

        int min = 0;
        int max = size - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (value == arrInteger[mid]) {
                return true;
            }

            if (value < arrInteger[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return false;
    }

    @Override
    public int indexOf(Integer value) {
        verifyData(value);

        var resIndex = IntStream.range(0, size)
                .filter(index-> arrInteger[index].equals(value))
                .map(index-> index)
                .min();

        return resIndex.isEmpty() ? -1 : resIndex.getAsInt();
    }

    @Override
    public int lastIndexOf(Integer value) {
        verifyData(value);

        var resIndex = IntStream.range(0, size)
                .filter(index-> arrInteger[index].equals(value))
                .map(index-> index)
                .max();

        return resIndex.isEmpty() ? -1 : resIndex.getAsInt();
    }

    @Override
    public Integer get(int index) {
        verifyIndex(index);

        return arrInteger[index];
    }

    @Override
    public Integer[] getClone(Integer[] otherArray) {
        int capacityClone = 0;
        for (var index = 0; index < otherArray.length; index++) {
            if (otherArray[index] != null) {
                capacityClone++;
            }
        }

        var resArrString = new Integer[capacityClone];

        for (var index = 0; index < otherArray.length; index++) {
            if (otherArray[index] == null) {
                continue;
            }

            resArrString[index] = otherArray[index];
        }

        return resArrString;
    }

    @Override
    public Integer[] getClone() {

        var resArrInteger = new Integer[capacity];

        for (var index = 0; index < size; index++) {
            resArrInteger[index] = arrInteger[index];
        }

        return resArrInteger;
    }

    @Override
    public boolean equals(Integer[] otherArray) {
        return false;
    }

    @Override
    public void sortArrInteger() {

        if (size == 0) {
            return;
        }

        int in, out;

        Integer[] sortedArrInteger = getClone();

        for(out = 1; out < size; out++) {
            Integer temp = sortedArrInteger[out];

            in = out;
            while(in > 0 && sortedArrInteger[in-1].compareTo(temp) > 0 ) {
                sortedArrInteger[in] = sortedArrInteger[in-1];
                --in;
            }

            if (in < out) {
                sortedArrInteger[in] = temp;
            }
        }

        arrInteger = sortedArrInteger;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        arrInteger = new Integer[capacity];
    }

    @Override
    public Integer[] toArray() {
        if (size == 0) {
            return null;
        }

        var resArrInteger = new Integer[size];

        var index = 0;
        while (index < size) {
            resArrInteger[index] = arrInteger[index++];
        }

        return resArrInteger;
    }
}
