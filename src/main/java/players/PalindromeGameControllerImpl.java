package players;

import dto.Player;
import service.PalindromeGameService;

import java.util.Map;

/**
 * Слой контроллера которые выполняет комманды пришедшие от пользователя
 * Вся логика содержится в сервисном слое
 */
public class PalindromeGameControllerImpl implements PalindromeGameController {
    PalindromeGameService palindromeGameService = new PalindromeGameService();

    public PalindromeGameControllerImpl() {
    }

    /**
     * Выводит 5 игроков с наибольшим количеством очков победы
     */
    @Override
    public void leaderBoard() {
        palindromeGameService.leaderBoard().entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(System.out::println);
    }

    /**
     * Создание нового пользователя и запись его в класс репозиторий
     * @param nick - ник игрока
     * @param name - имя игрока
     * @return - возвращает 1 если пользователь успешно создан и 0 если он уже существует или не получилось добавить в лист
     */
    @Override
    public boolean newPlayer(String nick, String name) {
        return palindromeGameService.newPlayer(nick, name);
    }

    /**
     * Сам процесс игры если str является палиндром и его нет в списке палиндром игрока то добавляем его туда
     * и начисляем очки победы
     * @param player - игрок
     * @param str - слово палиндр
     */
    @Override
    public void playGame(Player player, String str) {
        palindromeGameService.playGame(player, str);
    }

    /**
     * Выводит на экран всех игроков которые зарегисрировались
     */
    @Override
    public void allPlayers() {
        palindromeGameService.allPlayers();
    }

    /**
     * Находит в репо класс игрока по нику
     * @param nick - ник игрока
     * @return - возвращает нужны класс Player
     */
    @Override
    public Player getPlayer(String nick) {
        return palindromeGameService.getPlayer(nick);
    }

    /**
     * Показывает текущее количество очков победы у конкретного игрока
     * @param nick - ник игрока
     * @return - возвращает количество очков победы у конкретного игрока
     */
    @Override
    public int getVpPlayer(String nick) {
        return palindromeGameService.getVpPlayer(nick);
    }
}
