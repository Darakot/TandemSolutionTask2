package repo;

import dto.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс который хранит информацию о игроках в листе
 * Можно переделать под хранение в какой либо Базе данных заменив лист игроков на реализацию какой либо БД
 */
public class PlayerRepo {
    private List<Player> players;

    public PlayerRepo() {
        this.players = new ArrayList<>();
    }

    /**
     * Все ники игроков
     * @return - все ники, всех игроков которые хранятся в листе
     */
    public List<String> allNickNamesPlayers() {
        return players.stream()
                .map(Player::getNick)
                .collect(Collectors.toList());
    }

    /**
     * Добавляет нового пользователя
     * @param name - имя пользователя
     * @param nick - ник пользователя
     * @return - возращает 1 если пользователь создан успешно
     */
    public boolean addNewPlayer(String name, String nick){
        return players.add(new Player(name,nick,players.size()));
    }

    /**
     * Возращает список всех игроков
     * @return - список всех игроков
     */
    public List<Player> getPlayers() {
        return players;
    }
}
