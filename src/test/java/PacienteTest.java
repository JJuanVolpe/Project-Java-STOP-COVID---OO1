import classes.ControlClinico;
import classes.ControlLaboratorio;
import classes.Paciente;
import classes.ControlPcr;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PacienteTest {

    @Test
    public void realizarControlPaciente(){
        Paciente p = new Paciente("Jose", "M", "12", 20, "0019");
        p.agregarControl(new ControlClinico(LocalDate.now(), false));
        assertTrue(p.noTieneControlesPositivos());
        assertEquals(1, p.getControles().size());

        p.agregarControl(new ControlClinico(LocalDate.now().plusWeeks(1), true));
        assertFalse(p.noTieneControlesPositivos());
    }

    @Test
    public void getControlsByWeeks(){
        Paciente p = new Paciente("Jose", "M", "12", 20, "0019");
        addLabControls(p);
        assertEquals(3, p.getControlsLabByTime(true).size());
        assertEquals(6, p.getControlsLabByTime(false).size());
    }

    @Test
    public void hasControlesPositivos(){
        Paciente p = new Paciente("Jose", "M", "12", 20, "0019");
        addLabControls(p);
        p.agregarControl(new ControlClinico(LocalDate.now().plusWeeks(0), false));
        assertTrue(p.noTieneControlesPositivos());
        p.agregarControl(new ControlClinico(LocalDate.now().plusWeeks(6), true));
        assertFalse(p.noTieneControlesPositivos());
    }

    @Test
    public void booleanReturnByControls(){
        Paciente p = new Paciente("Jose", "M", "12", 20, "0019");
        addLabControls(p);
        p.agregarControl(new ControlPcr(LocalDate.now().plusWeeks(6), true));
        assertTrue(p.poseePcrPositivo());
        assertFalse(p.poseeClinicoPositivo());
        assertTrue(p.poseeControlLaboratorio());
    }

    private void addLabControls(Paciente p) {
        p.agregarControl(new ControlLaboratorio(LocalDate.now(),0d));
        p.agregarControl(new ControlLaboratorio(LocalDate.now().plusWeeks(1),10d));
        p.agregarControl(new ControlLaboratorio(LocalDate.now().plusWeeks(2),20d));
        p.agregarControl(new ControlLaboratorio(LocalDate.now().plusWeeks(3),30d));
        p.agregarControl(new ControlLaboratorio(LocalDate.now().plusWeeks(4),40d));
        p.agregarControl(new ControlLaboratorio(LocalDate.now().plusWeeks(5),60d));
    }
}
