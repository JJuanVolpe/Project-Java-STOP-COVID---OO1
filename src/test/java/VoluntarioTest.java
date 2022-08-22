import classes.*;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VoluntarioTest {

    @Test
    void testEquals() {
        Voluntario voluntario = new Voluntario("Ismael", "12897600", "M", 39, "00000");
        Voluntario voluntario1 = new Voluntario("José", "12123251", "M", 31, "00001");
        assertNotEquals(voluntario, voluntario1);
        assertEquals(voluntario1, new Voluntario("Pedro", "1231312", "F", 34, "00001"));
    }

    @Test
    void getByAge() {
        Voluntario voluntario = new Voluntario("Ismael", "12897600", "M", 39, "00000");
        Voluntario voluntario1 = new Voluntario("José", "12123251", "M", 31, "00001");
        assertTrue(voluntario.getByAge(38, 39));
        assertTrue(voluntario1.getByAge(31, 32));
        assertFalse(voluntario.getByAge(31, 38));
    }
}
