package com.company;

import my_exceptions.NegativePrice;
import my_exceptions.WrongFormatText;
import my_interface.MoreWords;

public class Urgent extends Telegram implements MoreWords {

    public Urgent (double word_price, String text) throws NegativePrice, WrongFormatText {
        super(word_price, "МОЛНИЯ " + text);
    }

    public Urgent(Urgent urgent) throws NegativePrice, WrongFormatText {
        super(urgent);
    }

    @Override
    public boolean is_more(Telegram telegram) {
        return super.is_more(telegram);
    }

    @Override
    public boolean is_more(Urgent urgent) {
        return super.is_more(urgent);
    }

    @Override
    public boolean is_more(Artistic artistic) {
        return super.is_more(artistic);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if( !(obj instanceof Urgent) )
            return false;
        Urgent urgent = (Urgent)obj;
        if(this.text.equals(urgent.text))
            return true;
        return false;
    }
}
