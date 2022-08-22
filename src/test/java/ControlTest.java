import classes.ControlClinico;
import classes.Control;
import classes.ControlLaboratorio;
import classes.ControlPcr;
import org.testng.annotations.Test;
import java.time.LocalDate;
import static org.testng.AssertJUnit.*;

public class ControlTest {
    private Control laboratorio = new ControlLaboratorio(LocalDate.now(), 50d);
    private Control clinico = new ControlClinico(LocalDate.now(), true);
    private Control pcr = new ControlPcr(LocalDate.now().plusWeeks(1), false);
    @Test
    void testingTimeToAddControls(){
        assertFalse(laboratorio.passedAWeek(clinico.getFecha()));
        assertFalse(clinico.passedAWeek(laboratorio.getFecha()));
        assertFalse(pcr.passedAWeek(laboratorio.getFecha()));
        assertTrue(laboratorio.passedAWeek(pcr.getFecha()));
    }
    @Test
    void testingLogicByMethods(){
        assertFalse(laboratorio.isPositive());
        assertTrue(clinico.isPositive());
        assertFalse(pcr.isPositive());
        assertTrue(new ControlPcr(LocalDate.now(), true).isPositive());
    }
}
