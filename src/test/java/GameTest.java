import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
  private Game game;

    @BeforeEach
    void setUp() {
        this.game = new Game(); // Initialize the Game object before each test method
    }

    @Test
    public void canSplitCoordinatesFromInput(){
        String input = "[4,2]";
        String[] actual = game.splitCoordinates(input);
        String[] expected = {"4","2"};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void canExtractCoordinatesFromInputForFlag(){
        String input = "F[2,3]";
        int[] actual = game.extractValues(input);
        int[] expected = {2 ,3};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void canExtractCoordinatesFromInputForRemoveFlag(){
        String input = "-F[7,5]";
        int[] actual = game.extractValues(input);
        int[] expected = {7 ,5};
        Assertions.assertArrayEquals(expected, actual);
    }

}


