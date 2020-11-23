package my_exceptions;

public class SquareNotRectangle extends Exception{
    String s = "По указу свыше квадрат не прямоугольник. Ошибка: ширина равна высоте ";
    public SquareNotRectangle(){}
    @Override
    public String toString(){
        return s;
    }
    @Override
    public String getMessage(){
        return s;
    }
}
