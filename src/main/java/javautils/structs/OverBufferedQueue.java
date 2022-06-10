package javautils.structs;

public class OverBufferedQueue<T> {
    protected T[] queue;
    protected int lastInd = 0, length;

    protected T tempValue;

    public OverBufferedQueue(int length){
        queue = (T[]) new Object[length];
        this.length = length;
    }

    public void push(T value){
        queue[lastInd++] = value;
        if(length == lastInd){
            lastInd = 0;
        }
    }

    public T pop(){
        tempValue = queue[lastInd];
        queue[lastInd--] = null;
        if(lastInd == -1){
            lastInd = length - 1;
        }
        return tempValue;
    }

    public T[] getQueue(){
        return queue;
    }

    public int getLength(){
        return length;
    }

    public int getLastInd(){
        return lastInd;
    }
}
