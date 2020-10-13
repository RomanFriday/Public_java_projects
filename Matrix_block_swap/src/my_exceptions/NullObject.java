package my_exceptions;

public class NullObject extends Exception{
    String s = "Нулевой объект / объект не создан\n";
    public NullObject(){}
    @Override
    public String toString(){return s;}
    @Override
    public String getMessage(){return s;}
}
