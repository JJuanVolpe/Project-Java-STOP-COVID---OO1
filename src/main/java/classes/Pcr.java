package classes;

import java.time.LocalDate;
import java.util.List;


public class Pcr extends Control<Boolean>{


    Pcr(LocalDate fecha, Boolean resultado){
        super(fecha, resultado);

    }

    @Override
    boolean isPositive() {
        return this.resultado;
    }

    @Override
    public Boolean getResult() {
        return this.resultado;
    }

    @Override
    LocalDate getFecha() {
        return this.fecha;
    }

    protected void agregarPorPcr(List<Control> controlesPcr){
        controlesPcr.add(this);
    };



}
