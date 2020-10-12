package my_exceptions;

public class ValueOutsideSet extends Exception{
    String s = "Введённое значение вне заданного промежутка ";
    public ValueOutsideSet(){};
    public ValueOutsideSet(int min, int max){s += "("+min+";"+max+")";}
    public ValueOutsideSet(int value, int min, int max){s += " "+value+" вне ("+min+";"+max+")";}
    @Override
    public String getMessage(){ return s;}
    @Override
    public String toString() {
        return s;
    }
}
