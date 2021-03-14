package my_comparators;

import notebook_package.Person;

import java.util.Comparator;

public class MobileNumberComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2){
        return p1.getMobile_number().compareTo(p2.getMobile_number());
    }
}
