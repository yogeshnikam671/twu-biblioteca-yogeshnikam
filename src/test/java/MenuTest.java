import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    @Test
    void shouldBeAbleToDisplayOptions() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Menu menu = new Menu();

        menu.display();

        assertEquals("1 List of books\n2 Quit the application\n", outContent.toString());
    }
}