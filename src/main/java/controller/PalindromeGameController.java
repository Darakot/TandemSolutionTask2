package controller;

import dto.Player;

public interface PalindromeGameController {
    /**
     * Выводит 5 игроков с наибольшим количеством очков победы
     */
    void leaderBoard();

    /**
     * Создание нового пользователя и запись его в класс репозиторий
     * @param nick - ник игрока
     * @param name - имя игрока
     * @return - возвращает 1 если пользователь успешно создан и 0 если он уже существует или не получилось добавить в лист
     */
    boolean newPlayer(String name, String nick);

    /**
     * Сам процесс игры если str является палиндром и его нет в списке палиндром игрока то добавляем его туда
     * и начисляем очки победы
     * @param player - игрок
     * @param str - слово палиндр
     */
    void playGame(Player player,String str);

    /**
     * Выводит на экран всех игроков которые зарегисрировались
     */
    void allPlayers();

    /**
     * Находит в репо класс игрока по нику
     * @param nick - ник игрока
     * @return - возвращает нужны класс Player
     */
    Player getPlayer(String nick);
    /**
     * Показывает текущее количество очков победы у конкретного игрока
     * @param nick - ник игрока
     * @return - возвращает количество очков победы у конкретного игрока
     */
    int getVpPlayer(String nick);
}
