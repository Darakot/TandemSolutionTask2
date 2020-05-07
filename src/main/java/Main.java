import players.PalindromeGameControllerImpl;
import dto.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inCommand = new Scanner(System.in);
        Scanner inStr = new Scanner(System.in);
        PalindromeGameControllerImpl controller = new PalindromeGameControllerImpl();
//        Player player1 = new Player("D", "Дмитрий", 0);
//        Player player2 = new Player("K", "Ксения", 1);
//        controller.newPlayer("D", "Дмитрий");
//        controller.newPlayer("K", "Ксения");
//        controller.newPlayer("A","Антон");
//        controller.newPlayer("L","Лео");
//        controller.newPlayer("S","Сергей");
//        controller.newPlayer("A1","Антон");
//        controller.newPlayer("V","Вася");
//        controller.newPlayer("P","Петя");
//
//        controller.addVp("D",100);
//        controller.addVp("K",99);
//        controller.addVp("A",23);
//        controller.addVp("L",44);
//        controller.addVp("S",11);
//        controller.addVp("A1",101);
//        controller.addVp("V",200);
//        controller.addVp("P",300);
//
//        controller.leaderBoard();

//        controller.playGame(player1, "akka");
//        controller.playGame(player1, "akks");
//        controller.playGame(player1, "assssa");
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
