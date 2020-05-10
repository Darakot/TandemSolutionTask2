package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс dto для игроков
 * Храник список полидромов который вводил пользователь, очки победы, имя, ник и id
 * Можно потом перенисти в сущность для базы данных
 * Конструктор создает пользователя с ником, именем, id, пустым списком полидромов и 0 очков
 */

@Getter
@Setter
@ToString
public class Player {
    private int id;
    private String name;
    private String nick;
    private Integer vp;
    private List<String> palindromes;


    public Player(String nick, String name, int id) {
        this.nick = nick;
        this.name = name;
        this.id = id;

        this.vp = 0;
        this.palindromes = new ArrayList<>();
    }

    public Player(String name, String nick, int id, int vp) {
        this.id = id;
        this.name = name;
        this.nick = nick;
        this.vp = vp;

        this.palindromes = new ArrayList<>();
    }

    public void addVp(int length) {
        this.vp+=length;
    }

    public void addPalindrome(String palindrome) {
        this.palindromes.add(palindrome);
    }

    public String getVpStr() {
        return vp.toString();
    }
}
