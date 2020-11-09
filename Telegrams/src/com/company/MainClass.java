package com.company;

import my_exceptions.NegativePrice;
import my_exceptions.WrongFormatText;
import my_interface.MoreWords;

import java.util.Scanner;

public class MainClass {
    static Scanner s = new Scanner(System.in);
    static double price_regular = 2;
    static double price_urgent = 4;
    static double price_artistic = 1;
    static double price_artistic_blank = 17;
    public static void main(String[] args){
        System.out.println("Сколько телеграмм хотите написать?");
        int quantity = get_positive_int();
        Telegram t[] = new Telegram[quantity];
        MoreWords [] mw = new MoreWords[quantity];
        for ( int i = 0; i < quantity; i++ )
            mw[i] = t[i] = get_telegram();
        sort(mw, quantity);
        print_mw(mw, quantity);
        mw_to_t(mw, t, quantity);
    }

    public static void mw_to_t(MoreWords mw[], Telegram t[], int quantity){
            for (int i=0; i < quantity; i++) {
                t[i] = (Telegram)mw[i];
            }
        }

    public static int get_int(){
        int x = 0;
        while(true) {
            if(s.hasNextInt()) {
                x = s.nextInt();
                return x;
            }
            else {
                String str = null;
                System.out.print("Это не число. \nВведите заново:");
                while (!s.hasNextInt())
                    str = s.nextLine();
            }
        }
    }

    public static int get_positive_int(){
        int x = get_int();
        while(x<=0){
            System.err.println("Это не положительное число.");
            x = get_int();
        }
        return x;
    }

    public static int get_type() {
        int type = 1;
        type = get_int();
        while(type < 1 || type > 3){
            System.out.print("Неверный тип. \nВведите заново:");
            type = get_int();
        }
        return type;
    }

    public static Telegram get_telegram(){
        Telegram t = null;
        System.out.println("\n\nВыберите тип телеграммы:\n" +
                "  1 - обычная ( " + price_regular + "руб. слово)\n" +
                "  2 - срочная ( " + price_urgent + "руб. слово)\n" +
                "  3 - художественная ( " + price_artistic + "руб. слово + " + price_artistic_blank + "руб. бланк)");
        int type = get_type();
        String str;
        if(s.hasNextLine())
            str = s.nextLine();
        System.out.println("Введите текст:");
        while(true) {
            String text = s.nextLine();
            try {
                switch (type) {
                    // обычная
                    case 1:
                        t = new Telegram(price_regular, text);
                        break;
                    case 2:
                        t = new Urgent(price_urgent, text);
                        break;
                    case 3:
                        t = new Artistic(price_artistic, price_artistic_blank, text);
                        break;
                    default:
                        break;
                }
                ;
            } catch (NegativePrice ex) {
                System.out.println(ex.getMessage());
                continue;
            } catch (WrongFormatText ex) {
                System.out.println(ex.getMessage()+"\n Введите текст заново:");
                continue;
            }
            break;
        }
        System.out.println("Ваша телеграмма: "+t.toString() + "\n" +
                "Количество слов: " + t.count_of_words() + "\n" +
                "Цена: "+t.price());
        return t;
    }

    public static void print_mw(MoreWords mw[], int quantity) {
        for(MoreWords m : mw){
            if(m instanceof Telegram){
                Telegram telegram = (Telegram)m;
                System.out.println(telegram.toString());
                continue;
            }
            if(m instanceof Urgent){
                Urgent urgent = (Urgent) m;
                System.out.println(urgent.toString());
                continue;
            }
            if(m instanceof Artistic){
                Artistic artistic = (Artistic) m;
                System.out.println(artistic.toString());
                continue;
            }
        }

    }

    public static void sort(MoreWords mw[], int quantity){
        for (int i = 0; i < quantity; i++) {
            for (int j = 0; j < quantity - i - 1; j++){
                if(mw[j+1] instanceof Telegram){
                    Telegram telegram = (Telegram)mw[j+1];
                    if( mw[j].is_more(telegram) ){
                        MoreWords temp = mw[j];
                        mw[j] = mw[j+1];
                        mw[j+1] = temp;
                    }
                    continue;
                }
                if(mw[j+1] instanceof Urgent){
                    Urgent urgent = (Urgent)mw[j+1];
                    if(mw[j].is_more(urgent))
                    {
                        MoreWords temp = mw[j];
                        mw[j] = mw[j+1];
                        mw[j+1] = temp;
                    }
                    continue;
                }
                if(mw[j+1] instanceof Artistic){
                    Artistic artistic = (Artistic) mw[j+1];
                    if(mw[j+1].is_more(artistic)){
                        MoreWords temp = mw[j];
                        mw[j] = mw[j+1];
                        mw[j+1] = temp;
                    }
                    continue;
                }
            }
        }
    }

}

/*

Не забыть проверить:
в в в виде в в в продолжение в с  в связи с с.

*/
