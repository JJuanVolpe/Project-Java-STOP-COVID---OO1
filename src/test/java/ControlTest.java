import classes.Clinico;
import classes.Control;
import classes.Laboratorio;
import classes.Pcr;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class ControlTest {
    private Control laboratorio = new Laboratorio(LocalDate.now(), 50d);
    private Control clinico = new Clinico(LocalDate.now(), true);
    private Control pcr = new Pcr(LocalDate.now().plusWeeks(1), false);
    @Test
    void testingTimeToAddControls(){
        Assertions.assertFalse(laboratorio.passedAWeek(clinico.getFecha()));
        Assertions.assertFalse(clinico.passedAWeek(laboratorio.getFecha()));

        Assertions.assertFalse(pcr.passedAWeek(laboratorio.getFecha()));
        Assertions.assertTrue(laboratorio.passedAWeek(pcr.getFecha()));
    }
    @Test
    void testingLogicByMethods(){
        Assertions.assertFalse(laboratorio.isPositive());
        Assertions.assertTrue(clinico.isPositive());
        Assertions.assertFalse(pcr.isPositive());
        Assertions.assertTrue(new Pcr(LocalDate.now(), true).isPositive());
    }
}
