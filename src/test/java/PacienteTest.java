import classes.ControlClinico;
import classes.ControlLaboratorio;
import classes.Paciente;
import classes.ControlPcr;
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
    public void realizarControlPcr(){
        Paciente p = new Paciente("Jose", "M", "12", 20, "0019");
        p.agregarControl(new ControlClinico(LocalDate.now(), true));
        assertEquals(1, p.getControles().size());

        p.agregarControl(new ControlClinico(LocalDate.now(), true));
        assertEquals(1, p.getControles().size());   //No se agrega por el tiempo y tipo del mismo
        p.agregarControl(new ControlPcr(LocalDate.now(), true));
        assertEquals(2, p.getControles().size());   //Si se agrega, por ser pcr y tener cómo último un estudio clínico positivo
    }

    @Test
    public void getControlsByWeeks(){
        Paciente p = new Paciente("Jose", "M", "12", 20, "0019");
        addSevenLabControls(p);
        assertEquals(3, p.getControlsLabByTime(true).size());
        assertEquals(6, p.getControlsLabByTime(false).size());
    }

    @Test
    public void hasControlesPositivos(){
        Paciente p = new Paciente("Jose", "M", "12", 20, "0019");
        addSevenLabControls(p);
        p.agregarControl(new ControlClinico(LocalDate.now().plusWeeks(0), false));
        assertTrue(p.noTieneControlesPositivos());
        p.agregarControl(new ControlClinico(LocalDate.now().plusWeeks(7), true));
        assertFalse(p.noTieneControlesPositivos());
    }

    @Test
    public void booleanReturnByControls(){
        Paciente p = new Paciente("Jose", "M", "12", 20, "0019");
        addSevenLabControls(p);
        p.agregarControl(new ControlPcr(LocalDate.now().plusWeeks(7), true));
        assertTrue(p.poseePcrPositivo());
        assertFalse(p.poseeClinicoPositivo());
        assertTrue(p.poseeControlLaboratorio());
    }

    private void addSevenLabControls(Paciente p) {
        p.agregarControl(new ControlLaboratorio(LocalDate.now(),0d));
        p.agregarControl(new ControlLaboratorio(LocalDate.now().plusWeeks(1),10d));
        p.agregarControl(new ControlLaboratorio(LocalDate.now().plusWeeks(2),20d));
        p.agregarControl(new ControlLaboratorio(LocalDate.now().plusWeeks(3),30d));
        p.agregarControl(new ControlLaboratorio(LocalDate.now().plusWeeks(4),40d));
        p.agregarControl(new ControlLaboratorio(LocalDate.now().plusWeeks(5),60d));
        p.agregarControl(new ControlLaboratorio(LocalDate.now().plusWeeks(6),70d));

    }
}
