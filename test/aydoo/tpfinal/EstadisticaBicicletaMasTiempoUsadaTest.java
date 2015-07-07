package aydoo.tpfinal;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EstadisticaBicicletaMasTiempoUsadaTest {

	@Test
	public void crearUnaEstadisticaDelTipoEstadisticaBicicletaMasUsada() {

		Estadistica masTiempoUsada = new EstadisticaBicicletaMasTiempoUsada();
		Assert.assertEquals("Bicicletas mas tiempo usadas",masTiempoUsada.getNombreEstadistica());
	}

	@Test
	public void generarEstadisticaParaBicicletaMasTiempoUsadaEnBaseAUnaListaDeRecorridosDebeContenerId422(){
		
		Recorrido recorrido1 = new Recorrido("449","6","DERECHO","5","ADUANA","50");
		Recorrido recorrido2 = new Recorrido("422","6","DERECHO","5","ADUANA","80");
		Recorrido recorrido3 = new Recorrido("350","6","DERECHO","5","ADUANA","40");
		Recorrido recorrido4 = new Recorrido("449","6","DERECHO","5","ADUANA","70");
		Recorrido recorrido5 = new Recorrido("422","3","RETIRO","3","RETIRO","80");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica masTiempoUsada = new EstadisticaBicicletaMasTiempoUsada();
		masTiempoUsada.generarEstadistica(listaDeRecorridos);
		List <String> listaResultante = masTiempoUsada.obtenerEstadisticaResultante();
		
		Assert.assertTrue(listaResultante.size()==1);
		Assert.assertTrue(listaResultante.contains("422"));
			
	}
	
	@Test
	public void cuandoGeneroUnaEstadisticaConUnaListaVaciaDebeDevolverUnaListaVacia(){
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		Estadistica mastTiempoUsada = new EstadisticaBicicletaMasTiempoUsada();
		mastTiempoUsada.generarEstadistica(listaDeRecorridos);
		List <String> listaResultante = mastTiempoUsada.obtenerEstadisticaResultante();
		
		Assert.assertTrue(listaResultante.isEmpty());
		
	}
	
	@Test
	public void cuandoGeneroUnaEstadisticaQueContieneMasDeUnValorDebeDevolverUnaListaQueLosContenga(){
		
		Recorrido recorrido1 = new Recorrido(/*"501",*/"422",/*"2010-12-01 13:26:15",*/"6","DERECHO",/*"2010-12-01 14:23:50",*/"5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido(/*"501",*/"422",/*"2010-12-01 13:26:15",*/"6","DERECHO",/*"2010-12-01 14:23:50",*/"5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido(/*"501",*/"350",/*"2010-12-01 13:26:15",*/"6","DERECHO",/*"2010-12-01 14:23:50",*/"5","ADUANA","40");
		Recorrido recorrido4 = new Recorrido(/*"501",*/"460",/*"2010-12-01 13:26:15",*/"6","DERECHO",/*"2010-12-01 14:23:50",*/"5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido(/*"1720",*/"460",/*"2010-12-01 13:22:19",*/"3","RETIRO",/*"2010-12-01 14:21:41",*/"3","RETIRO","58");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica masTiempoUsada = new EstadisticaBicicletaMasTiempoUsada();
		masTiempoUsada.generarEstadistica(listaDeRecorridos);
		List <String> listaResultante = masTiempoUsada.obtenerEstadisticaResultante();
		
		Assert.assertTrue((listaResultante.size()==2));
		Assert.assertTrue(listaResultante.contains("460"));
		Assert.assertTrue(listaResultante.contains("422"));
		
	}
	
	@Test
	public void generarUnaEstadisticaEnFormatoYMLDebeContenerLosIdsEnElFormatoYML(){
		
		Recorrido recorrido1 = new Recorrido(/*"501",*/"422",/*"2010-12-01 13:26:15",*/"6","DERECHO",/*"2010-12-01 14:23:50",*/"5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido(/*"501",*/"422",/*"2010-12-01 13:26:15",*/"6","DERECHO",/*"2010-12-01 14:23:50",*/"5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido(/*"501",*/"350",/*"2010-12-01 13:26:15",*/"6","DERECHO",/*"2010-12-01 14:23:50",*/"5","ADUANA","57");
		Recorrido recorrido4 = new Recorrido(/*"501",*/"460",/*"2010-12-01 13:26:15",*/"6","DERECHO",/*"2010-12-01 14:23:50",*/"5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido(/*"1720",*/"460",/*"2010-12-01 13:22:19",*/"3","RETIRO",/*"2010-12-01 14:21:41",*/"3","RETIRO","58");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica masTiempoUsada = new EstadisticaBicicletaMasTiempoUsada();
		masTiempoUsada.generarEstadistica(listaDeRecorridos);
		List<String> listaEstadisticas = masTiempoUsada.obtenerEstadisticaResultante();
		
		List<String> listaEnFormatoYML = masTiempoUsada.generarListaEnFormatoYML(listaEstadisticas);
		Assert.assertTrue(listaEnFormatoYML.size()==4);
		Assert.assertTrue(listaEnFormatoYML.contains("Bicicletas mas tiempo usadas:"));
		Assert.assertTrue(listaEnFormatoYML.contains("    id: 422"));
		Assert.assertTrue(listaEnFormatoYML.contains("    id: 460"));
		Assert.assertTrue(listaEnFormatoYML.contains("Tiempo de la bicicleta mas utilizada: 6960"));
	}

}
