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
    public int get_int(){
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

    public void quickSort(ArrayList<Circle> source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        double pivot = source.get((leftMarker + rightMarker) / 2).getR();
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (source.get(leftMarker).getR() < pivot) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (source.get(rightMarker).getR() > pivot) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    double temp = source.get(leftMarker).getR();
                    try{
                    source.get(leftMarker).setR(source.get(rightMarker).getR());
                    source.get(rightMarker).setR(temp);
                    }catch (NegativeRadius ex){}
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            quickSort(source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(source, leftBorder, rightMarker);
        }
    }

}
