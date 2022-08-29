package classes;

import classes.systemAvg.SystemAvg;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Ensayo {

    private Estudio estudio;
    private Set<Voluntario> voluntarios;

    public Ensayo(){
        voluntarios = new HashSet<>();
    }

    public Ensayo(Set<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public Set<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    // Inciso 1: registro de voluntario, hay sobrecarga de métodos para mayor flexibilidad
    public void registrarVoluntario(String nombre, String dni, String sexo, int edad, String id){
        if (this.getVoluntarios().stream().map(Voluntario::getId).anyMatch(some -> some.equals(id)) && id.length() != 4) {
            System.out.println("Existe un id registrado con el mismo numero o el id no posee el formato requerido");
        } else{
            this.getVoluntarios().add(new Voluntario(nombre, dni, sexo, edad, id));
        }
    }

    public void registrarVoluntario(Voluntario v){
        if (this.getVoluntarios().stream().anyMatch(some -> some.equals(v)) && v.getId().length() != 4) {
            System.out.println("Existe un id registrado con el mismo numero o el id no posee el formato requerido");
        } else{
            this.getVoluntarios().add(new Voluntario(v.getNombre(), v.getDni(), v.getSexo(), v.getEdad(), v.getId()));
        }
    }

    // Inciso 2: inicio de estudio
    public void iniciarEstudio() {
        if (this.getVoluntarios().size() > 1 && this.getVoluntarios().size() % 2 == 0) {
            this.estudio = new Estudio(this.getVoluntarios());
            this.getVoluntarios().clear();
        } else {
            System.out.println("Insuficientes voluntarios para crear estudio, deben generarse grupos pares");
        }
    }

    // Inciso 6: Porcentaje de integrantes de ese grupo con Control Clínico positivo divididos en tres rangos de edades
    public void reportarSintomas() {
        if (this.getEstudio() != null) {
            List<Double> stats = SystemAvg.getAvgByAgeAndClinico(this.getEstudio().getAllVoluntarios());
            System.out.println("promedio edades 18-40:" + stats.get(0) +
                               "\npromedio edades 41-60:" + stats.get(1) +
                               "\npromedio edad > 60:" + stats.get(2));
        } else {
            System.out.println("No se ha creado estudio todavia");
        }
    }

    // Inciso 7: Reportamos los Suspendidos por Pcr Positivo
    public void reportarSuspendidos() {
        if (this.getEstudio() != null) {
            List<Double> stats = SystemAvg.getAvgByAgeAndPcr(this.getEstudio().getAllVoluntarios());
            System.out.println("promedio edades 18-40:" + stats.get(0) +
                                "\npromedio edades 41-60:" + stats.get(1) +
                                "\npromedio edad > 60:" + stats.get(2));
        } else {
            System.out.println("No se ha creado estudio todavia");
        }
    }

    // Inciso 8: Reportamos promedio de cantidad anticuerpos generados por 3 o 6 semanas.
    public void reporteGrupoVacuna() {
        if (this.getEstudio() != null) {
            List<Double> stats = SystemAvg.getAvgByWeeks(this.getEstudio().getVacuna());
            System.out.println("Promedio Total en Tres semanas:" + stats.get(0) +
                               "\nPromedio Total en Seis semanas:" + stats.get(1));
        } else {
            System.out.println("No se ha creado estudio todavia");
        }
    }
}
