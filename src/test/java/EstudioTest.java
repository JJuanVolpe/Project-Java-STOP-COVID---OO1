import classes.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class EstudioTest {

    Estudio estudio = new Estudio(utilDataClass.getTenVoluntarios());
    
    @Test
    public void testRegistrarInoculacion(){
        assertNull(estudio.getFechaInoculacion());
        estudio.registrarInoculacion();
        assertNotNull(estudio.getFechaInoculacion());
        assertEquals(LocalDate.now().toString(), estudio.getFechaInoculacion().toString());
    }
    @Test
    public void testRegistrarGrupos(){
        assertEquals(estudio.getPlacebo().size(), estudio.getVacuna().size());
        assertEquals(10, estudio.getAllVoluntarios().size());
    }
    
    @Test
    public void hacerControlGrupoPlacebo(){
        estudio.realizarControlGrupoPlacebo(estudio.getPlacebo().get(0), new ControlClinico(LocalDate.now(), true));
        assertEquals(1, estudio.getPlacebo().get(0).getControles().size()); // 1 control agregado

        estudio.realizarControlGrupoPlacebo(estudio.getPlacebo().get(0), new ControlLaboratorio(LocalDate.now(), 28d));
        assertEquals(1, estudio.getPlacebo().get(0).getControles().size()); //Grupo Placebo no debe tener controles por Laboratorio

        estudio.realizarControlGrupoPlacebo(estudio.getVacuna().get(0), new ControlClinico(LocalDate.now(), true));
        assertEquals(1, estudio.getPlacebo().get(0).getControles().size()); // 1 control agregado
    }

    @Test
    public void informarResultadoControl(){
        Estudio study = new Estudio(utilDataClass.getTenVoluntarios());

        assertEquals(10, study.getAllVoluntarios().size());
        assertEquals(0, study.getSuspendidos().size());

        study.realizarControlGrupoPlacebo(study.getPlacebo().get(0), new ControlPcr(LocalDate.now(), true));
        study.realizarControlGrupoVacuna(study.getVacuna().get(1), new ControlPcr(LocalDate.now(), true));
        study.realizarControlGrupoVacuna(study.getVacuna().get(2), new ControlPcr(LocalDate.now(), false));

        study.informarResultadoControl(); //Detecta PCR positivos y los reporta
        assertEquals(2, study.getSuspendidos().size());  //2 pcr positivos equivale a 2 suspendidos luego del informe de resultados.
        assertEquals(4, study.getVacuna().size());
        assertEquals(4,study.getPlacebo().size());
        assertEquals(10, study.getAllVoluntarios().size());
    }

    @BeforeAll
     static void beforeAll(){
        System.out.println("Inicializando Test  de Estudio");
    }
    @AfterAll
     static void afterAll(){
        System.out.println("Finalizando Test  de Estudio");
    }
}