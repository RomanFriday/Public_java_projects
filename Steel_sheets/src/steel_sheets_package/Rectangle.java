package steel_sheets_package;

import my_exceptions.NotPositive;
import my_exceptions.SquareNotRectangle;

public class Rectangle extends SteelSheet{
    protected final double a, b; /* длина и ширина */

    public Rectangle(double density, double thickness, double a, double b) throws NotPositive, SquareNotRectangle {
        super(density, thickness);
        if(a<0 || b<0)
            throw new NotPositive();
        if(a==b)
            throw new SquareNotRectangle();
        this.a = a;
        this.b = b;
    }

    public Rectangle(Rectangle r){
        super(r);
        this.a = r.a;
        this.b = r.b;
    }

    @Override
    public double area(){
        return a*b;
    }

    @Override
    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    @Override
    public String toString(){
        return "  Тип: прямоугольник\n"+super.toString()+"  Ширина:"+a+"мм\n  Высота: "+b+"мм\n";
    }
}
