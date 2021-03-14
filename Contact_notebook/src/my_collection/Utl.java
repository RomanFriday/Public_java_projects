package my_collection;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

import my_comparators.FullNameComparator;
import my_exceptions.FileIsOver;
import notebook_package.Person;

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

    public void setIn(String input) throws IOException{
        this.inFile = new File(input);
        try{
            this.in = new Scanner(inFile);
        }
        catch (IOException ex){
            throw ex;
        }
    }

    public void setOut(String output) throws IOException{
        this.outFile = new File(output);
        try{
            this.out = new FileWriter(outFile);
        }
        catch (IOException ex){
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
                System.err.print("Это не число. \nВведите заново: ");
                while (!s.hasNextInt())
                    str = s.nextLine();
            }
        }
    }

    public int get_type(int quantity_types) {
        int type = 1;
        type = get_int();
        while(type < 1 || type > quantity_types){
            System.err.print("Неверное значение. \nВведите заново: ");
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

    public String unique_txt_file(String start_name) {
        Utl u = new Utl();
        StringBuffer txt_name = new StringBuffer(start_name+".txt");
        while(true){
            try{
                u.setIn(txt_name.toString());
            }catch (IOException ex){ // файл не открылся => новый файл будет уникальным
                break;
            }
            // файл открылся => создаём новое имя для нового поздравления
            txt_name.insert(txt_name.length()-4, "_1");
        }
        return txt_name.toString();
    }

    public ArrayList<Person> found(String s, ArrayList<Person> list){
        ArrayList<Person> founded = new ArrayList<Person>();
        if(s==null)
            return founded;
        for(Person person: list){
            if(person.get_full_name().contains(s) ||
                    person.getGeneral_number().contains(s) || person.getMobile_number().contains(s) ||
                    person.getStatus().contains(s) || person.getAddress().contains(s))
                founded.add(person);
        }
        founded.sort(new FullNameComparator());
        return founded;
    }

}
