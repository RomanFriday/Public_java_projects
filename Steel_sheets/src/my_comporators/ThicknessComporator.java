package my_comporators;

import steel_sheets_package.SteelSheet;

import java.util.Comparator;

public class ThicknessComporator implements Comparator<SteelSheet> {
    @Override
    public int compare(SteelSheet ss1, SteelSheet ss2){
        double t1 = ss1.getThickness();
        double t2 = ss2.getThickness();
        if(t1>t2)
            return 1;
        if(t1==t2)
            return 0;
        return -1;
    }
}
