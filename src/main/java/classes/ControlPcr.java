package classes;

import java.time.LocalDate;
import java.util.List;


public class ControlPcr extends Control<Boolean>{
    public ControlPcr(LocalDate fecha, Boolean resultado){
        super(fecha, resultado);
    }

    @Override
    public boolean isPositive() {
        return this.resultado;
    }
    protected boolean isPcr(){
        return true;
    };

}
