package com.company;

import java.io.IOException;
import java.util.ArrayList;

import com.company.*;
import my_exceptions.*;

public class MainClass {
    public static void main(String[] args) {
        ArrayList<Circle> circles = new ArrayList<Circle>();
        ArrayList<Circle> concentric = new ArrayList<Circle>();
        ArrayList<Circle> concerning = new ArrayList<Circle>();
        ArrayList<Circle> intercecting = new ArrayList<Circle>();
        try {
            Utl u = new Utl("input.txt", "output.txt");
            circles_from_file(u, circles);
            sort(u, circles);
            for(Circle c: circles)
                System.out.println(c.toString());
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
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

    private static void sort(Utl u, ArrayList<Circle> circles) {
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
}