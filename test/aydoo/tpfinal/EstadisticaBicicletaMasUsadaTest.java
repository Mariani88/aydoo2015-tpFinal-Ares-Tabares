package aydoo.tpfinal;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;



public class EstadisticaBicicletaMasUsadaTest {
	
	@Test
	public void crearUnaEstadisticaDelTipoEstadisticaBicicletaMasUsada(){
		
		Estadistica masUsada = new EstadisticaBicicletaMasUsada();
		Assert.assertEquals("Bicicletas mas usadas", masUsada.getNombreEstadistica());
		
	}
	
	@Test
	public void generarEstadisticaParaBicicletaMasUsadaEnBaseAUnaListaDeRecorridosDebeContenerId422(){
		
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
		
		Estadistica masUsada = new EstadisticaBicicletaMasUsada();
		masUsada.generarEstadistica(listaDeRecorridos);
		List <String> listaEsperada = masUsada.obtenerEstadisticaResultante();
		
		Assert.assertTrue(listaEsperada.size()==1);
		Assert.assertTrue(listaEsperada.contains("422"));
			
	}
	
	@Test
	public void cuandoGeneroUnaEstadisticaConUnaListaVaciaDebeDevolverUnaListaVacia(){
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		Estadistica masUsada = new EstadisticaBicicletaMasUsada();
		masUsada.generarEstadistica(listaDeRecorridos);
		List <String> listaEsperada = masUsada.obtenerEstadisticaResultante();
		
		Assert.assertTrue(listaEsperada.isEmpty());
		
	}
	
	@Test
	public void cuandoGeneroUnaEstadisticaQueContieneMasDeUnValorDebeDevolverUnaListaQueLosContenga(){
		
		Recorrido recorrido1 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("350","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("460","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("460","3","RETIRO","3","RETIRO","59");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica masUsada = new EstadisticaBicicletaMasUsada();
		masUsada.generarEstadistica(listaDeRecorridos);
		List <String> listaEsperada = masUsada.obtenerEstadisticaResultante();
		
		Assert.assertTrue((listaEsperada.size()==2));
		Assert.assertTrue(listaEsperada.contains("460"));
		Assert.assertTrue(listaEsperada.contains("422"));
		
	}
	
	@Test
	public void generarUnaEstadisticaEnFormatoYMLDebeContenerLosIdsEnElFormatoYML(){
		
		Recorrido recorrido1 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("350","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("460","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("460","3","RETIRO","3","RETIRO","59");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica masUsada = new EstadisticaBicicletaMasUsada();
		masUsada.generarEstadistica(listaDeRecorridos);
		List<String> lista = masUsada.obtenerEstadisticaResultante();
	
		Assert.assertTrue(masUsada.generarListaEnFormatoYML(lista).size()==3);
		Assert.assertTrue(masUsada.generarListaEnFormatoYML(lista).contains("Bicicletas mas usadas:"));
		Assert.assertTrue(masUsada.generarListaEnFormatoYML(lista).contains("    id: 422"));
		Assert.assertTrue(masUsada.generarListaEnFormatoYML(lista).contains("    id: 460"));
		
	}

}
