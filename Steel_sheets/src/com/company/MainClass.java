package com.company;

import my_exceptions.NotPositive;
import my_exceptions.SquareNotRectangle;
import my_exceptions.WrongTriangle;
import steel_sheets_package.Rectangle;
import steel_sheets_package.Square;
import steel_sheets_package.SteelSheet;
import steel_sheets_package.Triangle;

import java.util.Scanner;

public class MainClass {
    static Scanner s = new Scanner(System.in);
    private static final double density = 7700;
    public static void main(String[] args) {
        System.out.println("Введите размер массива листов стали:");
        int quantity = get_positive_int();
        SteelSheet [] array_ss = new SteelSheet[quantity];
        for(int i=0; i<quantity; i++)
            array_ss[i] = create_steel_sheet();
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
                System.err.print("Это не число. \nВведите заново:");
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

    public static int get_type(int quantity_types) {
        int type = 1;
        type = get_int();
        while(type < 1 || type > quantity_types){
            System.err.print("Неверный тип. \nВведите заново:");
            type = get_int();
        }
        return type;
    }

    public static SteelSheet create_steel_sheet(){
        System.out.println("\nВыберите тип листа:\n" +
                "\t1: Прямоугольный\n" +
                "\t2: Квадратный\n" +
                "\t3: Треугольный\n");
        int type = get_type(3);
        switch(type){
            case 1: // прямоугольный
                return create_rectangle();
            case 2: // квадратный
                return create_square();
            case 3: // треугольный
                return create_triangle();
            default: return null;
        }
    }

    public static Rectangle create_rectangle(){
        Rectangle r = null;
        System.out.println("Введите толщину, длину и ширину:\n");
        int a, b, thickness = get_positive_int();;
        while(true){
            a = get_positive_int();
            b = get_positive_int();
            try {
                r = new Rectangle(density, thickness, a, b);
                System.out.println(r.toString());
                return r;
            }catch (NotPositive ex){
                System.err.println(ex.getMessage()+"\nВведите размеры заново:");
            }catch (SquareNotRectangle ex){
                System.err.println(ex.getMessage()+"\nВведите размеры заново:");
            }
        }
    }

    public static Square create_square(){
        Square s = null;
        System.out.println("Введите толщину и длину :\n");
        int a, thickness;
        while(true){
            thickness = get_positive_int();
            a = get_positive_int();
            try {
                s = new Square(density, thickness, a);
                System.out.println(s.toString());
                return s;
            }catch (NotPositive ex){
                System.err.println(ex.getMessage()+"\nВведите заново:");
            }
        }
    }

    public static Triangle create_triangle(){
        Triangle t = null;
        System.out.println("Введите толщину, и стороны треугольника:\n");
        int a, b, c, thickness = get_positive_int();
        while(true){
            a = get_positive_int();
            b = get_positive_int();
            c = get_positive_int();
            try {
                t = new Triangle(density, thickness, a, b, c);
                System.out.println(t.toString());
                return t;
            }catch (NotPositive ex){
                System.err.println(ex.getMessage()+"\nВведите заново:");
            }catch (WrongTriangle ex){
                System.err.println(ex.getMessage()+"\nВведите размеры заново:");
            }
        }
    }

}
/*
Тесты:

9
1
10 30 40
2
10 60
3
10 30 40 50
3
10 60 60 60
2
10 40
1
10 50 100
1
10 10 10
10 20
1
10 50 6000
1
10 1000 300



*/
