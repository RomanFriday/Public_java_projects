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
            u.quickSort(circles,0, circles.size()-1);
            for(int i=0; i<circles.size(); i++){
                System.out.println(circles.get(i).toString());
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void circles_from_file(Utl u, ArrayList<Circle> circles) {
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
}