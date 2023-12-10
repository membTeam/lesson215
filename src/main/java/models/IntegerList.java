package models;

import lesson215.exception.ErrStringListException;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Arrays;
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
    private void verifyIndex(int index) {
        if (index < 0 || index >= size ) {
            runException("Индекс за пределом допустимого значения");
        }
    }
    private void verifyIndexGrup(int sizeAdd, int startIndex) {
        if (size == capacity ) {
            runException("Переполнение списка");
        } else if (startIndex < 0 || startIndex >= capacity ) {
            runException("Индекс за пределом допустимого значения");
        }
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
        return null;
    }

    @Override
    public Integer remove(int index) {
        verifyIndex(index);

        var resultRemove = get(index);
        offsetLeft(index);

        return resultRemove;
    }

    @Override
    public boolean contains(Integer value) {
        return false;
    }

    @Override
    public int indexOf(Integer value) {
        return 0;
    }

    @Override
    public int lastIndexOf(Integer value) {
        return 0;
    }

    @Override
    public Integer get(int index) {
        verifyIndex(index);

        return arrInteger[index];
    }

    @Override
    public Integer[] getClone(Integer[] otherStringArray) {
        return new Integer[0];
    }

    @Override
    public Integer[] getClone() {
        return new Integer[0];
    }

    @Override
    public boolean equals(Integer[] otherStringArray) {
        return false;
    }

    @Override
    public Integer[] sortArrayString(Integer[] intList) {
        int in, out;

        Integer[] resultArrInteger = intList == null ? arrInteger : intList;

        for(out = 1; out < size; out++) {
            Integer temp = resultArrInteger[out];

            if (temp == null) {
                continue;
            }

            in = out;
            while(in > 0 && resultArrInteger[in-1].compareTo(temp) > 0 ) {
                resultArrInteger[in] = resultArrInteger[in-1];
                --in;
            }

            if (in < out) {
                resultArrInteger[in] = temp;
            }
        }

        return resultArrInteger;

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Integer[] toArray() {
        return new Integer[0];
    }
}
