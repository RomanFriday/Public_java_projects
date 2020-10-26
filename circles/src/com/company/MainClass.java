package com.company;

import java.io.IOException;
import java.util.ArrayList;

import com.company.*;
import my_exceptions.*;

public class MainClass {
    public static void main(String[] args) {
        ArrayList<Circle> circles = new ArrayList<Circle>();
        ArrayList<Circle> concentric = new ArrayList<Circle>();
        Utl u = null;
        try {
            u = new Utl("input.txt", "output.txt");
            circles_from_file(u, circles);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
            return;
        }
        sortRXY(u, circles);
        System.out.println("\nВсе окружности:");
        for(Circle c: circles)
            System.out.println(c.toString());

        create_concentric(circles, concentric);
        sortXYR(u, concentric);
        System.out.println("\nКонцентрические:");
        print_concentric(concentric);

        System.out.println("\nКасающиеся:");
        print_concerning(circles);

        System.out.println("\nПересекающиеся:");
        print_intercecting(circles);

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
                if(!u.is_repeat(circles, c))
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

    private static void create_concentric(ArrayList<Circle> circles, ArrayList<Circle> concentric) {
        Utl u = new Utl();
        for (int i = 1; i < circles.size(); i++){
            for (int j = 0; j < i; j++) {
                if (circles.get(i).is_concentric(circles.get(j))) {
                    if (!u.is_repeat(concentric, circles.get(j)))
                        concentric.add(new Circle(circles.get(j)));
                    if (!u.is_repeat(concentric, circles.get(i)))
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

    private static void print_concerning(ArrayList<Circle> circles){
        Utl u = new Utl();
        for (int i = 1; i < circles.size(); i++){
            for (int j = 0; j < i; j++) {
                if (circles.get(i).is_concerning(circles.get(j))) {
                    System.out.println(circles.get(i).toString());
                    System.out.println(circles.get(j).toString());
                    System.out.println();
                }
            }
        }
    }

    private static void print_intercecting(ArrayList<Circle> circles){
        Utl u = new Utl();
        for (int i = 1; i < circles.size(); i++){
            for (int j = 0; j < i; j++) {
                if (circles.get(i).is_intercecting(circles.get(j))) {
                    System.out.println(circles.get(i).toString());
                    System.out.println(circles.get(j).toString());
                    System.out.println();
                }
            }
        }
    }

    private static void sortRXY(Utl u, ArrayList<Circle> circles) {
        // сортировка по радиусу
        u.quick_sort_r(circles,0, circles.size()-1);
        // для каждого радиуса r сортировка по x
        for(int i=0; i<circles.size();)
        {
            int left = i,right = i;
            double r = circles.get(i).getR();
            while( (right < circles.size()) && (circles.get(right).getR() == r) )
                right++;
            u.quick_sort_x0(circles, left, right-1);

            // для каждого x сортировка по y
            i = left;
            for(int j=left; j<right;){
                int k=j;
                double x = circles.get(j).getX0();
                while( (k < right) && (circles.get(k).getX0() == x) )
                    k++;
                u.quick_sort_y0(circles, j, k-1);
                j = k;
            }

            i = right;
        }
    }

    private static void sortXYR(Utl u, ArrayList<Circle> circles) {
        // сортировка по x0
        u.quick_sort_x0(circles,0, circles.size()-1);
        // для каждого x0 сортировка по y
        for(int i=0; i<circles.size();)
        {
            int left = i, right = i;
            double x0 = circles.get(i).getX0();
            while( (right < circles.size()) && (circles.get(right).getX0() == x0) )
                right++;
            u.quick_sort_y0(circles, left, right-1);

            // для каждого y сортировка по r
            for(int j=left; j<right;){
                int k=j;
                double y = circles.get(j).getY0();
                while( (k < right) && (circles.get(k).getY0() == y) )
                    k++;
                u.quick_sort_r(circles, j, k-1);
                j = k;
            }

            i = right;
        }
    }
}