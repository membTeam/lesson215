package models;

public interface IntegerListAPI {
    Integer add(Integer value);
    Integer add(int index, Integer value);
    Integer append(Integer value);

    void offsetLeft(int indexOffSet);
    void offsetRight(int indexOffSet);

    Integer set(int index, Integer value);

    Integer remove(Integer value);
    Integer removeByIndex(int index);

    boolean contains(Integer value);

    int indexOf(Integer value);
    int lastIndexOf(Integer value);

    Integer get(int index);

    Integer[] getClone(Integer[] otherArray);
    Integer[] getClone();

    boolean equals(Integer[] otherArray);
    void sortArrInteger();
    boolean isEmpty();
    void clear();
    Integer[] toArray();
}
