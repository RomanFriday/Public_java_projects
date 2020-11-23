package steel_sheets_package;

import my_exceptions.NotPositive;

public class Square extends SteelSheet{
    protected final double a; /* длина стороны квадрата */

    public Square(double density, double thickness, double a)throws NotPositive {
        super(density, thickness);
        if(a<0)
            throw new NotPositive();
        this.a = a;
    }

    public Square(Square s){
        super(s);
        this.a = s.a;
    }

    @Override
    public double area(){
        return a*a;
    }

    @Override
    public double getA() {
        return a;
    }

    @Override
    public String toString(){
        return "  Тип: квадрат\n"+super.toString()+"  Сторона:"+a+"мм\n";
    }

}
