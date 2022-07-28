package classes.systemAvg;

import classes.Control;
import classes.Laboratorio;
import classes.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class SystemAvg {

    private static final int[] youngAge = {18, 40};

    private static final int[] mediumAge = {41, 60};

    private static final int[] oldAge = {61, 161};  //Se adopta limite superior para permitir el mecanismo por defecto y no agregar más métodos.

    //Según estudios clínicos y por edad
    public static void getAvgByAgeAndClinico(List<Paciente> list) {
        list = list.stream().filter(Paciente::poseeClinicoPositivo).collect(Collectors.toList());

        if (!list.isEmpty()){
            long avgYoung = list.stream().filter(p -> p.getByAge(youngAge[0], youngAge[1])).count() / list.size();
            long avgMedium = list.stream().filter(p -> p.getByAge(mediumAge[0], mediumAge[1])).count() / list.size();
            long avgOld = list.stream().filter(p -> p.getByAge(oldAge[0], oldAge[1])).count() / list.size();
            System.out.println("promedio: : 18-40:" + avgYoung +
                             "\npromedio: : 41-60:" + avgMedium +
                             "\npromedio: 60 y mayores:" + avgOld);
        }
    }

    //Según estudios PCR y por edad
    public static void getAvgByAgeAndPcr(List<Paciente> list) {
        int total = list.size();
        list = list.stream().filter(Paciente::poseePcrPositivo).collect(Collectors.toList());
        if (!list.isEmpty()){
            long avgYoung = list.stream().filter(p -> p.getByAge(youngAge[0], youngAge[1])).count() / total;
            long avgMedium = list.stream().filter(p -> p.getByAge(mediumAge[0], mediumAge[1])).count() / total;
            long avgOld = list.stream().filter(p -> p.getByAge(oldAge[0], oldAge[1])).count() / total;
            System.out.println("promedio con 18-40:" + avgYoung +
                             "\npromedio con 41-60:" + avgMedium +
                             "\npromedio con y mayores:" + avgOld);
        }
    }

    //Según estudios por Laboratorio y por edad
    public static void getAvgByWeeks(List<Paciente> list) {
        double firstAvgByThreeWeeks = 0;
        double firstAvgBySixWeeks = 0;
        List<Control> controles = new ArrayList<>();

        list.stream().map(P -> P.getControlsByLab(true)).forEach(controles::addAll);
        if (!controles.isEmpty()) {
            firstAvgByThreeWeeks = controles.stream().map(c -> (Laboratorio) c).toList().stream().mapToDouble(Laboratorio::getResult).average().getAsDouble();
        }
        System.out.println("Promedio Total en Tres semanas:" + firstAvgByThreeWeeks);
        controles.clear();

        list.stream().map(P -> P.getControlsByLab(false)).forEach(controles::addAll);
        if (!controles.isEmpty()) {
            firstAvgBySixWeeks = controles.stream().map(c -> (Laboratorio) c).toList().stream().mapToDouble(Laboratorio::getResult).average().getAsDouble();
        }
        System.out.println("Promedio Total en Seis semanas:" + firstAvgBySixWeeks);
    }

}
