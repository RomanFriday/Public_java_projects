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
            throw ex;
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

    public int get_int(){
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

    public double get_double(){
        double x = 0;
        while(true) {
            if(s.hasNextDouble()) {
                x = s.nextDouble();
                return x;
            }
            else {
                String str = null;
                System.err.print("Это не число. \nВведите заново:");
                while (!s.hasNextDouble())
                    str = s.nextLine();
            }
        }
    }
}
