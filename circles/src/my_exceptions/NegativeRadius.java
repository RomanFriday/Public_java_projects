package my_exceptions;

public class NegativeRadius extends Exception{
    String s = "Отрицательный радиус\n";
    public NegativeRadius(){}
    @Override
    public String toString(){return s;}
    @Override
    public String getMessage(){return s;}

}