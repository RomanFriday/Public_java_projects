package my_collection;

import notebook_package.Person;

public class Event {
    public enum Entry{ BIRTHDAY, ANNIVERSARY, MEETING, RENDEZVOUS, FIRSTBORN, ANOTHER_ENTRY};
    protected Entry entry;

    public Event(Entry entry){
        this.entry = entry;
    }

    public Event(int type){
        switch (type) {
            case 1: entry = Entry.BIRTHDAY; break;
            case 2: entry = Entry.ANNIVERSARY; break;
            case 3: entry = Entry.MEETING; break;
            case 4: entry = Entry.RENDEZVOUS; break;
            case 5: entry = Entry.FIRSTBORN; break;
            default: entry = Entry.ANOTHER_ENTRY;
        }
    }

    public StringBuffer template_letter(Entry entry, Person person) {
        StringBuffer sb = new StringBuffer();
        String ls = System.getProperty("line.separator"); // перенос строки в файле
        sb.append("\n       Дорогой " + person.get_full_name() + ls);
        if(entry == Entry.BIRTHDAY || entry == Entry.MEETING || entry == Entry.RENDEZVOUS)
        {
            sb.append(ls + "Приглашаю Вас ");
            switch(entry)
            {
                case BIRTHDAY:
                    sb.append("отпраздновать свой день рождения." + ls);
                    break;
                case MEETING:
                    sb.append("обсудить наши важные дела." + ls);
                    break;
                case RENDEZVOUS:
                    sb.append(" вести войска, куда условились в прошлый раз." + ls);
                    break;
            }
            sb.append("Жду нашей встречи." + ls);
        }
        else
        {
            sb.append("Поздравляю Вас с " + ls);
            switch(entry)
            {
                case ANNIVERSARY:
                    sb.append("годовщиной!" + ls + "Пусть пламя любови греет вас холодными ночами!" + ls);
                    break;
                case FIRSTBORN:
                    sb.append("первенцем!" + ls + "Пусть подгузник всегда будет под рукой в нужный момент!" + ls);
                    break;
            }
        }
        return sb;
    }



    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
