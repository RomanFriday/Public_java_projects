package com.company;

import my_exceptions.NegativePrice;
import my_exceptions.WrongFormatText;

import java.util.Scanner;

public class MainClass {
    static Scanner s = new Scanner(System.in);
    static double price_regular = 2;
    static double price_urgent = 4;
    static double price_artistic = 1;
    static double price_artistic_blank = 17;
    public static void main(String[] args){
        Telegram t = null;
        System.out.println("Введите текст:");
        String text = s.nextLine();
        System.out.println("Выберите тип телеграммы:\n" +
                "  1 - обычная ( " + price_regular+ "руб. слово)\n"+
                "  2 - срочная ( " + price_urgent+ "руб. слово)\n"+
                "  3 - художественная ( " + price_artistic+ "руб. слово + "+price_artistic_blank+"руб. бланк)");
        int type = get_type();
        try{
            switch(type){
                // обычная
                case 1:
                    t = new Telegram(price_regular,text);
                    break;
                case 2:
                    t = new Urgent(price_urgent, text);
                    break;
                case 3:
                    t = new Artistic(price_artistic, price_artistic_blank, text);
                    break;
                default: return;
            };
        }catch (NegativePrice ex){
            System.out.println(ex.getMessage());
            return;
        }
        catch (WrongFormatText ex){
            System.out.println(ex.getMessage());
            return ;
        }
        System.out.println("Ваша телеграмма: "+t.toString() + "\n" +
                        "Количество слов: " + t.count_of_words() + "\n" +
                "Цена: "+t.price());
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

    public static int get_type() {
        int type = 1;
        type = get_int();
        while(type < 1 || type > 3){
            System.out.print("Неверный тип. \nВведите заново:");
            type = get_int();
        }
        return type;
    }

}

/*

Не забыть проверить:
в в в виде в в в продолжение в с  в связи с с.

*/
