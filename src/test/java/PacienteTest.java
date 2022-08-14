import classes.Clinico;
import classes.Laboratorio;
import classes.Paciente;
import classes.Pcr;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class PacienteTest {

    @Test
    public void realizarControlPaciente(){
        Paciente p = new Paciente("Jose", "M", "12", 20, "0019");
        p.agregarControl(new Clinico(LocalDate.now(), false));
        Assert.assertTrue(p.noTieneControlesPositivos());
        Assert.assertEquals(1, p.getControles().size());

        p.agregarControl(new Clinico(LocalDate.now().plusWeeks(1), true));
        Assert.assertFalse(p.noTieneControlesPositivos());
    }

    @Test
    public void getControlsByWeeks(){
        Paciente p = new Paciente("Jose", "M", "12", 20, "0019");
        addLabControls(p);
        Assert.assertEquals(3, p.getControlsLabByTime(true).size());
        Assert.assertEquals(6, p.getControlsLabByTime(false).size());
    }

    @Test
    public void hasControlesPositivos(){
        Paciente p = new Paciente("Jose", "M", "12", 20, "0019");
        addLabControls(p);
        p.agregarControl(new Clinico(LocalDate.now().plusWeeks(0), false));
        Assert.assertTrue(p.noTieneControlesPositivos());
        p.agregarControl(new Clinico(LocalDate.now().plusWeeks(6), true));
        Assert.assertFalse(p.noTieneControlesPositivos());
    }

    @Test
    public void booleanReturnByControls(){
        Paciente p = new Paciente("Jose", "M", "12", 20, "0019");
        addLabControls(p);
        p.agregarControl(new Pcr(LocalDate.now().plusWeeks(6), true));
        Assert.assertTrue(p.poseePcrPositivo());
        Assert.assertFalse(p.poseeClinicoPositivo());
        Assert.assertTrue(p.poseeControlLaboratorio());
    }

    private void addLabControls(Paciente p) {
        p.agregarControl(new Laboratorio(LocalDate.now(),0d));
        p.agregarControl(new Laboratorio(LocalDate.now().plusWeeks(1),10d));
        p.agregarControl(new Laboratorio(LocalDate.now().plusWeeks(2),20d));
        p.agregarControl(new Laboratorio(LocalDate.now().plusWeeks(3),30d));
        p.agregarControl(new Laboratorio(LocalDate.now().plusWeeks(4),40d));
        p.agregarControl(new Laboratorio(LocalDate.now().plusWeeks(5),60d));
    }
}
