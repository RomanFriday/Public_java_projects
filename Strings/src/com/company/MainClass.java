package com.company;

import my_exceptions.InvalidMaximumValue;

import java.io.*;
import java.util.Scanner;

public class MainClass {
    static  Scanner scanner = new Scanner(System.in);
    static int quantity_of_exercise = 5;

    public static void main(String[] args) {
        boolean flag = true;
        while(flag){
            System.out.println("\nВыберите задание (1-5) или завершите работу (6): ");
            int type=0;
            try{ type = get_type(quantity_of_exercise+1);
            }catch (InvalidMaximumValue ex){
                System.err.println(ex.getMessage());
                return;
            }
            String str = scanner.nextLine();
            switch (type){
                case 1:
                    exercise_1();
                    break;
                case 2:
                    exercise_2();
                    break;
                case 3:
                    exercise_3();
                    break;
                case 4:
                    exercise_4();
                    break;
                case 5:
                    exercise_5();
                    break;
                default: flag = false;
            }
        }
    }

    public static void exercise_1(){
        System.err.println("\n   Задание 1:\n" +
                "Поиск подстроки \"ant\"\n");
        System.out.println("\n Введите строку:");
        String str = scanner.nextLine();
        int index = str.indexOf("ant");
        if(index < 0)
            System.out.println("В введённой строке нет подстроки \"ant\"");
        else
            System.out.println("В введённой содержится подстрока \"ant\"");
    }

    public static void exercise_2(){
        System.out.println("\n   Задание 2:\n" +
                "Может ли ведённая строка быть номером автомобиля \n");
        System.out.println("\n Введите строку:");
        String str = scanner.nextLine();
        str = str.toUpperCase();
        if(str.matches("\\s*[A-ZА-Я][0-9]{3}[A-ZА-Я]{2}\\s*"))
            System.out.println("Введённая строка может быть автомобильным номером.");
        else
            System.out.println("Введённая строка НЕ может быть автомобильным номером.");
    }

    public static void exercise_3(){
        System.out.println("\n   Задание 3:\n" +
                "В предложении найти количество слов из четырёх букв\n");
        System.out.println("\n Введите предложение:");
        String str = scanner.nextLine();
        while(!str.matches("[a-zA-Zа-яА-Я0-9\\s,]*[!.?]")){
            System.err.println("\nПредложение должно заканчиваться символом окончания предложения -" +
                    " точкой, воклицательным или вопросительным знаком," +
                    "\n   содержать буквы и цифры, возможно наличие запятых и пробелов.\n" +
                    "   А также в предложении содержится ровно один символ окончания предложения." +
                    "\nВведите заново:");
            str = scanner.nextLine();
        }
        int l = str.length(), count = 0;
        char [] s = str.toCharArray();
        for(int i=0; i<l; i++)
        {
            while((i<l) && (s[i]==' ' || s[i]=='\t' || s[i]=='.' || s[i]==',' || s[i]=='!' ))
                i++;
            if(i>=l)
                break;
            int count_letter = 0;
            while( (i < l) && (s[i] != ',') && (s[i] != ' ') && (s[i] != '\t')
                    && (s[i] != '!') && (s[i] != '?') && (s[i] != '.') ) {
                count_letter++;
                i++;
            }
            if(count_letter==4)
                count++;
        }
        System.out.println("Количество четырёхбуквенных слов: " + count);
    }

    public static void exercise_4(){
        System.out.println("\n   Задание 4:\n" +
                "Из слова удалить все заглавные буквы \n");
        System.out.println("\n Введите слово:");
        String str = get_word("\\s*[a-zA-Zа-яА-Я]*\\s*",
                "\nСлово должно содержать только буквы!\nВведите заново:");
        int index = -1;
        StringBuffer a = new StringBuffer("A"), sb = new StringBuffer(str);
        for(int i=0;i<= (int)'Z'-(int)'A'; i++) {
            a.setCharAt(0, (char)('A' + i));
            while( (index = sb.indexOf(a.toString())) >= 0){
                sb.delete(index,index+1);
            }
        }
        for(int i=0;i<= (int)'Я'-(int)'А'; i++) {
            a.setCharAt(0, (char)('А' + i));
            while( (index = sb.indexOf(a.toString())) >= 0){
                sb.delete(index,index+1);
            }
        }
        System.out.println("Полученное слово: "+sb.toString());
    }

    public static void exercise_5(){
        System.out.println("\n   Задание 5:\n" +
                "Из текста вывести предложения, содержащие ключевое слово \n");
        BufferedReader reader = null;
        PrintWriter writer = null;
        try{
            reader = new BufferedReader(new FileReader("input.txt"));
            writer = new PrintWriter("output.txt");
            System.out.println("\n Введите ключевое слово:");
            String word = get_word("\\s*[^(\\s,)]*\\s*", "\nВведите только одно слово, без разделителей:");
            StringBuffer sb = new StringBuffer("");
            String str = null;
            while((str = reader.readLine()) != null){
                sb.append(str);
            }
            String [] text = sb.toString().split("[.!?]");
            /*fwriter.close();
            * */
            for(String sentence : text){
                if(sentence.matches("[\\s,]*"+word+"[,\\s]*|" + // только ключевое слово
                        "[\\s*,]*"+word+"[,*\\s]+.*|" + // начинается с ключевого слова
                        ".*[\\s,]+"+word+"[,\\s]*|" + // заканчивается ключевым словом
                        ".*[\\s,]+"+word+"[\\s,]+.*" )){ // содержит _слово_ или ,слово, или _слово, или ,слово
                    writer.println(sentence);
                    System.out.println(sentence);
                }
            }
            reader.close();
            writer.close();
        } catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }

    public static String get_word(String regex, String error_message){
        String word = scanner.nextLine();
        while(!word.matches(regex)){
            System.err.println(error_message);
            word = scanner.nextLine();
        }
        StringBuffer sb = new StringBuffer(word);
        int index = -1;
        while( (index = sb.indexOf(" ")) >= 0)
            sb.delete(index, index+1);
        while( (index = sb.indexOf("\t")) >= 0)
            sb.delete(index, index+1);
        word = sb.toString();
        return word;
    }

    public static int get_int(){
        int x = 0;
        while(true) {
            if(scanner.hasNextInt()) {
                x = scanner.nextInt();
                return x;
            }
            else {
                String str = null;
                System.out.print("Это не число. \nВведите заново:");
                while (!scanner.hasNextInt())
                    str = scanner.nextLine();
            }
        }
    }

    public static int get_type(int max) throws InvalidMaximumValue {
        if(max<1)
            throw new InvalidMaximumValue(1);
        int type = 1;
        type = get_int();
        while(type < 1 || type > max){
            System.out.print("Неверный тип. \nВведите заново:");
            type = get_int();
        }
        return type;
    }

}
