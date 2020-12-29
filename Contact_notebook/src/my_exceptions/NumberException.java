package my_exceptions;

public class NumberException extends Exception {
    String s = "\nНомер должен содержать только цифры и спец. символы: \" * + # \" .\n";
    public NumberException() { }
    @Override
    public String toString() { return s; }
    @Override
    public String getMessage() { return s; }
}
