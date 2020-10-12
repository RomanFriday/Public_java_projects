package com.company;

import java.util.Scanner;

import my_exceptions.ValueOutsideSet;
import my_exceptions.MinMoreMax;

public class MainClass {
    public static void main(String[] args) throws ValueOutsideSet, MinMoreMax {
        Utl u = new Utl();
        Counter counter = create_counter(u);
        exercise(u, counter);

    }

    public static void exercise(Utl u, Counter counter){
        if(counter == null) throw new NullPointerException();
        System.out.print("Начало:");
        counter.println_now();
        System.out.println("Сколько показаний счётчика вывести? (1;100)");
        int amount_outputs = 0;
        boolean all_is_ok=false;
        while(!all_is_ok){
            amount_outputs = u.get_int();
            if(amount_outputs<1 || amount_outputs>100) {
                System.out.println("Значение вне множества (1;100) ");
                continue;
            }
            all_is_ok = true;
        }
        for(int i=0; i<amount_outputs; i++){
            counter.plus1();
            counter.println_now();
        }
    }

    public static Counter create_counter(Utl u) throws ValueOutsideSet, MinMoreMax {
        boolean all_is_ok = false;
        int min1, min2, max1, max2, now1, now2;
        Counter counter = null;
        while (!all_is_ok) {
            System.out.println("Введите нижнюю границу первой части счётчика: ");
            min1 = u.get_int();
            System.out.println("Введите верхнюю границу первой части счётчика: ");
            max1 = u.get_int();
            System.out.println("Введите нижнюю границу второй части счётчика: ");
            min2 = u.get_int();
            System.out.println("Введите верхнюю границу второй части счётчика: ");
            max2 = u.get_int();
            try {
                counter = new Counter(min1, max1, min2, max2);
                all_is_ok = true;
            } catch (MinMoreMax ex) {
                System.out.println(ex.getMessage());
                all_is_ok = false;
                continue;
            }
        }
        all_is_ok = false;
        while(!all_is_ok){
            System.out.println("Введите начальное значение первой части счётчика: ");
            try {
                counter.setNow1(u.get_int());
                all_is_ok = true;
            } catch (ValueOutsideSet ex) {
                System.out.println(ex.getMessage());
                all_is_ok = false;
                continue;
            }
            System.out.println("Введите начальное значение второй части счётчика: ");
            try {
                counter.setNow2(u.get_int());
                all_is_ok = true;
            } catch (ValueOutsideSet ex) {
                System.out.println(ex.getMessage());
                all_is_ok = false;
                continue;
            }
        }
        return counter;
    }
}
