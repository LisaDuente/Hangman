import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldReturnAnException() {
        //input
        String tooMuchLetters = "sdf";
        Game game = new Game();

        //when
        game.update(tooMuchLetters);

        //result
        assertEquals("Endast en bokstav i taget!",game.getException());
    }

    @Test
    void selectGameWord() {
    }

    @Test
    void setFillInWord() {
    }

    @Test
    void manageWrongCharacters() {
    }

    @Test
    void getFillInWordAsString() {
    }

    @Test
    void getWrongCharacters() {
    }

    @Test
    void getWrongCount() {
    }
}