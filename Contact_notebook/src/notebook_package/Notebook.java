package notebook_package;

import my_comparators.FullNameComparator;
import my_exceptions.AlreadyExists;
import my_exceptions.NotExists;

import java.util.ArrayList;
import java.util.Comparator;

public class Notebook {
    protected ArrayList<Person> contacts;

    public Notebook(Person ...persons){
        contacts = new ArrayList<>();
        for (Person person: persons)
            contacts.add(new Person(person));
    }

    public Notebook(Notebook notebook) {
        contacts = new ArrayList<>();
        for(Person person: notebook.contacts)
            this.contacts.add(new Person(person));
    }

    public Person get_person(int index) {
        return contacts.get(index);
    }

    public int getSize(){
        return contacts.size();
    }

    public int get_person_index(Person person) throws NotExists {
        for(int i=0; i<getSize(); i++)
            if(person.equals(contacts.get(i)))
                return i;
        throw new NotExists(); // здесь решать проблему нельзя
    }

    public Person new_person(Person person) throws AlreadyExists {
        for (Person p: contacts)
            if(p.equals_name(person))
                throw new AlreadyExists(); // игнорировать или ошибка? - здесь решить проблему нельзя
        contacts.add(person);
        return person;
    }

    public Person remove(int index) {
        // удалить несуществующий можно, т.к. удалять нечего.
        // вернётся null
        if(index < 0 || index > contacts.size())
            return null;
        Person person = contacts.get(index);
        contacts.remove(index);
        return person;
    }

    public void sort(Comparator<Person> c) {
        contacts.sort(c);
    }

    public ArrayList<Person> found(String s){
        ArrayList<Person> founded = new ArrayList<Person>();
        if(s==null /*|| s.equals("")*/)
            return founded;
        for(Person person: contacts){
            if(person.get_full_name().contains(s) ||
                person.getGeneral_number().contains(s) || person.getMobile_number().contains(s) ||
                person.getStatus().contains(s) || person.getAddress().contains(s))
                founded.add(person);
        }
        founded.sort(new FullNameComparator());
        return founded;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Person person: contacts)
            sb.append(person.toString() + "\n");
        return sb.toString();
    }

}
