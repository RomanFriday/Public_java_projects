package my_exceptions;

public class WrongFormatText extends Exception{
    String s = "Некорректный формат текста\n";
    public WrongFormatText(){}
    @Override
    public String toString(){return s;}
    @Override
    public String getMessage(){return s;}
}