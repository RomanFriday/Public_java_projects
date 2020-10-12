package com.company;

import java.util.Scanner;

public class Utl {
    Scanner s = new Scanner(System.in);
    public Utl(){};
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
}
