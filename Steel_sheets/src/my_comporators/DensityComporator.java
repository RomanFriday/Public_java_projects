package my_comporators;

import steel_sheets_package.SteelSheet;

import java.util.Comparator;

public class DensityComporator implements Comparator<SteelSheet> {
    @Override
    public int compare(SteelSheet ss1, SteelSheet ss2){
        double d1 = ss1.getDensity();
        double d2 = ss2.getDensity();
        if(d1>d2)
            return 1;
        if(d1==d2)
            return 0;
        return -1;
    }
}
