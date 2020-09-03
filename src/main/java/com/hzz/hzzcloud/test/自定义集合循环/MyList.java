package com.hzz.hzzcloud.test.自定义集合循环;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/28 15:43
 */
public class MyList<E> extends ArrayList {
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        Itr() {}

        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            E o = (E) get(cursor);
            cursor+=1;
            return o;
        }

        @Override
        public void remove() {
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super E> consumer) {

        }

        final void checkForComodification() {

        }
    }


}
