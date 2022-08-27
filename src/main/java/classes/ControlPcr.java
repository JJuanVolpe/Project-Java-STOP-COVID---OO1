package classes;

import java.time.LocalDate;

public class ControlPcr extends Control<Boolean>{
    
    public ControlPcr(LocalDate fecha, Boolean resultado){
        super(fecha, resultado);
    }

    protected boolean isPcr(){
        return true;
    };    
    
    @Override
    public boolean isPositive() {
        return this.resultado;
    }
}
