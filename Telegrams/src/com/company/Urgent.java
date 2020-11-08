package com.company;

import my_exceptions.NegativePrice;
import my_exceptions.WrongFormatText;

public class Urgent extends Telegram{

    public Urgent (double word_price, String text) throws NegativePrice, WrongFormatText {
        super(word_price, "МОЛНИЯ " + text);
    }

    public Urgent(Urgent urgent) throws NegativePrice, WrongFormatText {
        super(urgent);
    }
}
