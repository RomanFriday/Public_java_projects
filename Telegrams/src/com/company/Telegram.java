package com.company;

import my_exceptions.NegativePrice;
import my_exceptions.WrongFormatText;

public class Telegram {
    final double word_price;
    public String text;
    public Telegram(double word_price, String text) throws NegativePrice, WrongFormatText {
        text = text.toUpperCase();
        if(word_price<0)
            throw new NegativePrice();
        this.word_price = word_price;
        if(text.matches("[A-ZА-ЯЁ,.\\s]*\\.\\s*")){
            text = text.replaceAll(",", " ЗПТ ");
            text = text.replaceAll("\\.", " ТЧК ");
            text = text.replaceAll("  ", " ");
            this.text = text;
        }
        else {
            throw new WrongFormatText();
        }
    }

    public Telegram(Telegram t){
        this.text = t.text;
        this.word_price = t.word_price;
    }

    public double getWord_price() {
        return word_price;
    }

    public String getText() {
        return text;
    }

    public int count_of_words(){
        int count=0;
        for(int i=0, l = text.length(); i<l; i++)
        {
            while((i<l) && (text.charAt(i)==' '||text.charAt(i)=='\t'||text.charAt(i)=='\n'))
                i++;
            if(i>=l)
                return count;
            count++;
            while((i<l) && !(text.charAt(i)==' '||text.charAt(i)=='\t'||text.charAt(i)=='\n'))
                i++;
        }
        return count;
    }

    public double price()
    {
        return count_of_words() * word_price;
    }

    @Override
    public String toString(){
        return text;
    }
}
