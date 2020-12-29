package my_exceptions;

public class FileIsOver extends Exception{
    String s = "\nФайл закончился\n";
    public FileIsOver(){}
    @Override
    public String toString(){return s;}
    @Override
    public String getMessage(){return s;}

}

