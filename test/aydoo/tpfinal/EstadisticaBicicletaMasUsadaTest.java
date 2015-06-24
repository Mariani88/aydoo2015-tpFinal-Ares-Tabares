package aydoo.tpfinal;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;



public class EstadisticaBicicletaMasUsadaTest {
	
	@Test
	public void generarEstadisticaParaBicicletaMasUsadaEnBaseAUnaListaDeRecorridosDebeContenerId422(){
		
		Recorrido recorrido1 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("501","350","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("1720","460","2010-12-01 13:22:19","3","RETIRO","2010-12-01 14:21:41","3","RETIRO","59");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica masUsada = new EstadisticaBicicletaMasUsada();
				
		Assert.assertTrue(masUsada.generarEstadistica(listaDeRecorridos).size()==1);
		Assert.assertTrue(masUsada.generarEstadistica(listaDeRecorridos).contains("422"));
			
	}
	
	@Test
	public void cuandoGeneroUnaEstadisticaConUnaListaVaciaDebeLanzaeUnaExcepcionYDevolverUnaListaVacia(){
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		Estadistica masUsada = new EstadisticaBicicletaMasUsada();
		
		Assert.assertTrue(masUsada.generarEstadistica(listaDeRecorridos).isEmpty());
		
	}
	
	@Test
	public void cuandoGeneroUnaEstadisticaQueContieneMasDeUnValorDebeDevolverUnaListaQueLosContenga(){
		
		Recorrido recorrido1 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("501","350","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("501","460","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("1720","460","2010-12-01 13:22:19","3","RETIRO","2010-12-01 14:21:41","3","RETIRO","59");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica masUsada = new EstadisticaBicicletaMasUsada();
		
		Assert.assertTrue((masUsada.generarEstadistica(listaDeRecorridos).size()==2));
		Assert.assertTrue(masUsada.generarEstadistica(listaDeRecorridos).contains("460"));
		Assert.assertTrue(masUsada.generarEstadistica(listaDeRecorridos).contains("422"));
		
	}
	
	@Test
	public void generarUnaEstadisticaEnFormatoYMLDebeContenerLosIdsEnElFormatoYML(){
		
		Recorrido recorrido1 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("501","350","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("501","460","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("1720","460","2010-12-01 13:22:19","3","RETIRO","2010-12-01 14:21:41","3","RETIRO","59");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica masUsada = new EstadisticaBicicletaMasUsada();
		List<String> lista = masUsada.generarEstadistica(listaDeRecorridos);
	
		Assert.assertTrue(masUsada.generarListaEnFormatoYML(lista).size()==3);
		Assert.assertTrue(masUsada.generarListaEnFormatoYML(lista).contains("Bicicletas mas usadas:"));
		Assert.assertTrue(masUsada.generarListaEnFormatoYML(lista).contains("	id: 422"));
		Assert.assertTrue(masUsada.generarListaEnFormatoYML(lista).contains("	id: 460"));
		
	}

}
