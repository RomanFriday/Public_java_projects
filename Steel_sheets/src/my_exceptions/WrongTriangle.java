package my_exceptions;

public class WrongTriangle extends Exception{
    String s = "Треугольник с введёнными размерами не существует ";
    public WrongTriangle(){}
    @Override
    public String toString(){
        return s;
    }
    @Override
    public String getMessage(){
        return s;
    }
}
