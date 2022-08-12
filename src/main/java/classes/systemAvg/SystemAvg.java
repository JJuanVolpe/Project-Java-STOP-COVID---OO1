package classes.systemAvg;

import classes.Control;
import classes.Laboratorio;
import classes.Paciente;
import java.util.Collection;
import java.util.List;

public class SystemAvg {

    private static final int[] YOUNG_AGE = {18, 40};

    private static final int[] MEDIUM_AGE = {41, 60};

    private static final int[] OLD_AGE = {61, 299};  //Se adopta limite superior para permitir el mecanismo por defecto y no agregar más métodos.

    //6. Reportar síntomas.
    //Según estudios clínicos y por edad
    public static List<Double> getAvgByAgeAndClinico(List<Paciente> list) {
        List<Paciente> newList = list.stream().filter(Paciente::poseeClinicoPositivo).toList();
        double avgYoung=0, avgMedium=0, avgOld=0;
        if (!list.isEmpty()){
            avgYoung = (double) newList.stream().filter(p -> p.getByAge(YOUNG_AGE[0], YOUNG_AGE[1])).count() / list.size();
            avgMedium =  (double) newList.stream().filter(p -> p.getByAge(MEDIUM_AGE[0], MEDIUM_AGE[1])).count() / list.size();
            avgOld = (double) newList.stream().filter(p -> p.getByAge(OLD_AGE[0], OLD_AGE[1])).count() / list.size();
            System.out.println("promedio edades 18-40:" + avgYoung +
                             "\npromedio edades 41-60:" + avgMedium +
                             "\npromedio edad > 60:" + avgOld);
        }
        return List.of(avgYoung, avgMedium, avgOld);
        }

    //7. Reportar suspendidos.
    //Según estudios PCR y por edad
    public static List<Double> getAvgByAgeAndPcr(List<Paciente> list) {
        List<Paciente> newList = list.stream().filter(Paciente::poseePcrPositivo).toList();
        double avgYoung=0, avgMedium=0, avgOld=0;
        if (!list.isEmpty()){
            avgYoung = (double)  newList.stream().filter(p -> p.getByAge(YOUNG_AGE[0], YOUNG_AGE[1])).count() / list.size();
            avgMedium = (double) newList.stream().filter(p -> p.getByAge(MEDIUM_AGE[0], MEDIUM_AGE[1])).count() / list.size();
            avgOld = (double) newList.stream().filter(p -> p.getByAge(OLD_AGE[0], OLD_AGE[1])).count() / list.size();
            System.out.println("promedio con 18-40:" + avgYoung +
                             "\npromedio con 41-60:" + avgMedium +
                             "\npromedio con y mayores:" + avgOld);
        }
        return List.of(avgYoung, avgMedium, avgOld);
    }

    //7. Reporte grupo Vacuna.
    //Según estudios por Laboratorio y por edad
    public static List<Double> getAvgByWeeks(List<Paciente> list) {
        double firstAvgByThreeWeeks = 0;
        double firstAvgBySixWeeks = 0;
        if (list.isEmpty()) {
            return List.of(0d, 0d);
        }
        else {
            List<Paciente> newList = list.stream().filter(Paciente::poseeControlLaboratorio).toList();
            List<Control> controles = newList.stream().map(P -> P.getControlsLabByTime(true)).flatMap(Collection::stream).toList();
            if (!controles.isEmpty()) {
                controles.stream().map(Control::getResultado).forEach(System.out::println);
                firstAvgByThreeWeeks = controles.stream().map(c -> (Laboratorio) c).toList().stream().mapToDouble(Laboratorio::getResultado).sum() / list.size();
            }
            System.out.println("Promedio Total en Tres semanas:" + firstAvgByThreeWeeks);

            controles = newList.stream().map(P -> P.getControlsLabByTime(false)).flatMap(Collection::stream).toList();
            if (!controles.isEmpty()) {
                firstAvgBySixWeeks = controles.stream().map(c -> (Laboratorio) c).toList().stream().mapToDouble(Laboratorio::getResultado).sum() / list.size();
            }
            System.out.println("Promedio Total en Seis semanas:" + firstAvgBySixWeeks);
        }
        return List.of(firstAvgByThreeWeeks, firstAvgBySixWeeks);
    }

}
