import controller.PalindromeGameControllerImpl;
import dto.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inCommand = new Scanner(System.in);
        Scanner inStr = new Scanner(System.in);
        PalindromeGameControllerImpl controller = new PalindromeGameControllerImpl();

        int command = 0;
        String nick;
        Player player = null;

        while (true) {
            System.out.println("1 сыграть " + "\n" +
                    "2 создать игрока " + "\n" +
                    "3 таблица результатов " + "\n" +
                    "4 узнатьк количестов очков " + "\n" +
                    "-1 выйти");
            command = inCommand.nextInt();
            if (command == -1) break;
            switch (command) {
                case 1:
                    System.out.println("Введите ник игрока кем будете играть?");
                    controller.allPlayers();
                    nick = inStr.nextLine();
                    player = controller.getPlayer(nick);
                    if(player!=null){
                        System.out.println("Введите слово которое является палиндром:");
                        String strPalindrome = inStr.nextLine();
                        controller.playGame(player,strPalindrome);
                    }
                    break;
                case 2:
                    System.out.println("Введите имя игрока");
                    String name = inStr.nextLine();
                    System.out.println("Введите nick игрока");
                    nick = inStr.nextLine();
                    if (controller.newPlayer(nick, name)) {
                        System.out.println(String.format("Игрок с именем %s и ником %s успешно создан", name, nick));
                    }
                    break;
                case 3:
                    controller.leaderBoard();
                    break;
                case 4:
                    System.out.println("У какого игрока вывести очки?");
                    controller.allPlayers();
                    nick = inStr.nextLine();
                    System.out.println(controller.getVpPlayer(nick));
                    break;
            }
        }


    }


}
