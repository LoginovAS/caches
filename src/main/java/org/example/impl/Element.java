package org.example.impl;

public class Element<T> {

    private T value;
    private Integer count;

    public Element(T value) {
        this.value = value;
        count = 1;
    }

    public T getValue() {
        return value;
    }

    public void increaseCount() {
        count++;
    }

    public Integer getCount() {
        return count;
    }
}
