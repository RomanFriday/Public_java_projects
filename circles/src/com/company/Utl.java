package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

import my_exceptions.FileIsOver;
import my_exceptions.NegativeRadius;

public class Utl {
    File inFile = null;
    File outFile = null;
    Scanner in = null;
    FileWriter out = null;
    Scanner s = new Scanner(System.in);

    public Utl(){};

    public Utl(String input, String output) throws IOException{
        this.inFile = new File(input);
        this.outFile = new File(output);
        try{
            this.in = new Scanner(inFile);
            this.out = new FileWriter(outFile);
        }
        catch (IOException ex){
            System.out.println("Файлы не открылись");
            throw new IOException();
        }
    }

    public double get_double_from_file() throws FileIsOver {
        double x = 0;
        while(true) {
            while(in.hasNext()){
                if(in.hasNextDouble()) {
                    x = in.nextDouble();
                    return x;
                }
                else {
                    String str = in.nextLine();
                }
            }
            throw new FileIsOver();
        }
    }

    public void quick_sort_r(ArrayList<Circle> circles, int leftBorder, int rightBorder) {
        if(leftBorder > rightBorder)
            return;
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        double pivot = circles.get((leftMarker + rightMarker) / 2).getR();
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (circles.get(leftMarker).getR() < pivot) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (circles.get(rightMarker).getR() > pivot) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    Circle temp_c = circles.get(leftMarker);
                    circles.set(leftMarker, circles.get(rightMarker));
                    circles.set(rightMarker, temp_c);
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            quick_sort_r(circles, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quick_sort_r(circles, leftBorder, rightMarker);
        }
    }

    public void quick_sort_x0(ArrayList<Circle> circles, int leftBorder, int rightBorder) {
        if(leftBorder > rightBorder)
            return;
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        double pivot = circles.get((leftMarker + rightMarker) / 2).getX0();
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (circles.get(leftMarker).getX0() < pivot) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (circles.get(rightMarker).getX0() > pivot) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    Circle temp_c = circles.get(leftMarker);
                    circles.set(leftMarker, circles.get(rightMarker));
                    circles.set(rightMarker, temp_c);
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            quick_sort_x0(circles, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quick_sort_x0(circles, leftBorder, rightMarker);
        }
    }

    public void quick_sort_y0(ArrayList<Circle> circles, int leftBorder, int rightBorder) {
        if(leftBorder > rightBorder)
            return;
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        double pivot = circles.get((leftMarker + rightMarker) / 2).getY0();
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (circles.get(leftMarker).getY0() < pivot) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (circles.get(rightMarker).getY0() > pivot) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    Circle temp_c = circles.get(leftMarker);
                    circles.set(leftMarker, circles.get(rightMarker));
                    circles.set(rightMarker, temp_c);
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            quick_sort_y0(circles, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quick_sort_y0(circles, leftBorder, rightMarker);
        }
    }

    public boolean is_repeat(ArrayList<Circle> circles, Circle circle){
        for(Circle c: circles)
            if(c.equals(circle))
                return true;
        return false;
    }
}
