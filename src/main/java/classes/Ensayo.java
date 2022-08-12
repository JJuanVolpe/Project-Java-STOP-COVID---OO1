package classes;

import classes.systemAvg.SystemAvg;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
public class Ensayo {

    private Estudio estudio;
    private Set<Voluntario> voluntarios = new HashSet<>();


    public Ensayo(){
        voluntarios = new HashSet<>();
    };

    public Ensayo(Set<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public Set<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    // Inciso 1: registro de voluntario, hay sobre-carga de metodo para flexibilidad
    public void registrarVoluntario(String nombre, String dni, String sexo, int edad, String id){
        if (this.getVoluntarios().stream().map(Voluntario::getId).anyMatch(some -> some.equals(id))) {
            System.out.println("Lo sentimos no se puede agregar el voluntario debido a que existe un id registrado con el mismo numero.");
        } else{
            this.getVoluntarios().add(new Voluntario(nombre, dni, sexo, edad, id));
        }
    }
    public void registrarVoluntario(Voluntario v){
        if (this.getVoluntarios().stream().map(Voluntario::getId).anyMatch(some -> some.equals(v.getId()))) {
            System.out.println("Lo sentimos no se puede agregar el voluntario debido a que existe un id registrado con el mismo numero.");
        } else{
            this.getVoluntarios().add(new Voluntario(v.getNombre(), v.getDni(), v.getSexo(), v.getEdad(), v.getId()));
        }
    }

    // Inciso 2: inicio de estudio
    public void iniciarEstudio() {
        if (this.getVoluntarios().size() > 1 && this.getVoluntarios().size() % 2 == 0) {
            this.estudio = new Estudio(this.getVoluntarios());
        } else {
            System.out.println("Insuficientes voluntarios para crear estudio, deben generarse grupos pares");
        }
    }

    // Inciso 6: Porcentaje de integrantes de ese grupo con Control Clínico positivo divididos en tres rangos de edades
    public void reportarSintomas() {
        if (this.getEstudio() != null) {
            SystemAvg.getAvgByAgeAndClinico(this.getEstudio().getAllVoluntarios());
        } else {
            System.out.println("No se ha creado estudio todavia");
        }
    }

    // Inciso 7: Reportamos los Suspendidos por Pcr Positivo
    public void reportarSuspendidos() {
        if (this.getEstudio() != null) {
            SystemAvg.getAvgByAgeAndPcr(this.getEstudio().getAllVoluntarios());
        } else {
            System.out.println("No se ha creado estudio todavia");
        }
    }

    // Inciso 8: Reportamos promedio de cantidad anticuerpos generados por 3 o 6 semanas
    public void reporteGrupoVacuna() {
        if (this.getEstudio() != null) {
            SystemAvg.getAvgByWeeks(this.getEstudio().getVacuna());
        } else {
            System.out.println("No se ha creado estudio todavia");
        }
    }
}
