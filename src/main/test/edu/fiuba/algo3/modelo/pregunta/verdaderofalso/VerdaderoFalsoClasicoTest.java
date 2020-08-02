package edu.fiuba.algo3.modelo.pregunta.verdaderofalso;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VerdaderoFalsoClasicoTest {

    @Test
    public void debeCrearUnaPreguntaVerdaderFalsoConOpciones() throws ParametrosInvalidosExcepcion {
        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable verdaderoFalsoClasico = new VerdaderoFalsoClasico(preguntaTexto, opciones);

        Assertions.assertEquals(opciones, verdaderoFalsoClasico.obtenerOpciones());
        Assertions.assertEquals(preguntaTexto, verdaderoFalsoClasico.obtenerPregunta());
    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpciones() {

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoClasico("pregunta?", Collections.EMPTY_LIST));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConCantidadOpcionesDistintoDe2() {

        Opcion opcion = new Opcion("opcion", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcion);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoClasico("pregunta?", opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpcionesCorrectas() {
        Opcion opcionIncorrecta1 = new Opcion("Verdadero", Boolean.FALSE);
        Opcion opcionIncorrecta2 = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionIncorrecta1, opcionIncorrecta2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoClasico("pregunta?", opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConMasDe1OpcionCorrecta() {
        Opcion opcionCorrecta1 = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionCorrecta2 = new Opcion("Falso", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta1, opcionCorrecta2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoClasico("pregunta?", opciones));

    }

    @Test
    public void alAplicarMultiplicadorDebeLanzarUnaExcepcion() throws ParametrosInvalidosExcepcion {
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta1 = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta2 = new Opcion("Falso", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta1, opcionIncorrecta2);

        Preguntable verdaderoFalsoClasico = new VerdaderoFalsoClasico("pregunta?", opciones);

        Assertions.assertThrows(MultiplicadorExcepcion.class, () -> verdaderoFalsoClasico.aplicarMultiplicador(1, Multiplicador.PorDos));

    }

    @Test
    public void alAplicarMultiplicadorDefaultNoDebeLanzarExcepcion() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta1 = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta2 = new Opcion("Falso", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta1, opcionIncorrecta2);

        Preguntable verdaderoFalsoClasico = new VerdaderoFalsoClasico("pregunta?", opciones);

        Assertions.assertEquals(1, verdaderoFalsoClasico.aplicarMultiplicador(1, Multiplicador.PorDefecto));

    }

}
