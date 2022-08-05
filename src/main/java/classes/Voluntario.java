package classes;

import java.util.Objects;

public class Voluntario {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                                 //si this es nulo se rompe?
        if (o == null || getClass() != o.getClass()) return false;  //Esto esta mal?
        Voluntario v = (Voluntario) o;
        return this.getId().equals(v.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public Voluntario(String nombre, String dni, String sexo, int edad, String id){
        this.setId(id);
        this.setNombre(nombre);
        this.setDni(dni);
        this.setSexo(sexo);
        this.setEdad(edad);
    }

    @Override
    public String toString() {
        return  "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", sexo='" + sexo + '\'' +
                ", id='" + id + '\'' +
                ", edad=" + edad ;
    }

    private String nombre;
    private String dni;
    private String  sexo;

    private String  id;
    private int  edad;


    public int getEdad() {
        return edad;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public boolean getByAge(int minus, int plus) {
        return this.getEdad()  <= plus && this.getEdad()  >= minus;
    }
}
