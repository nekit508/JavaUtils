package javautils.structs;

import javautils.types.lambdas.Out0In1;
import javautils.types.lambdas.Out1In1;
import javautils.utils.Log;

@SuppressWarnings("unchecked")
public class Seq<T>{
    protected int size, listSize;
    protected T[] items;
    protected T temp1;
    protected T[] temp2;

    public String toString(){
        String string = "[";
        for(T elem: items){
            if(elem != null)string += elem.toString();
            string += ", ";
        }
        string += "]";
        return string;
    }

    public Seq(){
        listSize = 0;
        items = getList();
        size = 0;
    }

    T[] getList(){
        return (T[])(new Object[listSize]);
    }

    T[] getList(int size){
        return (T[])(new Object[size]);
    }

    protected T[] resize(){
        T[] items = this.items;
        T[] newItems = getList();
        System.arraycopy(items, 0, newItems, 0, size);
        this.items = newItems;
        return newItems;
    }

    public T get(int ind){
        return items[ind];
    }

    public T get(Out1In1<Boolean, T> f){
        for(int ind = 0;ind < size;ind++){
            if(f.get(get(ind))){
                return get(ind);
            }
        }
        return null;
    }

    public void add(T val){
        if(size == listSize) {
            listSize += 1;
            resize();
        }
        items[size++] = val;
    }

    public T pop(){
        temp1 = items[size-1];
        items[--size] = null;
        listSize--;
        resize();
        return temp1;
    }

    public void remove(int ind){
        size--;
        items[ind] = null;
        temp2 = getList(size);
        System.arraycopy(items, 0, temp2, 0, ind);
        System.arraycopy(items, ind+1, temp2, ind, size-ind);
        items = temp2;

        listSize--;
    }

    public void remove(Out1In1<Boolean, T> f){
        for(int ind = 0;ind < size;ind++){
            if(f.get(get(ind))){
                remove(ind);
            }
        }
    }

    public void toLog(){
        Log.info(toString());
    }

    public void each(Out0In1<T> f){
        for(T o: items){
            f.get(o);
        }
    }

    public void eachIf(Out1In1<Boolean, T> fb, Out0In1<T> f){
        for(T o: items){
            if(fb.get(o)) f.get(o);
        }
    }

    public T getLastElement() {
        return items[size-1];
    }
}
