package com.company;

import my_exceptions.NegativePrice;
import my_exceptions.WrongFormatText;

public class Artistic extends Telegram{
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
    public double price(){
        return super.price()+blank_price;
    }
}