package aydoo.tpfinal;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EstadisticaRecorridoMasRealizadoTest {
	
	@Test
	public void crearUnaEstadisticaDelTipoRecorridoMasRealizado(){
		
		Estadistica recorridoMasRealizado = new EstadisticaRecorridoMasRealizado();
		Assert.assertEquals("Recorrido mas realizado", recorridoMasRealizado.getNombreEstadistica());
		
	}
	
	@Test
	public void generarEstadisticaParaRecorridoMasRealizadoDebeDevolverUnPar6Coma5(){
		
		Recorrido recorrido1 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("350","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("460","3","RETIRO","3","RETIRO","59");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica recorridoMasRealizado = new EstadisticaRecorridoMasRealizado();
		recorridoMasRealizado.generarEstadistica(listaDeRecorridos);
		List <String> listaEsperada = recorridoMasRealizado.obtenerEstadisticaResultante();
		
		Assert.assertTrue(listaEsperada.contains("6;5"));
	}
	
	@Test
	public void cuandoGeneroUnaEstadisticaConUnaListaVaciaDebeLanzaeUnaExcepcionYDevolverUnaListaVacia(){
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		Estadistica recorridoMasRealizado = new EstadisticaRecorridoMasRealizado();
		
		recorridoMasRealizado.generarEstadistica(listaDeRecorridos);
		List <String> listaEsperada = recorridoMasRealizado.obtenerEstadisticaResultante();
		Assert.assertTrue(listaEsperada.isEmpty());
	}
	
	@Test
	public void cuandoGeneroUnaEstadisticaQueContieneMasDeUnValorDebeDevolverUnaListaQueLosContenga(){
		
		Recorrido recorrido1 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("350","7","DERECHO","8","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("422","7","DERECHO","8","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("460","3","RETIRO","3","RETIRO","59");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica recorridoMasRealizado = new EstadisticaRecorridoMasRealizado();
		recorridoMasRealizado.generarEstadistica(listaDeRecorridos);
		List <String> listaEsperada = recorridoMasRealizado.obtenerEstadisticaResultante();
		
		
		Assert.assertTrue(listaEsperada.size()==2);
		Assert.assertTrue(listaEsperada.contains("6;5"));
		Assert.assertTrue(listaEsperada.contains("7;8"));
	}
	
	@Test
	public void generarUnaEstadisticaEnFormatoYMLDebeContenerLosRecorridosEnElFormatoYML(){
		
		Recorrido recorrido1 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("350","7","DERECHO","8","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("422","7","DERECHO","8","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("460","3","RETIRO","3","RETIRO","59");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica recorridoMasRealizado = new EstadisticaRecorridoMasRealizado();
		recorridoMasRealizado.generarEstadistica(listaDeRecorridos);
		
		List<String> listaDeResultados = recorridoMasRealizado.obtenerEstadisticaResultante();
		recorridoMasRealizado.generarListaEnFormatoYML(listaDeResultados);
	
		Assert.assertTrue(recorridoMasRealizado.generarListaEnFormatoYML(listaDeResultados).size()==5);
		Assert.assertTrue(recorridoMasRealizado.generarListaEnFormatoYML(listaDeResultados).contains("Recorrido mas realizado:"));
		Assert.assertTrue(recorridoMasRealizado.generarListaEnFormatoYML(listaDeResultados).contains("    id origen: 6"));
		Assert.assertTrue(recorridoMasRealizado.generarListaEnFormatoYML(listaDeResultados).contains("    id destino: 5"));
		Assert.assertTrue(recorridoMasRealizado.generarListaEnFormatoYML(listaDeResultados).contains("    id origen: 7"));
		Assert.assertTrue(recorridoMasRealizado.generarListaEnFormatoYML(listaDeResultados).contains("    id destino: 8"));	
	}
}