package classes;

import java.time.LocalDate;
import java.util.List;


public class Pcr extends Control<Boolean>{
    public Pcr(LocalDate fecha, Boolean resultado){
        super(fecha, resultado);
    }

    @Override
    public boolean isPositive() {
        return this.resultado;
    }
    protected void agregarPorPcr(List<Control> controlesPcr){
        controlesPcr.add(this);
    };
    protected boolean isPcr(){
        return true;
    };

}
