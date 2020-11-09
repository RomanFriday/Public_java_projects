package my_comporators;

import steel_sheets_package.SteelSheet;

import java.util.Comparator;

public class MassComporator implements Comparator<SteelSheet>{
    @Override
    public int compare(SteelSheet ss1, SteelSheet ss2){
        double m1 = ss1.mass();
        double m2 = ss2.mass();
        if(m1>m2)
            return 1;
        if(m1==m2)
            return 0;
        return -1;
    }
}
