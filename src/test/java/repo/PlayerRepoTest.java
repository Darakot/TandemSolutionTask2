package repo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Тесты для репо
 * Для начала заполняем репозиторий данными
 * тестируем 2 метода
 * allNickNamesPlayers - должен возвратить список всех ников у всех игроков
 * addNewPlayerAndDelete - тестирует успешное удаление и добавление игрока
 */
public class PlayerRepoTest {
    private List<String> playersNicks = new ArrayList<>();
    private PlayerRepo playerRepo = new PlayerRepo();

    @Before
    public void createPlayers(){
        playerRepo.addNewPlayer("D", "Данил");
        playerRepo.addNewPlayer("K", "Ксения");
        playerRepo.addNewPlayer("A", "Антон");
        playerRepo.addNewPlayer("L", "Лео");
        playerRepo.addNewPlayer("S", "Сергей");
        playerRepo.addNewPlayer("A1", "Антон");
        playerRepo.addNewPlayer("V", "Вася");
        playerRepo.addNewPlayer("P", "Петя");

        playersNicks.add("D");
        playersNicks.add("K");
        playersNicks.add("A");
        playersNicks.add("L");
        playersNicks.add("S");
        playersNicks.add("A1");
        playersNicks.add("V");
        playersNicks.add("P");
    }

    @Test
    public void allNickNamesPlayers() {
        Assert.assertEquals(playerRepo.allNickNamesPlayers(),playersNicks);
    }

    @Test
    public void addNewPlayerAndDelete() {
        Assert.assertTrue(playerRepo.addNewPlayer("F", "Федор"));
        Assert.assertTrue(playerRepo.deletePlayer("F"));
    }
}
