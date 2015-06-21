package aydoo.tpfinal;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RecorridoTest {

    private Recorrido recorrido;

    @Before
    public void setUp(){
        this.recorrido = new Recorrido("1036","403","2010-12-30 19:39:03","6","DERECHO","2010-12-30 19:46:03","3","RETIRO","7");
    }

    @Test
    public void cuandoLeoUnaLineaDeStringsConTiempoDeUso7SeCreaElObjetoRecorridoYElTiempoDeUsoEsDelTipoIntYTieneValor7(){
        Assert.assertEquals(7,this.recorrido.getTiempoDeUso());
    }

    @Test
    public void  cuandoLeoUnaLineaDeStringsConCiertoOrigenFechaSeCreaElObjetoRecorridoYValidarElDiaDeLaFechaOrigen(){

        //Amigacho vas a ver que la fecha esperada tiene mes 11 y no 12, esto es porque para gregorian calendar los meses van del 0 al 11 para el constructor, o sea, el mes Diciembre es el 11 y Enero el 0
        GregorianCalendar fechaEsperada = new GregorianCalendar(2010,11,30,19,39,03);

        Assert.assertEquals(fechaEsperada.get(Calendar.DAY_OF_MONTH),this.recorrido.getOrigenFecha().get(Calendar.DAY_OF_MONTH));


    }

    @Test
    public void crearRecorridoYValidarElAnioDeLaFechaOrigen(){

        GregorianCalendar fechaEsperada = new GregorianCalendar(2010,11,30,19,39,03);

        Assert.assertEquals(fechaEsperada.get(Calendar.YEAR),this.recorrido.getOrigenFecha().get(Calendar.YEAR));

    }

    @Test
    public void crearRecorridoYValidarElMesDeLaFechaOrigen(){

        GregorianCalendar fechaEsperada = new GregorianCalendar(2010,11,30,19,39,03);

        Assert.assertEquals(fechaEsperada.get(Calendar.MONTH),this.recorrido.getOrigenFecha().get(Calendar.MONTH));

    }

    @Test
    public void crearRecorridoYValidarLaHoraDeLaFechaOrigen(){

        GregorianCalendar fechaEsperada = new GregorianCalendar(2010,11,30,19,39,03);

        Assert.assertEquals(fechaEsperada.get(Calendar.HOUR),this.recorrido.getOrigenFecha().get(Calendar.HOUR));

    }

    @Test
    public void crearRecorridoYValidarLosMinDeLaFechaOrigen(){

        GregorianCalendar fechaEsperada = new GregorianCalendar(2010,11,30,19,39,03);

        Assert.assertEquals(fechaEsperada.get(Calendar.MINUTE),this.recorrido.getOrigenFecha().get(Calendar.MINUTE));

    }

    @Test
    public void crearRecorridoYValidarLosSegDeLaFechaOrigen(){

        GregorianCalendar fechaEsperada = new GregorianCalendar(2010,11,30,19,39,03);

        Assert.assertEquals(fechaEsperada.get(Calendar.SECOND),this.recorrido.getOrigenFecha().get(Calendar.SECOND));

    }
}
