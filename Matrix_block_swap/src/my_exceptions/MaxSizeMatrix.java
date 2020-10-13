package my_exceptions;

public class MaxSizeMatrix extends Exception{
    String s = "Размер матрицы не должен превышать максимальный ";
    int max_size = 1;
    public MaxSizeMatrix(int max_size){this.max_size = max_size;}
    @Override
    public String toString(){return s+"("+max_size+")";}
    @Override
    public String getMessage(){return s+"("+max_size+")";}

}
