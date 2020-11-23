package my_comporators;

import steel_sheets_package.SteelSheet;

import java.util.Comparator;

public class AreaComporator implements Comparator<SteelSheet> {
    @Override
    public int compare(SteelSheet ss1, SteelSheet ss2){
        double a1 = ss1.area();
        double a2 = ss2.area();
        if(a1>a2)
            return 1;
        if(a1==a2)
            return 0;
        return -1;
    }
}
