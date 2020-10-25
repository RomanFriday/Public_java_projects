package com.company;

import my_exceptions.NegativeRadius;

public class Circle{
    double x0 = 0, y0 = 0, r = 0;

    public Circle(){}

    public Circle(double x0, double y0, double r) throws NegativeRadius {
        try{
        setR(x0);
        }
        catch (NegativeRadius ex){
            throw new NegativeRadius();
        }
        setX0(y0);
        setY0(r);
    }

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }

    public double getR() {
        return r;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public void setR(double r) throws NegativeRadius{
        if(r < 0)
            throw new NegativeRadius();
        this.r = r;
    }

    public double center_distance(Circle c){
        return Math.sqrt( (this.x0-c.getX0())*(this.x0-c.getX0()) + (this.y0-c.getY0())*(this.y0-c.getY0()) );
    }

    public boolean is_concentric(Circle c){
        if(this.x0 == c.getX0() && this.y0 == c.getY0())
            return true;
        return false;
    }

    public boolean is_concerning(Circle c){
        if(center_distance(c) == this.r + c.getR())
            return true;
        return false;
    }

    public boolean is_intersecting(Circle c){
        double c_d = center_distance(c);
        if( (c_d < this.r + c.getR()) && (c_d > Math.max(this.r, c.getR())) )
            return true;
        return false;
    }

    @Override
    public String toString(){
        return "(x - "+getX0()+")^2 + (y - "+getY0()+")^2 = ("+getR()+")^2";
    }

}
