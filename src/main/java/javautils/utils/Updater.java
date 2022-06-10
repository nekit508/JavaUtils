package javautils.utils;

import javautils.types.lambdas.Out0In0;

public class Updater {
    protected long prev, cur;
    protected Out0In0 f;
    public long delta = 1;
    public float ups;

    public Updater(Out0In0 f, float ups){
        this.f = f;
        this.ups = ups;
        prev = System.currentTimeMillis();
        cur = prev;
    }

    public void update(){
        cur = System.currentTimeMillis();
        if(cur - prev >= 1000f/ups){
            delta = cur - prev;
            f.get();
            prev = cur;
        }
    }
}
