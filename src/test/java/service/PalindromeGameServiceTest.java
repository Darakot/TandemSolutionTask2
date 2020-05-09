package service;

import dto.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Тестируем сервисный слой
 * Для начала наполняем репо данными через сервисный слой
 * leaderBoard - тестируем доску лидеров, должен возвращать 5 игроков с наибольшим количество очков,в отсортировоном виде
 * newPlayer - тестирует успешное удаление и добавление игрока
 * playGame - тестируем сам процесс игры
 * у игры есть 3 варианта завершения:
 * 1) Слово является палиндромом и игроку начисляются очки
 * 2) Слово является палиндромо но в списке полиндромов игрока уже есть такой же палиндром, очки не начисляются
 * 3) Слово не является палиндромом
 * allPlayers - должен возвратить список всех игроков
 * getPlayer - тестируем получение конкретного игрока по нику
 * getVpPlayer - тестируем получение очков у конкретного игрока по нику
 */
public class PalindromeGameServiceTest {
    private List<Player> players = new ArrayList<>();
    private Player[] leaderBoard;
    PalindromeGameService palindromeGameService = new PalindromeGameService();

    @Before
    public void createPlayers(){
        leaderBoard = new Player[]{
                new Player("A1", "Антон",5,300),
                new Player("S", "Сергей",4,250),
                new Player("P", "Петя",7,210),
                new Player("K", "Ксения",1,200),
                new Player("D", "Данил",0,100)
        };

        palindromeGameService.newPlayerTest("D", "Данил",100);
        palindromeGameService.newPlayerTest("K", "Ксения",200);
        palindromeGameService.newPlayerTest("A", "Антон",10);
        palindromeGameService.newPlayerTest("L", "Лео",90);
        palindromeGameService.newPlayerTest("S", "Сергей",250);
        palindromeGameService.newPlayerTest("A1", "Антон",300);
        palindromeGameService.newPlayerTest("V", "Вася",20);
        palindromeGameService.newPlayerTest("P", "Петя",210);

        players.add(new Player("D", "Данил",0,100));
        players.add(new Player("K", "Ксения",1,200));
        players.add(new Player("A", "Антон",2,10));
        players.add(new Player("L", "Лео",3,90));
        players.add(new Player("S", "Сергей",4,250));
        players.add(new Player("A1", "Антон",5,300));
        players.add(new Player("V", "Вася",6,20));
        players.add(new Player("P", "Петя",7,210));
    }

    @Test
    public void leaderBoard() {
        Assert.assertEquals(Arrays.toString(leaderBoard), Arrays.toString(palindromeGameService.leaderBoard()));
    }

    @Test
    public void newPlayerAndDelete() {
        Assert.assertTrue(palindromeGameService.newPlayer("AS", "Данил"));
        Assert.assertTrue(palindromeGameService.deletePlayerTest("AS"));
    }

    @Test
    public void playGame() {
        String palindrome = "А роза упала на лапу Азора";
        String duplicate = "Алла";
        String isNotPalindrome = "Карантин";

        Player player = new Player("D", "Данил",0,100);
        player.getPalindromes().add(duplicate);

        String gameSuccess = String.format("%s получил %s. Общий счет %s",player.getNick(),
                palindrome.length(),
                player.getVp() + palindrome.length());
        String gameFailedIsNotPalindrome = String.format("%s не является палиндром",isNotPalindrome);
        String gameFailed = String.format("Вы уже вводили слово %s",duplicate);

        Assert.assertEquals(gameSuccess,palindromeGameService.playGame(player,palindrome));
        Assert.assertEquals(gameFailedIsNotPalindrome,palindromeGameService.playGame(player,isNotPalindrome));
        Assert.assertEquals(gameFailed,palindromeGameService.playGame(player,duplicate));
    }

    @Test
    public void allPlayers() {
        Assert.assertEquals(players.toString(),palindromeGameService.allPlayers().toString());
    }

    @Test
    public void getPlayer() {
        Player player = new Player("D", "Данил",0,100);
        Assert.assertEquals(player.toString(),palindromeGameService.getPlayer("D").toString());
    }

    @Test
    public void getVpPlayer() {
        Player player = new Player("D", "Данил",0,100);
        Assert.assertEquals(player.getVp(),palindromeGameService.getVpPlayer("D"));
    }
}
