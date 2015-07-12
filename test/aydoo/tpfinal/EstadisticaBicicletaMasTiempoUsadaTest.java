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
		
		Recorrido recorrido1 = new Recorrido("501","449","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","50");
		Recorrido recorrido2 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","80");
		Recorrido recorrido3 = new Recorrido("501","350","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","40");
		Recorrido recorrido4 = new Recorrido("501","449","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","70");
		Recorrido recorrido5 = new Recorrido("1720","422","2010-12-01 13:22:19","3","RETIRO","2010-12-01 14:21:41","3","RETIRO","80");
		
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

}
