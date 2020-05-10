package service;

import dto.Player;
import repo.PlayerRepo;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Сервисный слой в котором находится логика обработки комманд контроллера
 */
public class PalindromeGameService {
    private static PalindromeGameService instance;
    private PlayerRepo playerRepo = new PlayerRepo();

    private PalindromeGameService() {
    }

    public static PalindromeGameService getInstance(){
        if(instance == null){
            instance = new PalindromeGameService();
        }
        return instance;
    }

    /**
     * Возвращает мапу с 5 лидерами по очкам, или меньше чем 5
     * Зачем возврать мапу а не вывести все в forEach мне сейчас тоже интересно)Возможно переделаю
     * @return - Мара где ключ это ник, а значение колиство очков победы
     */
    public Player[] leaderBoard() {
        return playerRepo.getPlayers().stream()
                .sorted((p1, p2) -> {
                    if (p1.getVp() > p2.getVp()) return -1;
                    if (p1.getVp() < p2.getVp()) return 1;
                    return 0;
                })
                .limit(5)
                .toArray(Player[]::new);
    }

    /**
     * Создание нового пользователя и запись его в класс репозиторий
     * @param nick - ник игрока
     * @param name - имя игрока
     * @return - возвращает 1 если пользователь успешно создан и 0 если он уже существует или не получилось добавить в лист
     */
    public boolean newPlayer(String nick, String name) {
        if (playerRepo.allNickNamesPlayers().contains(nick)) {
            return false;
        }

        return playerRepo.addNewPlayer(nick, name);
    }

    /**
     * Создание нового пользователя и запись его в класс репозиторий
     * @param nick - ник игрока
     * @param name - имя игрока
     * @return - возвращает 1 если пользователь успешно создан и 0 если он уже существует или не получилось добавить в лист
     */
    public boolean newPlayerTest(String nick, String name, int vp) {
        if (playerRepo.allNickNamesPlayers().contains(nick)) {
            return false;
        }

        return playerRepo.newPlayerTest(nick, name,vp);
    }
    /**
     * Удаление игрока
     * @param nick - ник игрока
     * @return - возвращает 1 если пользователь успешно удален и 0 если игрока с таким ником нет
     */
    public boolean deletePlayerTest(String nick) {
        if (playerRepo.allNickNamesPlayers().contains(nick)) {
            return playerRepo.deletePlayer(nick);
        }

        return false;
    }

    /**
     * Сам процесс игры если str является палиндром и его нет в списке палиндром игрока то добавляем его туда
     * и начисляем очки победы
     * @param player - игрок
     * @param str - слово палиндр
     */
    public String playGame(Player player, String str) {
        if (!isPalindrome(str)) { return String.format("%s не является палиндром",str);
        }else if(player.getPalindromes().contains(str)) { return String.format("Вы уже вводили слово %s",str);
        } else {
            Player p = playerRepo.getPlayers()
                    .get(player.getId());
            p.addVp(str.length());
            p.addPalindrome(str);
            return String.format("%s получил %s. Общий счет %s",p.getNick(),str.length(),p.getVp());
        }
    }

    /**
     * Проверка является ли слово палиндром
     * @param str - слово, которое может быть палиндромом
     * @return - возвращает 1 если слово палиндром и 0 если нет
     */
    private boolean isPalindrome(String str) {
        String strToLowerCase = str.replaceAll(" ", "").toLowerCase();
        return strToLowerCase.equals(new StringBuilder(strToLowerCase).reverse().toString());
    }

    /**
     * Выводит на экран всех игроков которые зарегисрировались
     * @return - лист со всем игроками
     */
    public List<Player> allPlayers() {
        return playerRepo.getPlayers();
    }

    /**
     * Находит в репо класс игрока по нику
     * @param nick - ник игрока
     * @return - возвращает нужны класс Player
     */
    public Player getPlayer(String nick) {
        Player player = null;
        try {
            player=playerRepo.getPlayers().stream()
                    .filter(p -> p.getNick().equals(nick))
                    .findFirst()
                    .get();
        }catch (NoSuchElementException ignored){
            System.out.println(String.format("Пользователя с ником %s не существует",nick));
        }
        return player;
    }

    /**
     * Показывает текущее количество очков победы у конкретного игрока
     * @param nick - ник игрока
     * @return - возвращает количество очков победы у конкретного игрока
     */
    public int getVpPlayer(String nick) {
        int Vp = -1;
        try {
            Vp=playerRepo.getPlayers().stream()
                    .filter(p -> p.getNick().equals(nick))
                    .findFirst()
                    .get()
                    .getVp();
        }catch (NoSuchElementException ignored){
            System.out.println(String.format("Пользователя с ником %s не существует",nick));
        }

        return Vp;
    }
}
