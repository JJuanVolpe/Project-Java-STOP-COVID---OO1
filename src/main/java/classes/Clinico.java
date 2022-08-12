package classes;

import java.time.LocalDate;
import java.util.List;

public class Clinico extends Control<Boolean>{

    public Clinico(LocalDate fecha, Boolean dato) {
        super(fecha, dato);
    }

    @Override
    public boolean isPositive() {
        return this.resultado;
    }

    protected void agregarPorClinico(List<Control> controlesClinicos){
        controlesClinicos.add(this);
    };

    protected boolean isClinico(){ return true;};
}
