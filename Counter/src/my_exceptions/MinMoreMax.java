package my_exceptions;

public class MinMoreMax extends Exception{
    String s = "Минимальное значение не может превышать максимальное ";
    public MinMoreMax(){};
    public MinMoreMax(int min, int max){s += "("+min+">"+max+")";}
    @Override
    public String getMessage(){ return s;}
    @Override
    public String toString() {
        return s;
    }
}
