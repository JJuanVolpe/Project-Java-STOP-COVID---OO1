import classes.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class EstudioTest {

    Estudio estudio = new Estudio(utilDataClass.getTenVoluntarios());
    @Test
    public void testRegistrarInoculacion(){
        Assert.assertNull(estudio.getFechaInoculacion());
        estudio.registrarInoculacion();
        Assert.assertNotNull(estudio.getFechaInoculacion());
        Assert.assertEquals(LocalDate.now().toString(), estudio.getFechaInoculacion().toString());
    }
    @Test
    public void testRegistrarGrupos(){
        Assert.assertEquals(estudio.getPlacebo().size(), estudio.getVacuna().size());
        Assert.assertEquals(10, estudio.getAllVoluntarios().size());
    }
    @Test
    public void hacerControlGrupoPlacebo(){
        estudio.realizarControlGrupoPlacebo(estudio.getPlacebo().get(0), new Clinico(LocalDate.now(), true));
        Assert.assertEquals(1, estudio.getPlacebo().get(0).getControles().size()); // 1 control agregado

        estudio.realizarControlGrupoPlacebo(estudio.getPlacebo().get(0), new Laboratorio(LocalDate.now(), 28d));
        Assert.assertEquals(1, estudio.getPlacebo().get(0).getControles().size()); //Grupo Placebo no debe tener controles por Laboratorio

        estudio.realizarControlGrupoPlacebo(estudio.getVacuna().get(0), new Clinico(LocalDate.now(), true));
        Assert.assertEquals(1, estudio.getPlacebo().get(0).getControles().size()); // 1 control agregado
    }

    @Test
    public void informarResultadoControl(){
        Estudio study = new Estudio(utilDataClass.getTenVoluntarios());

        Assert.assertEquals(10, study.getAllVoluntarios().size());
        Assert.assertEquals(0, study.getSuspendidos().size());

        study.realizarControlGrupoPlacebo(study.getPlacebo().get(0), new Pcr(LocalDate.now(), true));
        study.realizarControlGrupoVacuna(study.getVacuna().get(1), new Pcr(LocalDate.now(), true));

        study.informarResultadoControl(); //Detecta PCR positivos y los reporta
        Assert.assertEquals(2, study.getSuspendidos().size());
        Assert.assertEquals(4, study.getVacuna().size());
        Assert.assertEquals(4,study.getPlacebo().size());
        Assert.assertEquals(10, study.getAllVoluntarios().size());

    }

    @BeforeAll
     void beforeAll(){
        System.out.println("Inicializando Test  de Estudio");
    }
    @AfterAll
     void afterAll(){
        System.out.println("Finalizando Test  de Estudio");
    }
}
