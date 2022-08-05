package classes;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Estudio {


    private LocalDate fechaInicio;
    private LocalDate fechaInoculacion;
    private List<Paciente> vacuna;
    private List<Paciente> placebo;
    private List<Paciente> suspendidos; //Mutuamente excluyente de  (Placebo o Vacuna)

    public Estudio(Set<Voluntario> voluntarios) {
        this.fechaInicio = LocalDate.now();
        this.vacuna = new ArrayList<>(voluntarios.size() / 2);
        this.placebo = new ArrayList<>(voluntarios.size() / 2);
        this.suspendidos = new ArrayList<>();
        this.generarGrupoPlaceboYVacuna(voluntarios);
    }
    public LocalDate getFechaInoculacion() {
        return fechaInoculacion;
    }

    public List<Paciente> getVacuna() {
        return vacuna;
    }

    public List<Paciente> getPlacebo() {
        return placebo;
    }
    public List<Paciente> getSuspendidos() {
        return this.suspendidos;
    }

    //Registramos fecha en que se genera la inoculación de ambos grupos, se asume el mismo tiempo para todos
    public void registrarInoculacion() {
        this.fechaInoculacion = LocalDate.now();
    }
    private void generarGrupoPlaceboYVacuna(Set<Voluntario> set) {

        List<Voluntario> list = new ArrayList<>(set);
        int midIndex = (list.size() - 1) / 2;

        List<List<Voluntario>> lists = new ArrayList<>(
                list.stream()
                        .collect(Collectors.partitioningBy(s -> list.indexOf(s) > midIndex))
                        .values()
        );
        lists.get(0).forEach(v -> this.vacuna.add((new Paciente(v))));
        lists.get(1).forEach(v -> this.placebo.add((new Paciente(v))));

    }

    private void suspenderConSintomas(List<Paciente> list){
        List<Paciente> listToErase = new ArrayList<>(10);
        listToErase.addAll(list.stream().filter(Paciente::poseePcrPositivo).toList());
        listToErase.forEach(p -> {
            System.out.println("Lo sentimos ha sido suspendido por detección de control PCR positivo:" + p.getNombre());
            this.suspendidos.add(p);
            if (this.getVacuna().contains(p))
                this.vacuna.remove(p);
            else
                this.placebo.remove(p);
        });
    }

    //Inciso 4. Se registra el control sobre el paciente.

    public void realizarControlGrupoPlacebo(Paciente p, Control control) {
        if (this.getPlacebo().contains(p) && !control.isLaboratorio()){
            p.agregarControl(control);
        } else{
            System.out.println("No se encuentra en el grupo placebo el paciente o es un control no permitido");
        }
    }
    public void realizarControlGrupoVacuna(Paciente p, Control control) {
        if (this.getVacuna().contains(p)){
            p.agregarControl(control);
        } else{
            System.out.println("No se encuentra en el grupo vacuna el paciente");
        }
    }
    //Inciso 5. Informar resultado de control.
    public void informarResultadoControl() {
        List<Paciente> list =  this.getVacuna();
        list.addAll(this.getPlacebo());
        list.forEach(Paciente::getLastControlResult);
        this.suspenderConSintomas(list);
    }
    public List<Paciente> getAllVoluntarios() {
        return Stream.of(this.getVacuna(), this.getPlacebo(), this.getSuspendidos())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
