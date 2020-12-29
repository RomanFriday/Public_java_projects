package my_comparators;

import com.company.Circle;

import java.util.Comparator;

public class Y_comparator implements Comparator<Circle> {
    @Override
    public int compare(Circle c1, Circle c2){
        // нельзя использовать return (int) c1.getX0 - c2.getX0;
        // так как отличие может составлять в сотых, или тысячных,
        // а приведение к типу int отбросит дробную часть
        if(c1.getY0() > c2.getY0())
            return 1;
        if(c2.getY0() > c1.getY0())
            return -1;
        return 0;
    }

}