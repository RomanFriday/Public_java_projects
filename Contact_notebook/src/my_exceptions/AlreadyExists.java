package my_exceptions;

public class AlreadyExists extends Exception{
    String s = "\nЭлемент уже существует\n";
    public AlreadyExists(){}
    @Override
    public String toString(){return s;}
    @Override
    public String getMessage(){return s;}
}

