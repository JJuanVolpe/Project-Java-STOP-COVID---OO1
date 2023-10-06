import classes.*;
import classes.systemAvg.SystemAvg;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.List;

public class StadisticsTest {

    //Porcentaje de voluntarios que debieron ser suspendidos por control PCR
    //Positivo en tres rangos de edades: 18-40, 41-60, mayores de 60.

    @Test
    void reportarSintomas() {
        List<Double> arrayByAges = SystemAvg.
                getAvgByAgeAndClinico(utilDataClass.
                        generarControlesParaReportarSintomas(utilDataClass.getTenPatients()));
        assertEquals(0.3d, arrayByAges.get(0));
        assertEquals(0.1d, arrayByAges.get(1));
        assertEquals(0.1d, arrayByAges.get(2));
    }

    @Test
    void reportarSuspendidos() {
        List<Paciente> list = utilDataClass.getTenPatients();
        list.forEach(p -> p.agregarControl(new ControlPcr(LocalDate.now(), true)));
        List<Double> statics = SystemAvg.getAvgByAgeAndPcr(list);

        assertEquals(0.6d, statics.get(0));
        assertEquals(0.2d, statics.get(1));
        assertEquals(0.2d, statics.get(1));
    }

    @Test
    void reporteGrupoVacuna() { //finished and tested
        Ensayo ensayo = new Ensayo((utilDataClass.getTenVoluntarios()));
        ensayo.iniciarEstudio();
        utilDataClass.generarControlesGrupoVacuna(ensayo.getEstudio().getVacuna());
        assertEquals(1.0d, SystemAvg.getAvgByWeeks(ensayo.getEstudio().getVacuna()).get(0));
        assertEquals(1.6d, SystemAvg.getAvgByWeeks(ensayo.getEstudio().getVacuna()).get(1));
    }

    //porcentaje de integrantes de ese grupo que registraron algún Control C. con síntomas,
    // en tres rangos de edades: 18-40, 41-60 y mayores de 60.
    @Test
    void stadisticsEmpty() {
        List<Double> arrayByAges = SystemAvg.getAvgByAgeAndClinico(utilDataClass.getTenPatients());  //Nobody have controls
        assertEquals(0d, arrayByAges.get(0));
        assertEquals(0d, arrayByAges.get(1));
        assertEquals(0d, arrayByAges.get(2));

        List<Double> arrayBySyntoms = SystemAvg.getAvgByAgeAndPcr(utilDataClass.getTenPatients());
        assertEquals(0d, arrayBySyntoms.get(0));
        assertEquals(0d, arrayBySyntoms.get(1));

        List<Double> arrayByWeeks = SystemAvg.getAvgByWeeks(utilDataClass.getTenPatients());
        assertEquals(0d, arrayByWeeks.get(0));
        assertEquals(0d, arrayByWeeks.get(1));
    }

}