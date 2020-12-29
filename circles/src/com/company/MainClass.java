package com.company;

import java.io.IOException;
import java.util.ArrayList;

import my_comparators.*;
import my_exceptions.*;

public class MainClass {
    public static void main(String[] args) {
        ArrayList<Circle> circles = new ArrayList<Circle>();
        ArrayList<Circle> concentric = new ArrayList<Circle>();
        System.out.println("Если хотите ввести окружности из файла, введите 0.\n");
        Utl u = null;
        int is_from_file = u.get_int();
        if(is_from_file==0)
        {
            try {
                u = new Utl("input.txt", "output.txt");
                circles_from_file(u, circles);
            }
            catch (IOException ex){
                System.out.println("Файлы не открылись");
                return;
            }
        }
        else
        {

        }
        X_comparator x_c = new X_comparator();
        Y_comparator y_c = new Y_comparator();
        R_comparator r_c = new R_comparator();
        circles.sort(r_c.thenComparing(x_c.thenComparing(y_c)));
        //sortRXY(circles);
        create_concentric(circles, concentric);
        concentric.sort(x_c.thenComparing(y_c.thenComparing(r_c)));
        //sortXYR(concentric);
        print_to_console(circles, concentric);
        try{
            print_to_file(u, circles, concentric);
            u.out.close();
            u.in.close();
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

    }

    private static void circles_from_file(Utl u, ArrayList<Circle> circles) {
        double x0 = 0, y0 = 0, r = 0;
        Circle c = null;
        while(true) {
            try {
                x0 = u.get_double_from_file();
                y0 = u.get_double_from_file();
                do {
                    r = u.get_double_from_file();
                }while (r < 0);
                c = new Circle(x0, y0, r);
                if(!is_repeat(circles, c))
                    circles.add(c);
            }
            catch (FileIsOver ex){
                return;
            }
            catch (NegativeRadius ex) {
                ;
            }
        }
    }

    private static void circles_from_console(Utl u, ArrayList<Circle> circles) {
        double x0 = 0, y0 = 0, r = 0;
        Circle c = null;
        while(true) {
            try {
                x0 = u.get_double();
                y0 = u.get_double();
                while(true){
                    r = u.get_double();
                    if(r>=0)
                           break;
                    System.err.println("\nРадиус окружности должен быть неотрицателен.\n Введите значение радиуса заново: ");
                }
                c = new Circle(x0, y0, r);
                if(!is_repeat(circles, c))
                    circles.add(c);
            }
            catch (NegativeRadius ex) {
                ;// нвеозможно
            }
        }
    }

    private static void create_concentric(ArrayList<Circle> circles, ArrayList<Circle> concentric) {
        Utl u = new Utl();
        for (int i = 1; i < circles.size(); i++){
            for (int j = 0; j < i; j++) {
                if (circles.get(i).is_concentric(circles.get(j))) {
                    if (!is_repeat(concentric, circles.get(j)))
                        concentric.add(new Circle(circles.get(j)));
                    if (!is_repeat(concentric, circles.get(i)))
                        concentric.add(new Circle(circles.get(i)));
                    break;
                }
            }
        }
    }

    private static void print_concentric(ArrayList<Circle> concentric) {
        for(int i=0; i<concentric.size(); i++){
            System.out.println(concentric.get(i).toString());
            if(i<concentric.size()-1)
                if( (concentric.get(i+1).getX0()!=concentric.get(i).getX0()) )
                    System.out.println();
        }
    }

    private static void print_concentric_to_file(Utl u, ArrayList<Circle> concentric) throws IOException{
        for(int i=0; i<concentric.size(); i++){
            try{
                u.out.write(concentric.get(i).toString()+"\n");
                if(i<concentric.size()-1)
                    if( (concentric.get(i+1).getX0()!=concentric.get(i).getX0()) )
                        u.out.write("\n");
            }catch(IOException ex){
                throw ex;
            }
        }
    }

    private static void print_concerning(ArrayList<Circle> circles){
        Utl u = new Utl();
        for (int i = 1; i < circles.size(); i++){
            for (int j = 0; j < i; j++) {
                if (circles.get(i).is_concerning(circles.get(j))) {
                    System.out.println(circles.get(i).toString());
                    System.out.println(circles.get(j).toString()+"\n");
                }
            }
        }
    }

    private static void print_concerning_to_file(Utl u, ArrayList<Circle> circles) throws IOException{
        for (int i = 1; i < circles.size(); i++){
            for (int j = 0; j < i; j++) {
                try {
                    if (circles.get(i).is_concerning(circles.get(j))) {
                        u.out.write(circles.get(i).toString()+"\n");
                        u.out.write(circles.get(j).toString()+"\n\n");
                    }
                }catch (IOException ex){
                    throw ex;
                }
            }
        }
    }

    private static void print_intersecting(ArrayList<Circle> circles){
        Utl u = new Utl();
        for (int i = 1; i < circles.size(); i++){
            for (int j = 0; j < i; j++) {
                if (circles.get(i).is_intercecting(circles.get(j))) {
                    System.out.println(circles.get(i).toString());
                    System.out.println(circles.get(j).toString()+"\n");
                }
            }
        }
    }

    private static void print_intersecting_to_file(Utl u, ArrayList<Circle> circles) throws IOException{
        for (int i = 1; i < circles.size(); i++){
            for (int j = 0; j < i; j++) {
                try {
                    if (circles.get(i).is_intercecting(circles.get(j))) {
                        u.out.write(circles.get(i).toString()+"\n");
                        u.out.write(circles.get(j).toString()+"\n\n");
                    }
                }catch (IOException ex){
                    throw ex;
                }
            }
        }
    }

    private static void print_to_console(ArrayList<Circle> circles, ArrayList<Circle> concentric){
        System.out.println("\nВсе окружности:");
        for(Circle c: circles)
            System.out.println(c.toString());

        System.out.println("\nКонцентрические:");
        print_concentric(concentric);

        System.out.println("\nКасающиеся:");
        print_concerning(circles);

        System.out.println("\nПересекающиеся:");
        print_intersecting(circles);
    }

    private static void print_to_file(Utl u, ArrayList<Circle> circles, ArrayList<Circle> concentric) throws IOException{
        try{
            u.out.write("\nВсе окружности:\n");
            for(Circle c: circles)
                u.out.write(c.toString() + "\n");

            u.out.write("\nКонцентрические:\n");
            print_concentric_to_file(u, concentric);

            u.out.write("\nКасающиеся:\n");
            print_concerning_to_file(u, circles);

            u.out.write("\nПересекающиеся:\n");
            print_intersecting_to_file(u, circles);
        } catch (IOException ex){
            throw ex;
        }
    }

    private static boolean is_repeat(ArrayList<Circle> circles, Circle circle){
        for(Circle c: circles)
            if(c.equals(circle))
                return true;
        return false;
    }

    private Circle create_circle()

}