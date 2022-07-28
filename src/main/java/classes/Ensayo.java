package classes;

import classes.systemAvg.SystemAvg;

import java.util.HashSet;
import java.util.Set;
public class Ensayo {

    private Estudio estudio;
    private Set<Voluntario> voluntarios;


    public Ensayo(){
        voluntarios = new HashSet<>();
    };

    public Ensayo(Set<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    // Inciso 1: registro de voluntario
    public void registrarVoluntario(String nombre, String dni, String sexo, int edad, String id){
        this.voluntarios.add(new Voluntario(nombre, dni, sexo, edad, id));
    }
    public void registrarVoluntario(Voluntario v){
        this.voluntarios.add(v);
    }

    // Inciso 2: inicio de estudio
    public void iniciarEstudio() {
        if (this.voluntarios.size() > 0 && this.voluntarios.size() % 2 != 0) {
            System.out.println("Insuficientes voluntarios para crear estudio, deben generarse grupos pares");
        } else {
            this.estudio = new Estudio(this.voluntarios);
            this.voluntarios.clear();
        }
    }

    // Inciso 6: Porcentaje de integrantes de ese grupo con Control Clínico positivo divididos en tres rangos de edades
    public void reportarSintomas() {
        if (this.estudio != null) {
            SystemAvg.getAvgByAgeAndClinico(this.estudio.getAllVoluntarios());
        } else {
            System.out.println("No se ha creado estudio todavia");
        }
    }

    // Inciso 7: Reportamos los Suspendidos por Pcr Positivo
    public void reportarSuspendidos() {
        if (this.estudio != null) {
            SystemAvg.getAvgByAgeAndPcr(this.estudio.getAllVoluntarios());
        } else {
            System.out.println("No se ha creado estudio todavia");
        }
    }

    // Inciso 8: Reportamos promedio de cantidad anticuerpos generados por 3 o 6 semanas
    public void reporteGrupoVacuna() {
        if (this.estudio != null) {
            SystemAvg.getAvgByWeeks(this.estudio.getGrupoVacunaPorLaboratorio());
        } else {
            System.out.println("No se ha creado estudio todavia");
        }
    }



}
