package my_exceptions;

public class NoCommandsException extends Throwable {
    String s = "\nНевозможно создать меню без команд\n";
    public NoCommandsException(){}
    @Override
    public String toString(){return s;}
    @Override
    public String getMessage(){return s;}
}
