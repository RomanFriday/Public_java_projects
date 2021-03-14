package my_comparators;

import notebook_package.Person;

import java.util.Comparator;

public class GeneralNumberComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2){
        return p1.getGeneral_number().compareTo(p2.getGeneral_number());
    }
}
