import classes.*;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnsayoTest {

    @Test
    public void testRegistroDeVoluntario(){
        Ensayo ensayo = new Ensayo();
        assertEquals(0, ensayo.getVoluntarios().size());
        ensayo.registrarVoluntario(new Voluntario("José", "18210112", "A", 71, "00006"));
        assertEquals(1, ensayo.getVoluntarios().size());  //Control de insercion de Voluntario

        ensayo.registrarVoluntario(new Voluntario("Ariana", "19001912", "M", 91, "00006"));
        assertEquals(1, ensayo.getVoluntarios().size());  //Control de unicidad por id.
    }

    @Test
    public void iniciarEstudio(){
        Ensayo ensayo = new Ensayo();
        ensayo.iniciarEstudio();
        assertNull(ensayo.getEstudio()); //No tiene voluntarios necesarios
        ensayo = new Ensayo(utilDataClass.getTenVoluntarios());

        ensayo.iniciarEstudio();
        assertNotNull(ensayo.getEstudio()); //No tiene voluntarios necesarios
        assertFalse(ensayo.getEstudio().getAllVoluntarios().isEmpty());
    }

}
