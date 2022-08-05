package classes.systemAvg;

import classes.Control;
import classes.Laboratorio;
import classes.Paciente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SystemAvg {

    private static final int[] YOUNG_AGE = {18, 40};

    private static final int[] MEDIUM_AGE = {41, 60};

    private static final int[] OLD_AGE = {61, 161};  //Se adopta limite superior para permitir el mecanismo por defecto y no agregar más métodos.

    //6. Reportar síntomas.
    //Según estudios clínicos y por edad
    public static void getAvgByAgeAndClinico(List<Paciente> list) {
        int total = list.size();
        list = list.stream().filter(Paciente::poseeClinicoPositivo).collect(Collectors.toList());
        long avgYoung=0, avgMedium=0, avgOld=0;
        if (!list.isEmpty()){
            avgYoung = list.stream().filter(p -> p.getByAge(YOUNG_AGE[0], YOUNG_AGE[1])).count() / total;
            avgMedium = list.stream().filter(p -> p.getByAge(MEDIUM_AGE[0], MEDIUM_AGE[1])).count() / total;
            avgOld = list.stream().filter(p -> p.getByAge(OLD_AGE[0], OLD_AGE[1])).count() / total;
            System.out.println("promedio: : 18-40:" + avgYoung +
                             "\npromedio: : 41-60:" + avgMedium +
                             "\npromedio: 60 y mayores:" + avgOld);
        }
    }

    //7. Reportar suspendidos.
    //Según estudios PCR y por edad
    public static void getAvgByAgeAndPcr(List<Paciente> list) {
        int total = list.size();
        list = list.stream().filter(Paciente::poseePcrPositivo).collect(Collectors.toList());
        long avgYoung=0, avgMedium=0, avgOld=0;
        if (!list.isEmpty()){
            avgYoung = list.stream().filter(p -> p.getByAge(YOUNG_AGE[0], YOUNG_AGE[1])).count() / total;
            avgMedium = list.stream().filter(p -> p.getByAge(MEDIUM_AGE[0], MEDIUM_AGE[1])).count() / total;
            avgOld = list.stream().filter(p -> p.getByAge(OLD_AGE[0], OLD_AGE[1])).count() / total;
            System.out.println("promedio con 18-40:" + avgYoung +
                             "\npromedio con 41-60:" + avgMedium +
                             "\npromedio con y mayores:" + avgOld);
        }
    }

    //7. Reporte grupo Vacuna.
    //Según estudios por Laboratorio y por edad
    public static void getAvgByWeeks(List<Paciente> list) {
        int total = list.size();
        List<Paciente> newList = list.stream().filter(Paciente::poseeControlLaboratorio).toList();
        double firstAvgByThreeWeeks = 0;
        double firstAvgBySixWeeks = 0;
        if (!list.isEmpty()) {
            List<Control> controles = newList.stream().map(P -> P.getControlsByLab(true)).flatMap(Collection::stream).toList();
            if (!controles.isEmpty()) {
                firstAvgByThreeWeeks = controles.stream().map(c -> (Laboratorio) c).toList().stream().mapToDouble(Laboratorio::getResult).sum() / total;
            }
            System.out.println("Promedio Total en Tres semanas:" + firstAvgByThreeWeeks);

            controles = newList.stream().map(P -> P.getControlsByLab(false)).flatMap(Collection::stream).toList();
            if (!controles.isEmpty()) {
                firstAvgBySixWeeks = controles.stream().map(c -> (Laboratorio) c).toList().stream().mapToDouble(Laboratorio::getResult).sum() / total;
            }
            System.out.println("Promedio Total en Seis semanas:" + firstAvgBySixWeeks);
        /*
        * frstAvg =  List<Control> controles = newList.stream().map(P -> P.getControlsByLab(true)).flatMap(Collection::stream)
        *                                                      .map(c -> (Laboratorio) c).toList().stream().mapToDouble(Laboratorio::getResult).sum() / total;

        *
        *
        *
        * List<Control> List<Control> List<Control> */

        }
    }

}
