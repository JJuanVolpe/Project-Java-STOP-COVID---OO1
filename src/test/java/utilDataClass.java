import classes.Clinico;
import classes.Laboratorio;
import classes.Paciente;
import classes.Voluntario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class utilDataClass {


    public static List<Paciente> getTenPatients(){
        return  new ArrayList(List.of(
                new Paciente("anibal", "3353121", "T", 18, "00000"),
                new Paciente("bernardo", "43678910", "C", 21, "00001"),
                new Paciente("camilo", "16782912", "F", 69, "00002"),
                new Paciente("damian", "12897600", "M", 39, "00003"),
                new Paciente("elias", "18210112", "P", 60, "00004"),
                new Paciente("facundo", "3353121", "T", 21, "00005"),
                new Paciente("gabriel", "43678910", "C", 19, "00006"),
                new Paciente("hector", "16782912", "F", 99, "00007"),
                new Paciente("ismael", "12897600", "M", 39, "00008"),
                new Paciente("juana", "18210112", "P", 41, "00009")));
    }


    public static Set<Voluntario> getTenVoluntarios() {
        return new HashSet<>(Set.of(
                new Voluntario("anibal", "3353121", "T", 18, "00000"),
                new Voluntario("bernardo", "43678910", "C", 21, "00001"),
                new Voluntario("camilo", "16782912", "F", 69, "00002"),
                new Voluntario("damian", "12897600", "M", 39, "00003"),
                new Voluntario("elias", "18210112", "P", 60, "00004"),
                new Voluntario("facundo", "3353121", "T", 21, "00005"),
                new Voluntario("gabriel", "43678910", "C", 19, "00006"),
                new Voluntario("hector", "16782912", "F", 99, "00007"),
                new Voluntario("ismael", "12897600", "M", 39, "00008"),
                new Voluntario("juana", "18210112", "P", 41, "00009")));
    }

    public static void generarControlesGrupoVacuna(List<Paciente> pacientes) {
        pacientes.get(0).agregarControl(new Laboratorio(LocalDate.now(), 1d));
        pacientes.get(0).agregarControl(new Laboratorio(LocalDate.now().plusWeeks(1), 1d));
        pacientes.get(0).agregarControl(new Laboratorio(LocalDate.now().plusWeeks(2), 1d));
        pacientes.get(0).agregarControl(new Laboratorio(LocalDate.now().plusWeeks(3), 1d));
        pacientes.get(0).agregarControl(new Laboratorio(LocalDate.now().plusWeeks(4), 1d));
        pacientes.get(0).agregarControl(new Laboratorio(LocalDate.now().plusWeeks(5), 1d));

        pacientes.get(2).agregarControl(new Laboratorio(LocalDate.now(), 1d));
        pacientes.get(3).agregarControl(new Laboratorio(LocalDate.now(), 1d));

    }

    public static List<Paciente> generarControlesParaReportarSintomas(List<Paciente> pacientes) {
        pacientes.get(0).agregarControl(new Clinico(LocalDate.now(), false));
        pacientes.get(1).agregarControl(new Clinico(LocalDate.now(), false));
        pacientes.get(2).agregarControl(new Clinico(LocalDate.now(), false));
        pacientes.get(3).agregarControl(new Clinico(LocalDate.now(), false));
        pacientes.get(4).agregarControl(new Clinico(LocalDate.now(), false));
        pacientes.get(5).agregarControl(new Clinico(LocalDate.now(), true));
        pacientes.get(6).agregarControl(new Clinico(LocalDate.now(), true));
        pacientes.get(7).agregarControl(new Clinico(LocalDate.now(), true));
        pacientes.get(8).agregarControl(new Clinico(LocalDate.now(), true));
        pacientes.get(9).agregarControl(new Clinico(LocalDate.now(), true));
        return pacientes;
    }

}
