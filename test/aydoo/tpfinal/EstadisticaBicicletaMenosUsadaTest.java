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
		
		Recorrido recorrido1 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("350","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("422","3","RETIRO","3","RETIRO","59");
		
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
		
		Recorrido recorrido1 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("422","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("350","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("460","6","DERECHO","5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("150","3","RETIRO","3","RETIRO","59");
		
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
		
		Estadistica menosUsada = new EstadisticaBicicletaMenosUsada();
		menosUsada.generarEstadistica(listaDeRecorridos);
		List<String> listaEsperada = menosUsada.obtenerEstadisticaResultante();
	
		Assert.assertTrue(menosUsada.generarListaEnFormatoYML(listaEsperada).size()==2);
		Assert.assertTrue(menosUsada.generarListaEnFormatoYML(listaEsperada).contains("Bicicletas menos usadas:"));
		Assert.assertTrue(menosUsada.generarListaEnFormatoYML(listaEsperada).contains("    id: 350"));
		
	}

}
