package steel_sheets_package;

import my_exceptions.NotPositive;

public abstract class SteelSheet {
    protected final double density; /* плотность */
    protected final double thickness; /* толщина */

    public SteelSheet(double density, double thickness) throws NotPositive {
        if(density<0 || thickness<0)
            throw new NotPositive();
        this.density = density;
        this.thickness = thickness;
    }

    public SteelSheet(SteelSheet s){
        this.thickness = s.thickness;
        this.density = s.density;
    }

    public double getDensity() {
        return density;
    }

    public double getThickness() {
        return thickness;
    }

    public abstract double area();

    public abstract double getA();

    public double mass(){
        return density * thickness * area() / (1000*1000*1000);
    }

    @Override
    public String toString(){
        return "  Плотность:  "+density+"кг/м^3\n  Толщина: "+thickness+"мм\n";
    }
}
