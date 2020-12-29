package notebook_package;

import my_exceptions.NumberException;

public class Person {
    protected String  surname, name, patronymic; // Фамилия Имя Отчество ( {"Иванов", "Иван", Иванович"} ... )
    protected String general_number, mobile_number; // Номера телефонов ( "    *110* +7 (915) - 666 - 13 - 42#" ... )
    protected String status; // статус ( друг, начальник, сантехник, сын маминой подруги ... )
    protected String address; // адрес ( "Союзная 144" ...)

    public Person() {
        name = surname = patronymic = "";
        general_number = mobile_number = "";
        status = "";
        address = "";
    }

    public Person(String surname, String name, String patronymic,
            String general_number, String mobile_number, String status, String address) throws NumberException {
        setSurname(surname);
        setName(name);
        setPatronymic(patronymic);
        try{
        setGeneral_number(general_number);
        setMobile_number(mobile_number);
        } catch (NumberException ex) {
            throw ex; // здесь решать проблему нельзя
        }
        setStatus(status);
        setAddress(address);
    }

    public Person(Person person) {
        setSurname(person.getSurname());
        setName(person.getName());
        setPatronymic(person.getPatronymic());
        try{
            setGeneral_number(person.getGeneral_number());
            setMobile_number(person.getMobile_number());
        } catch (NumberException ex) {
            System.err.println("НЕДОСТИЖИМАЯ СТРОКА КОДА");
        }
        setStatus(person.getStatus());
        setAddress(person.getAddress());
    }

    public Person(String surname, String name, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        general_number = mobile_number = "";
        status = "";
        address = "";
    }

    public Person(String full_name) {
        set_full_name(full_name);
        general_number = mobile_number = "";
        status = "";
        address = "";
    }

    public String get_full_name(){
        return surname + " " + name + " " + patronymic;
    }

    public void set_full_name(String str) {
       surname = str; // "Алебус Персиваль Вулфрик Брайан Дамблдор", "Саллах ад Дин" ...
       name = patronymic = "";
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getGeneral_number() {
        return general_number;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setGeneral_number(String general_number) throws NumberException {
        if(!general_number.matches("([\\s0-9+\\-*]*(\\([\\s0-9+\\-*]*\\))*)*"))
            throw new NumberException(); // здесь решать проблему нельзя
        this.general_number = general_number;
    }

    public void setMobile_number(String mobile_number) throws NumberException {
        if(!mobile_number.matches("([\\s0-9+\\-*]*(\\([\\s0-9+\\-*]*\\))*)*"))
            throw new NumberException(); // здесь решать проблему нельзя
        this.mobile_number = mobile_number;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if( !(obj instanceof Person) )
            return false;
        Person person = (Person)obj;
        if(surname.equals(person.surname) && name.equals(person.name) && patronymic.equals(person.patronymic))
                if(general_number.equals(person.general_number) && mobile_number.equals(person.mobile_number))
                    if(status.equals(person.status) && address.equals(person.address))
                        return true;
        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("");
        sb.append("\n" + surname + " " + name + " " + patronymic);
        sb.append("\n\tДомашний: " + general_number);
        sb.append("\n\tСотовый: " + mobile_number);
        sb.append("\n\tСтатус: " + status);
        sb.append("\n\tАдрес: " + address);
        sb.append("\n");
        return sb.toString();
    }
}
