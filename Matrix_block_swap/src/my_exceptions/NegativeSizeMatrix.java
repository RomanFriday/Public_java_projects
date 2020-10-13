package my_exceptions;

public class NegativeSizeMatrix extends Exception{
    String s = "Размер матрицы должен быть положительным\n";
    public NegativeSizeMatrix(){}
    @Override
    public String toString(){return s;}
    @Override
    public String getMessage(){return s;}
}
