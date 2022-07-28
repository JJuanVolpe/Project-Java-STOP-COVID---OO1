package classes;

import java.time.LocalDate;
import java.util.List;

public abstract class Control<T> {

    protected LocalDate fecha;
    protected T resultado;

    @Override
    public String toString() {
        return "Control:" +
                "fecha=" + fecha +
                ", resultado=" + resultado.toString();
    }

    public Control(LocalDate fecha, T dato){
        this.fecha = fecha;
        this.resultado = dato;
    }

    public  T getResult() {
        return null;
    }

    abstract boolean isPositive();

    abstract LocalDate getFecha();

    protected void agregarPorPcr(List<Control> controlesPcr){};
    protected void agregarPorClinico(List<Control> controlesClinico){};
    protected void agregarPorLaboratorio(List<Control> controlesLaboratorio){};

}
