package com.company;

import my_exceptions.NegativeRadius;

public class Circle{
    double x0 = 0, y0 = 0, r = 0;

    public Circle(){}

    public Circle(double x0, double y0, double r) throws NegativeRadius {
        try{
          setR(r);
        }
        catch (NegativeRadius ex){
            throw new NegativeRadius();
        }
        setX0(x0);
        setY0(y0);
    }

    public Circle(Circle c){
        this.x0 = c.getX0();
        this.y0 = c.getY0();
        this.r = c.getR();
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
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if( !(obj instanceof Circle) )
            return false;
        Circle o = (Circle) obj;
        if(o.getX0() == this.x0 && o.getY0() == this.y0 && o.getR() == this.r)
            return true;
        return false;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("(x ");
        if(this.x0 < 0)
            s.append(" + "+(-this.x0));
        else
            s.append(" - "+this.x0);
        s.append(")^2 + (y ");
        if(this.y0 < 0)
            s.append(" + "+(-this.y0));
        else
            s.append(" - "+this.y0);
        s.append(")^2 = ("+this.r+")^2");
        return s.toString();
    }

}
