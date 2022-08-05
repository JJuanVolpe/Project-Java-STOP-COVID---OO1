import classes.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.*;

public class SystemTest {

    public SystemTest(){
        //
        //
        //
    }
    private Ensayo ensayo;

    private Estudio estudio;


    private Set<Voluntario> getFiveVoluntarios(){
        return new HashSet(Set.of(new Voluntario("gabriel", "3353121", "T", 15, "00000"),
               new Voluntario("anibal", "43678910", "C", 21, "00001"),
               new Voluntario("facundo", "16782912", "F", 69, "00002"),
               new Voluntario("ismael", "12897600", "M", 39, "00003"),
               new Voluntario("ariel", "18210112", "P", 60, "00004")));
    }

    private List<Control> getNewControls(){
        return List.of(new Clinico(LocalDate.now(), false), new Laboratorio(LocalDate.now().plusWeeks(1), 61d), new Clinico(LocalDate.now().plusWeeks(2), true));
    }


    @BeforeTest
    public void getTestEnsayo() {
        ensayo = new Ensayo(getFiveVoluntarios());
        this.testRegistroDeVoluntario();
        this.testInicioEstudio();
    }
    @Test
    public void testRegistroDeVoluntario(){
        Voluntario v = new Voluntario("Damián", "18210112", "A", 71, "00006");
        this.ensayo.registrarVoluntario(v);
        Assert.assertTrue(this.ensayo.getVoluntarios().contains(v));  //Control de insercion de Voluntario
        int total = this.ensayo.getVoluntarios().size();
        this.ensayo.registrarVoluntario("Enzo", "18210112", "S", 91, "00006");
        Assert.assertEquals(total, this.ensayo.getVoluntarios().size());  //Control de unicidad por id.
    }

    @Test
    public void testInicioEstudio(){
        this.ensayo.iniciarEstudio();   //Notar: Inicializamos el sistema con voluntarios
        this.estudio = this.ensayo.getEstudio();
        Assert.assertNotNull(this.ensayo.getEstudio());
        Assert.assertEquals(this.ensayo.getEstudio().getPlacebo().size(), this.ensayo.getEstudio().getVacuna().size());
    }

    @Test
    public void testRegistrarInoculacion(){
        Assert.assertNull(this.estudio.getFechaInoculacion());
        this.estudio.registrarInoculacion();
        Assert.assertNotNull(this.estudio.getFechaInoculacion());
    }

    @Test
    public void testRealizarControlPaciente(){
        Paciente p = new Paciente(this.estudio.getVacuna().get(0));
        Assert.assertTrue(p.noTieneControlesPositivos());
        Assert.assertFalse(p.poseeControlLaboratorio());
        p.agregarControl(this.getNewControls().get(0));
        p.agregarControl(this.getNewControls().get(1)); //Este C. es por Laboratorio
        Assert.assertTrue(p.poseeControlLaboratorio());
        p.agregarControl(this.getNewControls().get(2)); //Este C. Clinico es positivo
        Assert.assertFalse(p.noTieneControlesPositivos());
        p.agregarControl(new Pcr(LocalDate.now().plusWeeks(4), true)); //Este C. Clinico es positivo
        Assert.assertTrue(p.poseePcrPositivo());
    }

    @Test
    public void testRealizarControlGrupoPlacebo(){
        List<Control> controles = this.getNewControls();
        List<Paciente> p = estudio.getPlacebo();
        estudio.realizarControlGrupoPlacebo(p.get(0), controles.get(0));
        Assert.assertEquals(1, p.get(0).getControles().size()); // 1 control agregado

        estudio.realizarControlGrupoPlacebo(p.get(0), controles.get(1));
        Assert.assertEquals(1, p.get(0).getControles().size()); //Grupo Placebo no debe tener controles por Laboratorio
        estudio.realizarControlGrupoVacuna(estudio.getVacuna().get(0), controles.get(1));

        estudio.realizarControlGrupoPlacebo(p.get(0), controles.get(0));
        Assert.assertEquals(1, p.get(0).getControles().size()); // No pasó una semana, entonces no se agrega
        estudio.realizarControlGrupoPlacebo(p.get(0), controles.get(2));
        Assert.assertEquals(2, p.get(0).getControles().size()); // Se agrega correctamente
    }

    @Test
    public void testRealizarControlGrupoVacuna(){
        List<Control> controles = this.getNewControls();
        List<Paciente> v = estudio.getVacuna();
        Assert.assertEquals(0, v.get(0).getControles().size());

        estudio.realizarControlGrupoVacuna(v.get(0), controles.get(0));
        estudio.realizarControlGrupoVacuna(v.get(0), controles.get(1));
        estudio.realizarControlGrupoVacuna(v.get(0), controles.get(2));

        Assert.assertEquals(3, v.get(0).getControles().size()); // Se agrega correctamente cada control

    }
    @Test
    public void testInformarResultadoControl(){
        Paciente p = this.estudio.getVacuna().get(1);
        p.agregarControl(this.getNewControls().get(2));
        p.agregarControl(new Pcr(LocalDate.now().plusWeeks(3), true));
        this.estudio.informarResultadoControl();
        Assert.assertFalse(this.estudio.getVacuna().contains(p));
        Assert.assertTrue(this.estudio.getSuspendidos().contains(p));
    }


    @Test
    public void informarResultado(){
        List<Control> controles = this.getNewControls();
        estudio.getVacuna().get(0).agregarControl(new Pcr(LocalDate.now(), true));
        Assert.assertEquals(0, this.estudio.getSuspendidos().size());
        estudio.informarResultadoControl();
        Assert.assertEquals(1, this.estudio.getSuspendidos().size());
    }

}
