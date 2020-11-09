package com.company;

import my_exceptions.NegativePrice;
import my_exceptions.WrongFormatText;
import my_interface.MoreWords;

public class Artistic extends Telegram implements MoreWords {
    final double blank_price;

    public Artistic (double word_price, double blank_price, String text) throws NegativePrice, WrongFormatText {
        super(word_price, text);
        if(blank_price < 0)
            throw new NegativePrice();
        this.blank_price = blank_price;
    }

    public Artistic(Artistic artistic) throws NegativePrice, WrongFormatText {
        super(artistic);
        this.blank_price = artistic.blank_price;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if( !(obj instanceof Artistic) )
            return false;
        Artistic artistic = (Artistic)obj;
        if(this.text.equals(artistic.text))
            return true;
        return false;
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
    public double price(){
        return super.price()+blank_price;
    }
}