package my_exceptions;

public class NotPositive extends Exception{
    String s = "Введено не положительное число ";
    public NotPositive(){}
    @Override
    public String toString(){
        return s;
    }
    @Override
    public String getMessage(){
        return s;
    }
}
