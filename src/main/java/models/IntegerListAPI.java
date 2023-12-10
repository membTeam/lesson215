package models;

public interface IntegerListAPI {
    Integer add(Integer value);
    Integer add(int index, Integer value);
    Integer append(Integer value);

    void offsetLeft(int indexOffSet);
    void offsetRight(int indexOffSet);

    Integer set(int index, Integer value);

    Integer remove(Integer value);
    Integer remove(int index);

    boolean contains(Integer value);

    int indexOf(Integer value);
    int lastIndexOf(Integer value);

    Integer get(int index);

    Integer[] getClone(Integer[] otherStringArray);
    Integer[] getClone();

    boolean equals(Integer[] otherStringArray);
    Integer[] sortArrayString(Integer[] strList);
    boolean isEmpty();
    void clear();
    Integer[] toArray();
}
