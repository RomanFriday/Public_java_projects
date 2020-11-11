package my_exceptions;

public class InvalidMaximumValue extends Exception{
    String s = "Максимальное значение не может быть меньше ";
    int value;

    public InvalidMaximumValue(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return s+value+"\n";
    }

    @Override
    public String getMessage(){
        return s+value+"\n";
    }
}
