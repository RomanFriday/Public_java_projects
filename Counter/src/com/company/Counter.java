package com.company;

import my_exceptions.MinMoreMax;
import my_exceptions.ValueOutsideSet;

public class Counter {
    int min1 = 0;
    int max1 = 1;
    int min2 = 0;
    int max2 = 1;
    int now1 = min1;
    int now2 = min2;

    public Counter(){};

    public Counter(int min, int max) throws MinMoreMax{
        if( min>max ) throw new MinMoreMax(min, max);
        this.min1 = this.min2 = min;
        this.max1 = this.max2 = max;
        now1 = now2 = min;
    };

    public Counter(int min1, int max1, int min2, int max2) throws MinMoreMax{
        if( min1>max1 ) throw new MinMoreMax(min1, max1);
        if( min2>max2 ) throw new MinMoreMax(min2, max2);
        this.min1 = min1;
        this.min2 = min2;
        this.max1 = max1;
        this.max2 = max2;
        this.now1 = min1;
        this.now2 = min2;
    }

    public Counter(Counter c){
        if(c==null) throw new NullPointerException();
        min1 = c.min1;
        min2 = c.min2;
        max1 = c.max1;
        max2 = c.max2;
        now1 = c.now1;
        now2 = c.now2;
    }

    public int getMin1(){
        return min1;
    }

    public int getMin2() {
        return min2;
    }

    public int getMax1() {
        return max1;
    }

    public int getMax2() {
        return max2;
    }

    public int getNow1() {
        return now1;
    }

    public int getNow2() {
        return now2;
    }

    public void setMax1(int max) throws MinMoreMax{
        if( min1>max ) throw new MinMoreMax(min1, max);
        this.max1 = max;
    }

    public void setMax2(int max) throws MinMoreMax{
        if( min2>max ) throw new MinMoreMax(min2, max);
        this.max2 = max;
    }

    public void setMin1(int min) throws MinMoreMax {
        if( min>max1 ) throw new MinMoreMax(min, max1);
        this.min1 = min;
    }

    public void setMin2(int min) throws MinMoreMax {
        if( min>max2 ) throw new MinMoreMax(min, max2);
        this.min2 = min;
    }

    public void setNow1(int now) throws ValueOutsideSet {
        if( now<min1 || now>max1 ) throw new ValueOutsideSet(now, min1, max1);
        this.now1 = now;
    }

    public void setNow2(int now) throws ValueOutsideSet {
        if( now<min2 || now>max2 ) throw new ValueOutsideSet(now, min2, max2);
        this.now2 = now;
    }

    public void print_now(){
        System.out.print(" " + now1 + " . " + now2 + " ");
    }

    public void println_now(){
        System.out.println(" " + now1 + " . " + now2 + " ");
    }

    public void plus1(){
        now2++;
        if(now2>max2){
            now2 = min2;
            now1++;
            if(now1>max1)
                now1 = min1;
        }
    }
}
