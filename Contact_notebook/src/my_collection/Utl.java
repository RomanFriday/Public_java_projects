package my_collection;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

import my_exceptions.FileIsOver;

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

    public int get_int(){
        int x = 0;
        while(true) {
            if(s.hasNextInt()) {
                x = s.nextInt();
                return x;
            }
            else {
                String str = null;
                System.out.print("Это не число. \nВведите заново: ");
                while (!s.hasNextInt())
                    str = s.nextLine();
            }
        }
    }

    public int get_type(int quantity_types) {
        int type = 1;
        type = get_int();
        while(type < 1 || type > quantity_types){
            System.err.print("Неверный тип. \nВведите заново: ");
            type = get_int();
        }
        return type;
    }

    public int get_int_from_file() throws FileIsOver {
        int x = 0;
        while(true) {
            while(in.hasNext()){
                if(in.hasNextInt()) {
                    x = in.nextInt();
                    return x;
                }
                else {
                    String str = in.nextLine();
                }
            }
            throw new FileIsOver();
        }
    }
}
