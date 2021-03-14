package my_exceptions;

public class NotExists extends Exception{
    String s = "\nЭлемента не существует\n";
    public NotExists(){}
    @Override
    public String toString(){return s;}
    @Override
    public String getMessage(){return s;}
}
