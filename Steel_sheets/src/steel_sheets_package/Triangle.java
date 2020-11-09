package steel_sheets_package;

import my_exceptions.NotPositive;
import my_exceptions.WrongTriangle;

public class Triangle extends SteelSheet{
    protected final double a, b, c; /* стороны треугольника */

    public Triangle(double density, double thickness, double a, double b, double c) throws NotPositive, WrongTriangle {
        super(density, thickness);
        if(a<0 || b<0 || c<0)
            throw new NotPositive();
        if(a+b<=c || a+c<=b || b+c<=a)
            throw new WrongTriangle();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle(Triangle t){
        super(t);
        this.a = t.a;
        this.b = t.b;
        this.c = t.c;
    }

    @Override
    public double area(){
        double p = (a+b+c)/2;
        return Math.sqrt( p * (p-a) * (p-b) * (p-c) );
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    @Override
    public String toString(){
        return "  Тип: треугольник\n"+super.toString()+"  1сторона:"+a+"мм\n" +
                "  2сторона: "+b+"мм\n  3сторона: "+c+"мм\n";
    }
}
