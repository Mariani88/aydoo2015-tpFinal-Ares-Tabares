package aydoo.tpfinal;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RecorridoTest {

    private Recorrido recorrido;

    @Before
    public void setUp(){
        this.recorrido = new Recorrido("403","6","DERECHO","3","RETIRO","7");
    }

    @Test
    public void cuandoLeoUnaLineaDeStringsConTiempoDeUso7SeCreaElObjetoRecorridoYElTiempoDeUsoEsDelTipoIntYTieneValor7(){
        Assert.assertEquals(7,this.recorrido.getTiempoDeUso());
    }

    /*@Test
    public void  cuandoLeoUnaLineaDeStringsConCiertoOrigenFechaSeCreaElObjetoRecorridoYValidarElDiaDeLaFechaOrigen(){

        GregorianCalendar fechaEsperada = new GregorianCalendar(2010,11,30,19,39,03);

        Assert.assertEquals(fechaEsperada.get(Calendar.DAY_OF_MONTH),this.recorrido.getOrigenFecha().get(Calendar.DAY_OF_MONTH));

    }*/

/*    @Test
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

    }*/
    
    @Test
    public void crearRecorridoYValidarSusAtributos() throws ParseException{
    	
    	GregorianCalendar origenFecha = new GregorianCalendar();
    	DateFormat dateFormatOrigen = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date dateOrigen = dateFormatOrigen.parse("2010-12-30 19:39:03");
    	origenFecha.setTime(dateOrigen);
    	
    	GregorianCalendar destinoFecha = new GregorianCalendar();
    	DateFormat dateFormatDestino = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date dateDestino = dateFormatDestino.parse("2010-12-30 19:46:03");
    	destinoFecha.setTime(dateDestino);
    	
    	//Assert.assertEquals("1036", this.recorrido.getUsuarioId());
    	Assert.assertEquals("403", this.recorrido.getBicicletaId());
    	//Assert.assertEquals(origenFecha, this.recorrido.getOrigenFecha());
    	Assert.assertEquals("6", this.recorrido.getOrigenEstacionId());
    	Assert.assertEquals("DERECHO", this.recorrido.getOrigenNombre());
    	//Assert.assertEquals(destinoFecha, this.recorrido.getDestinoFecha());
    	Assert.assertEquals("3", this.recorrido.getDestinoEstacionId());
    	Assert.assertEquals("RETIRO", this.recorrido.getDestinoNombre());
    	Assert.assertEquals(7, this.recorrido.getTiempoDeUso());
    	
    }
    
}
