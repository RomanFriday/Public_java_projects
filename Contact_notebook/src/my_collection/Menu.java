package my_collection;

import my_comparators.*;
import my_exceptions.AlreadyExists;
import my_exceptions.NotExists;
import my_exceptions.NumberException;
import notebook_package.Notebook;
import notebook_package.Person;

import java.io.IOException;
import java.util.ArrayList;

public class Menu {
    public Menu(){}

    public boolean commands(Notebook notebook) {
        System.out.println("\n\n\n\n" + // чтобы отделялось от предыдущего экрана
                "Вывести список контактов............1\n" +
                "Создать новый контакт...............2\n" +
                "Выбрать контакт.....................3\n" +
                "Сортировать.........................4\n" +
                "Создать приглашение.................5\n" +
                "Выйти из программы..................6\n" +
                "\n Выберите команду: ");
        Utl u = new Utl();
        int command = u.get_type(6);
        switch(command) {
            case 1:
                System.out.println(notebook.toString());
                return true;
            case 2:
                try {
                    notebook.new_person(new_contact());
                }
                catch (AlreadyExists ex) {
                    System.err.println(ex.getMessage());
                }
                return true;
            case 3:
                choose_contact(notebook);
                return true;
            case 4:
                sort(notebook);
                return true;
            case 5:
                create_invitation(notebook);
                return true;
            default: return false;
        }
    }

    private Person new_contact(){
        Person person = new Person();
        while(true){
            Utl u = new Utl();
            System.out.println("\n\nВведите фамилию: ");
            person.setSurname(u.s.nextLine());

            System.out.println("\nВведите имя: ");
            person.setName(u.s.nextLine());

            System.out.println("\nВведите отчество: ");
            person.setPatronymic(u.s.nextLine());

            while (true) {
                System.out.println("\nВведите домашний телефон: ");
                try {
                    person.setGeneral_number(u.s.nextLine());
                } catch (NumberException ex) {
                    System.err.println(ex.getMessage());
                    continue;
                }
                break;
            }

            while (true) {
                System.out.println("\nВведите сотовый телефон: ");
                try {
                    person.setMobile_number(u.s.nextLine());
                } catch (NumberException ex) {
                    System.err.println(ex.getMessage());
                    continue;
                }
                break;
            }

            System.out.println("\nВведите статус: ");
            person.setStatus(u.s.nextLine());

            System.out.println("\nВведите адрес: ");
            person.setAddress(u.s.nextLine());

            if(person.is_empty())
            {
                System.err.println("Вы не задали ни одного параметра.\n");
                System.out.println("Ввести данные заново.....1\n" +
                                    "Выйти ...................2");
                if( u.get_type(2)==2 )
                    break;
            }
            else
                break;
        }
        return person;
    }

    private void choose_contact(Notebook notebook){
        ArrayList<Person> search = notebook.found("");
        if(search.size() == 0) {
            System.err.println("Нет контактов");
            return;
        }
        int index = 0;
        Utl u = new Utl();
        String str;
        while(true) { // запускаем поиск контакта
            System.out.println("Выберите из предложенного списка:\n");
            for (int i = 0; i < search.size(); i++)
                System.out.println((i+1) + "\n" + search.get(i).toString());
            System.out.println("Если хотите сузить поиск, введите "+(search.size()+1)+".\n");
            index = u.get_type(search.size()+1)-1;
            if(index == search.size())
            {
                System.out.println("Введите ключевое слово: ");
                u.s.nextLine(); // очищаем буфер ввода
                search = u.found(u.s.nextLine(), search);
            }
            else
                break;
            if(search.size()==0) // поиск не дал результатов, запускаем поиск заново
                search = notebook.found("");
            if(search.size()==1) {// контакт уникален - поиск завершён
                index = 0;
                break;
            }
        }
        int command;
        try { // ищем индекс найденного контакта в списке всех контактов
            index = notebook.get_person_index(search.get(index));
        }
        catch (NotExists ex) {
            System.err.println("НЕДОСТИЖИМАЯ СТРОКА КОДА");
        }
        System.out.println(notebook.get_person(index).toString()+"\n" +
                "Изменить адрес..............1\n" +
                "Изменить домашний телефон...2\n" +
                "Изменить сотовый телефон....3\n" +
                "Удалить контакт.............4\n" +
                "Отмена......................5\n" +
                "Введите номер команды: ");
        command = u.get_type(5);
       if (u.s.hasNextLine()) // очищаем буфер ввода
            str = u.s.nextLine();
        switch(command) {
            case 1:
                System.out.println("Введите новый адрес: ");
                notebook.get_person(index).setAddress(u.s.nextLine());
                return;
            case 2:
                while(true) {
                    System.out.println("\nВведите домашний телефон: ");
                    try{
                        notebook.get_person(index).setGeneral_number(u.s.nextLine());
                    }
                    catch (NumberException ex){
                        System.err.println(ex.getMessage());
                        continue;
                    }
                    return;
                }
            case 3:
                while(true) {
                    System.out.println("\nВведите сотовый телефон: ");
                    try{
                        notebook.get_person(index).setMobile_number(u.s.nextLine());
                    }
                    catch (NumberException ex){
                        System.err.println(ex.getMessage());
                        continue;
                    }
                    return;
                }
            case 4:
                notebook.remove(index);
            default:return;
        }

    }

    private void sort(Notebook notebook) {
        Utl u = new Utl();
        System.out.println("Сортировать по:\n"+
                "ФИО..................1\n" +
                "Домашний номер.......2\n" +
                "Сотовый номер........3\n" +
                "Адрес................4\n" +
                "Статус...............5\n" +
                "Отмена...............6\n" +
                "\n Выберите команду: ");

        switch (u.get_type(6))
        {
            case 1:
                notebook.sort(new FullNameComparator());
                break;
            case 2:
                notebook.sort(new GeneralNumberComparator());
                break;
            case 3:
                notebook.sort(new MobileNumberComparator());
                break;
            case 4:
                notebook.sort(new AddressComparator());
                break;
            case 5:
                notebook.sort(new StatusComparator());
                break;
            default:return;
        }
        System.out.println(notebook.toString());
        return;
    }

    private void create_invitation(Notebook notebook){
        System.out.println("Введите статус:");
        ArrayList<Person> list = new ArrayList<Person>();
        Utl u = new Utl();
        String status = u.s.nextLine().toLowerCase();
        for(int i=0; i<notebook.getSize(); i++)
            if(notebook.get_person(i).getStatus().contains(status))
                list.add(notebook.get_person(i));
        if(list.size()==0){
            System.out.println("Данным статусом никто не обладает");
            return;
        }
        list.sort(new FullNameComparator());
        System.out.println("Список приглашённых:\n" + list.toString());
        System.out.println("\n" +
                "День рождения......1\n" +
                "Годовщина..........2\n" +
                "Встреча............3\n" +
                "Рандеву............4\n" +
                "Первенец...........5\n" +
                "Другой тип.........6\n"+
                "Выберите тип: ");

        int type = u.get_type(6);
        Event event = new Event(type);



        for (int i=0;i<list.size();i++)
            create_letter(list.get(i), event);
    }


    private void create_letter(Person person, Event event) {
        Utl u = new Utl();
        String txt_name = u.unique_txt_file(person.get_full_name());
        try{
            u.setOut(txt_name);
        }catch (IOException ex){
            System.err.println("\nК сожалению, создать файл не удалось.\n");
            return;
        }

        boolean write_address = true;
        System.out.println("\nСообщение для: \n"+person.toString());
        System.out.println("Напечатать адерес в начале файла?\n" +
                " Да - 1     Нет - 0");
        if( u.get_int() == 0 )
            write_address = false;

        StringBuffer text = new StringBuffer();
        System.out.println("\nИспользовать шаблон?\n" +
                    " Да - 1     Нет - 0");
        String ls = System.getProperty("line.separator"); // перенос строки в файле
        if( u.get_int() != 0 && !event.equals(Event.Entry.ANOTHER_ENTRY))
               text = event.template_letter(event.entry, person);
        else
        {
            System.out.println("\n Введите текст сообщения. Для завершения текста введите пустую строку.\n");
            String str = u.s.nextLine(); // после get_int остаётся строка
            str = u.s.nextLine();
            while(!str.equals("")){
                text.append(str);
                text.append(ls);
                str= u.s.nextLine();
            }
        }
        try{
            if(write_address)
                u.out.write(person.getAddress()+ls);
            u.out.write(text.toString());
            u.out.close();
        } catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }
}
