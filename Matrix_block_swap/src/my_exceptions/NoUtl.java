package my_exceptions;

public class NoUtl extends Exception{
    String s = "Не переданы утилиты\n";
    public NoUtl(){}
    @Override
    public String toString(){return s;}
    @Override
    public String getMessage(){return s;}

}
