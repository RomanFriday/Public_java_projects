package my_collection;

import my_comparators.FullNameComparator;
import my_exceptions.AlreadyExists;
import my_exceptions.FileIsOver;
import my_exceptions.NumberException;
import notebook_package.Notebook;
import notebook_package.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MainClass {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Person person = new Person();
        Notebook notebook = new Notebook();
        Utl u;
        try { u= new Utl("contacts.txt", ".txt");
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
            return;
        }
        while(u.in.hasNextLine()){
            try{
                person = new Person(u.in.nextLine(),u.in.nextLine(),u.in.nextLine(),u.in.nextLine(),u.in.nextLine(),u.in.nextLine(),u.in.nextLine());
                notebook.new_person(person);
            }
            catch (NumberException ex){
                System.err.println(ex.getMessage());
                System.err.println("Файл повреждён или содержит некорректные данные");
                return;
            }
            catch (NoSuchElementException ex){
                break; // файл закончился
            }
            catch (AlreadyExists ex){
                System.out.println("Файл содержит повторяющийся контакт:\n" + person.toString());
            }

        }

        while(menu.commands(notebook))
            ;

    }
}
