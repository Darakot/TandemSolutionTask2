package dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс dto для игроков
 * Храник список полидромов который вводил пользователь, очки победы, имя, ник и id
 * Можно потом перенисти в сущность для базы данных
 * Конструктор создает пользователя с ником, именем, id, пустым списком полидромов и 0 очков
 */

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

    public int getVp() {
        return vp;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getPalindromes() {
        return palindromes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", vp=" + vp +
                '}';
    }
}
