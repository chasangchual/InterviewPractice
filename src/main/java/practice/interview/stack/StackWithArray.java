package practice.interview.stack;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class StackWithArray<T extends Comparable> {
    static int CAPACITY = 1000;
    T[] array = (T[]) new Object[CAPACITY];
    int size = 0;
    int head = 0;
    int tail = -1;

    public void push(T element) {
        if( (size + 1) >= CAPACITY) {
            throw new IllegalStateException("stack is over capacity");
        }
        array[++tail] = element;
        size ++;
    }

    public T pop() {
        if(size > 0) {
            return array[tail--];
        } else {
            throw new NoSuchElementException();
        }
    }

    public Integer size() {
        return size;
    }
}
