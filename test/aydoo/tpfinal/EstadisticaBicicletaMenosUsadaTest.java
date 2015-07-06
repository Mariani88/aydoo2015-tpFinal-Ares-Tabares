package aydoo.tpfinal;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EstadisticaBicicletaMenosUsadaTest {
	
	@Test
	public void crearUnaEstadisticaDelTipoEstadisticaMenosUsada(){
		
		Estadistica menosUsada = new EstadisticaBicicletaMenosUsada();
		Assert.assertEquals("Bicicletas menos usadas", menosUsada.getNombreEstadistica());
		
	}
	
	@Test
	public void generarEstadisticaParaBicicletaMenosUsadaEnBaseAUnaListaDeRecorridosDebeDevolverId350(){
		
		Recorrido recorrido1 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("501","350","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("1720","422","2010-12-01 13:22:19","3","RETIRO","2010-12-01 14:21:41","3","RETIRO","59");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica menosUsada = new EstadisticaBicicletaMenosUsada();
		menosUsada.generarEstadistica(listaDeRecorridos);
		List <String> listaEsperada = menosUsada.obtenerEstadisticaResultante();
		
		Assert.assertTrue(listaEsperada.contains("350"));
			
	}
	
	@Test
	public void cuandoGeneroUnaEstadisticaConUnaListaVaciaDebeDevolverUnaListaVacia(){
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		Estadistica menosUsada = new EstadisticaBicicletaMenosUsada();
		menosUsada.generarEstadistica(listaDeRecorridos);
		List <String> listaEsperada = menosUsada.obtenerEstadisticaResultante();
		
		Assert.assertTrue(listaEsperada.isEmpty());
	}

	@Test
	public void cuandoGeneroUnaEstadisticaQueContieneMasDeUnValorDebeDevolverUnaListaQueLosContenga(){
		
		Recorrido recorrido1 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("501","350","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("501","460","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("1720","150","2010-12-01 13:22:19","3","RETIRO","2010-12-01 14:21:41","3","RETIRO","59");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica menosUsada = new EstadisticaBicicletaMenosUsada();
		
		menosUsada.generarEstadistica(listaDeRecorridos);
		List <String> listaEsperada = menosUsada.obtenerEstadisticaResultante();
		
		Assert.assertTrue((listaEsperada.size()==3));
		Assert.assertTrue(listaEsperada.contains("350"));
		Assert.assertTrue(listaEsperada.contains("150"));
		Assert.assertTrue(listaEsperada.contains("460"));
		
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
		
		Estadistica menosUsada = new EstadisticaBicicletaMenosUsada();
		menosUsada.generarEstadistica(listaDeRecorridos);
		List<String> listaEsperada = menosUsada.obtenerEstadisticaResultante();
	
		Assert.assertTrue(menosUsada.generarListaEnFormatoYML(listaEsperada).size()==2);
		Assert.assertTrue(menosUsada.generarListaEnFormatoYML(listaEsperada).contains("Bicicletas menos usadas:"));
		Assert.assertTrue(menosUsada.generarListaEnFormatoYML(listaEsperada).contains("    id: 350"));
		
	}

}
