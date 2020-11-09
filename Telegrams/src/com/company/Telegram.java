package com.company;

import my_exceptions.NegativePrice;
import my_exceptions.WrongFormatText;
import my_interface.MoreWords;

public class Telegram implements MoreWords {
    final double word_price;
    public String text;
    public Telegram(double word_price, String text) throws NegativePrice, WrongFormatText {
        if(word_price<0)
            throw new NegativePrice();
        try {
            setText(text);
        }catch (WrongFormatText ex){
            throw ex;
        }
        this.word_price = word_price;

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

    public void setText(String text) throws WrongFormatText {
        text = text.toUpperCase();
        if(text.matches("[A-ZА-ЯЁ,.\\s]*\\.\\s*")){
            text = text.replaceAll(",", " ЗПТ ");
            text = text.replaceAll("\\.", " ТЧК ");
            text = remove_prepositions(text);
            text = text.replaceAll("  ", " ");
            this.text = text;
        }
        else {
            throw new WrongFormatText();
        }
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

    public String remove_prepositions(String text){
        text = " " + text;
        // все предлоги взяты с Википедии
        String prepositions[] = {
                " БЕЗ ВЕДОМА ",
                " БЕЗО ",
                " БЛАГОДАРЯ ",
                " БЛИЗ ",
                " БЛИЗКО ОТ ",
                " В ВИДЕ ",
                " В ЗАВИСИМОСТИ ОТ ",
                " В ИНТЕРЕСАХ ",
                " В КАЧЕСТВЕ ",
                " В ЛИЦЕ ",
                " В ОТЛИЧИЕ ОТ ",
                " В ОТНОШЕНИИ ",
                " В ПАНДАН ",
                " В ПОЛЬЗУ ",
                " В ПРЕДДВЕРИИ ",
                " В ПРОДОЛЖЕНИЕ ",
                " В РЕЗУЛЬТАТЕ ",
                " В РОЛИ ",
                " В СВЯЗИ С ",
                " В СИЛУ ",
                " В СЛУЧАЕ ",
                " В СООТВЕТСТВИИ С ",
                " В ТЕЧЕНИЕ ",
                " В ЦЕЛЯХ ",
                " В ЧЕСТЬ ",
                " В ",
                " ВБЛИЗИ ",
                " ВВИДУ ",
                " ВГЛУБЬ ",
                " ВДОГОН ",
                " ВДОЛЬ ",
                " ВДОЛЬ ПО ",
                " ВЗАМЕН ",
                " ВКЛЮЧАЯ ",
                " ВКРУГ ",
                " ВМЕСТО ",
                " ВНЕ ",
                " ВНИЗУ ",
                " ВНУТРИ ",
                " ВНУТРЬ ",
                " ВО ИМЯ ",
                " ВО СЛАВУ ",
                " ВО ",
                " ВОВНУТРЬ ",
                " ВОЗЛЕ ",
                " ВОКРУГ ",
                " ВОПРЕКИ ",
                " ВОСЛЕД ",
                " ВПЕРЕДИ ",
                " ВПЛОТЬ ДО ",
                " ВПРЕДЬ ДО ",
                " ВРАЗРЕЗ ",
                " ВРОДЕ ",
                " ВСЛЕД ЗА ",
                " ВСЛЕД ",
                " ВСЛЕДСТВИЕ ",
                " ВСТРЕЧУ ",
                " ВЫКЛЮЧАЯ ",
                " ДЛЯ ",
                " ДЛЯ-РАДИ ",
                " ДО ",
                " ЗА ВЫЧЕТОМ ",
                " ЗА ИСКЛЮЧЕНИЕМ ",
                " ЗА СЧЁТ ",
                " ЗА ",
                " ЗАМЕСТ ",
                " ЗАМЕСТО ",
                " ИЗ-ЗА ",
                " ИЗ-ПОД ",
                " ИЗ-ПОДО ",
                " ИЗНУТРИ ",
                " ИЗО ",
                " ИСКЛЮЧАЯ ",
                " ИСХОДЯ ИЗ ",
                " КАСАЕМО ",
                " КАСАТЕЛЬНО ",
                " КО ",
                " КОНЧАЯ ",
                " КРОМЕ ",
                " КРУГОМ ",
                " ЛИЦОМ К ЛИЦУ С ",
                " МЕЖ ",
                " МЕЖДУ ",
                " МИМО ",
                " НА БЛАГО ",
                " НА ВИДУ У ",
                " НА ГЛАЗАХ У ",
                " НА ПРЕДМЕТ ",
                " НАВЕРХУ ",
                " НАВРОДЕ ",
                " НАВСТРЕЧУ ",
                " НАД ",
                " НАДО ",
                " НАЗАД ",
                " НАЗАДИ ",
                " НАЗЛО ",
                " НАКАНУНЕ ",
                " НАМЕСТО ",
                " НАПЕРЕКОР ",
                " НАПЕРЕРЕЗ ",
                " НАПЕРЕХВАТ ",
                " НАПОДОБИЕ ",
                " НАПОДОБЬЕ ",
                " НАПРОТИВ ",
                " НАРЯДУ С ",
                " НАСУПРОТИВ ",
                " НАСЧЁТ ",
                " НАЧИНАЯ С ",
                " НЕ БЕЗ ",
                " НЕ СЧИТАЯ ",
                " НЕВЗИРАЯ НА ",
                " НЕДАЛЕКО ОТ ",
                " НЕЗАВИСИМО ОТ ",
                " НЕСМОТРЯ ",
                " НЕСМОТРЯ НА ",
                " НИЖЕ ",
                " ОБ ",
                " ОБО ",
                " ОБОК ",
                " ОБОЧЬ ",
                " ОКОЛО ",
                " ОКРЕСТ ",
                " ОКРОМЕ ",
                " ОКРОМЯ ",
                " ОКРУГ ",
                " ОПОСЛЯ ",
                " ОПРИЧЬ ",
                " ОТНОСИТЕЛЬНО ",
                " ОТО ",
                " ПЕРЕД ",
                " ПЕРЕДО ",
                " ПО ЛИНИИ ",
                " ПО МЕРЕ ",
                " ПО НАПРАВЛЕНИЮ К ",
                " ПО ПОВОДУ ",
                " ПО ПРИЧИНЕ ",
                " ПО СЛУЧАЮ ",
                " ПО СРАВНЕНИЮ С ",
                " ПО-ЗА ",
                " ПО-НАД ",
                " ПО-ПОД ",
                " ПОБЛИЗОСТИ ОТ ",
                " ОТ ИМЕНИ ",
                " ОТ ЛИЦА ",
                " ПОВДОЛЬ ",
                " ПОВЕРХ ",
                " ПОД ВИДОМ ",
                " ПОД ЭГИДОЙ ",
                " ПОДЛЕ ",
                " ПОДО ",
                " ПОДОБНО ",
                " ПОЗАДИ ",
                " ПОЗАДЬ ",
                " ПОЗДНЕЕ ",
                " ПОМИМО ",
                " ПОПЕРЁД ",
                " ПОПЕРЁК ",
                " ПОРЯДКА ",
                " ПОСВЕРХУ ",
                " ПОСЕРЕДИ ",
                " ПОСЕРЕДИНЕ ",
                " ПОСЕРЁДКЕ ",
                " ПОСЕРЕДЬ ",
                " ПОСЛЕ ",
                " ПОСРЕДИ ",
                " ПОСРЕДИНЕ ",
                " ПОСРЕДСТВОМ ",
                " ПРЕД ",
                " ПРЕДО ",
                " ПРЕЖ ",
                " ПРЕЖДЕ ",
                " ПРИ ПОМОЩИ ",
                " ПРИМЕНИТЕЛЬНО К ",
                " ПРИ ",
                " ПРО ",
                " ПРОМЕЖ ",
                " ПРОМЕЖДУ ",
                " ПРОТИВ ",
                " ПРОТИВНО ",
                " ПРОТИВУ ",
                " ПУТЁМ ",
                " РАДИ ",
                " РЯДОМ С ",
                " С ВЕДОМА ",
                " С ПОМОЩЬЮ ",
                " С ПРИЦЕЛОМ НА ",
                " С ТОЧКИ ЗРЕНИЯ ",
                " С ЦЕЛЬЮ ",
                " СВЕРХ ",
                " СВЕРХУ ",
                " БЕЗ ",
                " ИЗ ",
                " К ",
                " НА ",
                " О ",
                " ОТ ",
                " ПО ",
                " ПОД ",
                " С ",
                " У "};
        for(String pr: prepositions){
            while(text.lastIndexOf(pr) > -1)
                text = text.replaceAll(pr, " ");
        }
        return text;
    }

    @Override
    public boolean is_more(Telegram telegram)
    {
        if(count_of_words() > telegram.count_of_words())
            return true;
        return false;
    }

    @Override
    public boolean is_more(Urgent urgent)
    {
        if(count_of_words() > urgent.count_of_words())
            return true;
        return false;
    }

    @Override
    public boolean is_more(Artistic artistic)
    {
        if(count_of_words() > artistic.count_of_words())
            return true;
        return false;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if( !(obj instanceof Telegram) )
            return false;
        Telegram telegram = (Telegram)obj;
        if(this.text.equals(telegram.text))
            return true;
        return false;
    }

    @Override
    public String toString(){
        return text;
    }
}
