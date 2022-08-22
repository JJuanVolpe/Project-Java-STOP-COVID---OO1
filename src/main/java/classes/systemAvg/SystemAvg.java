package classes.systemAvg;

/*
* Esta clase implementa los métodos específicos solicitados respecto a estadísticas y porcentajes en grupos.
*  Tenemos tres rangos de edades a utilizar, para los casos de uso: 6, 7 y 8 (3 métodos respectivos en órden)
*
*
* */
import classes.Control;
import classes.ControlLaboratorio;
import classes.Paciente;
import java.util.Collection;
import java.util.List;

public class SystemAvg {

    private static final int[] YOUNG_AGE = {18, 40};

    private static final int[] MEDIUM_AGE = {41, 60};

    private static final int[] OLD_AGE = {61, 299};  //Se adopta limite superior para permitir el mecanismo por defecto y no agregar más métodos.

    //6. Reportar síntomas.
    //Porcentaje de Controles clínicos positivos por rangos establecidos.
    public static List<Double> getAvgByAgeAndClinico(List<Paciente> list) {
        List<Paciente> newList = list.stream().filter(Paciente::poseeClinicoPositivo).toList();

        return getAverageByAge(newList, list.size());
        }

    //7. Reportar suspendidos.
    //Porcentaje de Controles Pcr positivos por rangos establecidos.
    public static List<Double> getAvgByAgeAndPcr(List<Paciente> list) {
        List<Paciente> newList = list.stream().filter(Paciente::poseePcrPositivo).toList();

        return getAverageByAge(newList, list.size());
    }

    private static List<Double> getAverageByAge(List<Paciente> newList, int total) {
        double avgYoung=0, avgMedium=0, avgOld=0;

        avgYoung = (double)  newList.stream().filter(p -> p.getByAge(YOUNG_AGE[0], YOUNG_AGE[1])).count() / total;
        avgMedium = (double) newList.stream().filter(p -> p.getByAge(MEDIUM_AGE[0], MEDIUM_AGE[1])).count() / total;
        avgOld = (double) newList.stream().filter(p -> p.getByAge(OLD_AGE[0], OLD_AGE[1])).count() / total;

        return List.of(avgYoung, avgMedium, avgOld);
    }


    //7. Reporte grupo Vacuna.
    //Promedio de Control Laboratorio (anticuerpos generados promedio en grupo) y por edad
    //Utilizamos un parámetro booleano para elegir si mostramos el porcentaje en 3 o 6 semanas
    public static List<Double> getAvgByWeeks(List<Paciente> list) {
        double firstAvgByThreeWeeks = 0;
        double firstAvgBySixWeeks = 0;

        List<Paciente> newList = list.stream().filter(Paciente::poseeControlLaboratorio).toList();

        List<Control> controles = newList.stream().map(P -> P.getControlsLabByTime(true)).flatMap(Collection::stream).toList();
        if (!controles.isEmpty()) {
            firstAvgByThreeWeeks = controles.stream().map(c -> (ControlLaboratorio) c).mapToDouble(ControlLaboratorio::getResultado).sum() / list.size();
        }

        controles = newList.stream().map(P -> P.getControlsLabByTime(false)).flatMap(Collection::stream).toList();
        if (!controles.isEmpty()) {
            firstAvgBySixWeeks = controles.stream().map(c -> (ControlLaboratorio) c).mapToDouble(ControlLaboratorio::getResultado).sum() / list.size();
        }

        return List.of(firstAvgByThreeWeeks, firstAvgBySixWeeks);
    }

}
