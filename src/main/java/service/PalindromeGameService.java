package service;

import dto.Player;
import repo.PlayerRepo;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Сервисный слой в котором находится логика обработки комманд контроллера
 */
public class PalindromeGameService {
    private PlayerRepo playerRepo = new PlayerRepo();

    public PalindromeGameService() {
    }

    /**
     * Возвращает мапу с 5 лидерами по очкам, или меньше чем 5
     * Зачем возврать мапу а не вывести все в forEach мне сейчас тоже интересно)Возможно переделаю
     * @return - Мара где ключ это ник, а значение колиство очков победы
     */
    public Map<String, Integer> leaderBoard() {
        return playerRepo.getPlayers().stream()
                .sorted((p1, p2) -> {
                    if (p1.getVp() > p2.getVp()) return -1;
                    if (p1.getVp() < p2.getVp()) return 1;
                    return 0;
                })
                .limit(5)
                .collect(Collectors.toMap(Player::getNick, Player::getVp));
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
     * Сам процесс игры если str является палиндром и его нет в списке палиндром игрока то добавляем его туда
     * и начисляем очки победы
     * @param player - игрок
     * @param str - слово палиндр
     */
    public void playGame(Player player, String str) {
        if (!isPalindrome(str)) {
            System.out.println(String.format("%s не является палиндром",str));
        }else if(player.getPalindromes().contains(str)) {
            System.out.println(String.format("Вы уже вводили слово %s",str));
        } else {
            Player p = playerRepo.getPlayers()
                    .get(player.getId());
            p.addVp(str.length());
            p.addPalindrome(str);
            System.out.println(String.format("%s получил %s. Общий счет %s",p.getNick(),str.length(),p.getVp()));
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
     */
    public void allPlayers() {
        playerRepo.getPlayers().forEach(player -> {
            System.out.println(player.getNick() + " " + player.getName());
        });
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
                    .filter(p -> p.getName().equals(nick))
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
                    .filter(p -> p.getName().equals(nick))
                    .findFirst()
                    .get()
                    .getVp();
        }catch (NoSuchElementException ignored){
            System.out.println(String.format("Пользователя с ником %s не существует",nick));
        }

        return Vp;
    }
}
