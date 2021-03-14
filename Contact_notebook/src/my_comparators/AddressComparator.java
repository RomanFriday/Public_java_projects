package my_comparators;

import notebook_package.Person;

import java.util.Comparator;

public class AddressComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2){
        return p1.getAddress().compareTo(p2.getAddress());
    }
}
