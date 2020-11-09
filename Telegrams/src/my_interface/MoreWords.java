package my_interface;

import com.company.Artistic;
import com.company.Telegram;
import com.company.Urgent;

public interface MoreWords {
    boolean is_more(Telegram telegram);
    boolean is_more(Urgent urgent);
    boolean is_more(Artistic artistic);
}
