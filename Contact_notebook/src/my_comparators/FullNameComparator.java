package my_comparators;

import notebook_package.Person;

import java.util.Comparator;

public class FullNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2){
        return p1.get_full_name().compareTo(p2.get_full_name());
    }
}
