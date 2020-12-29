package my_collection;

import my_comparators.FullNameComparator;
import my_comparators.NameComparator;
import my_comparators.PatronypicComparator;
import my_comparators.SurnameComparator;
import my_exceptions.NumberException;
import notebook_package.Notebook;
import notebook_package.Person;

import java.util.ArrayList;
import java.util.Comparator;

public class MainClass {
    public static void main(String[] args) {

        Person[] persons = new Person[5];
        for(int i=0; i< persons.length; i++)
            persons[i] = new Person("Иванов", "Иван", "Иванович "+(5-i));
        for(int i=0;i< persons.length;i++)
            System.out.println(persons[i].toString());
        System.out.println("\n---------------------------\n");


        Notebook notebook = new Notebook(persons[0], persons[1], persons[2], persons[3], persons[4]);
        System.out.println(notebook.toString());
        System.out.println("\n---------------------------\n");

        notebook.get_person(3).setSurname("Петров");
        notebook.get_person(1).setSurname("Сидоров");
        notebook.new_person(new Person("Петров", "Ибрагим", "Иванович 6"));
        notebook.new_person(new Person("Сидоров", "Ибрагим", "Иванович 7"));
        notebook.new_person(new Person("Петров", "Ибрагим", "Василиевич 8"));
        notebook.new_person(new Person("Сидоров", "Ибрагим", "Василиевич 9"));
        try{
            notebook.new_person(new Person("Имя", "Фамилия", "Отчество",
                    "8(800)-555-35-35", "666-666",
                    "Силач", "Город 1 Улица 1 Дом 1 Квартира 1 Комната 1 Шторка 1"));
        } catch (NumberException ex) {
            System.err.println(ex.getMessage());
            System.err.println("\nОчень жаль, но новый контакт не был добавлен.\n");
        }


        System.out.println(notebook.toString());
        System.out.println("\n---------------------------\n");

        FullNameComparator FNC = new FullNameComparator();
        notebook.sort(FNC);
        System.out.println(notebook.toString());
        System.out.println("\n---------------------------\n");

        ArrayList<Person> list =  notebook.found("Си");
        System.out.println(list.toString());


    }
}
