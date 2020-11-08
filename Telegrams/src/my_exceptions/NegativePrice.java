package my_exceptions;

public class NegativePrice extends Exception{
    String s = "Отрицательная цена\n";
    public NegativePrice(){}
    @Override
    public String toString(){return s;}
    @Override
    public String getMessage(){return s;}
}
