package aydoo.tpfinal;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EstadisticaTiempoPromedioDeUsoTest {
	
	@Test
	public void crearUnaEstadisticaDelTipoRecorridoMasRealizado(){
		
		Estadistica tiempoPromedio = new EstadisticaTiempoPromedioDeUso();
		Assert.assertEquals("Tiempo promedio de uso", tiempoPromedio.getNombreEstadistica());
		
	}
	
	@Test
	public void generarEstadisticaParaTiempoPromedioDeUsoDebeDevolver58(){
		
		Recorrido recorrido1 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido2 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido3 = new Recorrido("501","350","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido4 = new Recorrido("501","422","2010-12-01 13:26:15","6","DERECHO","2010-12-01 14:23:50","5","ADUANA","58");
		Recorrido recorrido5 = new Recorrido("1720","460","2010-12-01 13:22:19","3","RETIRO","2010-12-01 14:21:41","3","RETIRO","58");
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		listaDeRecorridos.add(recorrido1);
		listaDeRecorridos.add(recorrido2);
		listaDeRecorridos.add(recorrido3);
		listaDeRecorridos.add(recorrido4);
		listaDeRecorridos.add(recorrido5);
		
		Estadistica tiempoPromedio = new EstadisticaTiempoPromedioDeUso();
		tiempoPromedio.generarEstadistica(listaDeRecorridos);
		List <String> listaEsperada = tiempoPromedio.obtenerEstadisticaResultante();
		
		Assert.assertTrue(listaEsperada.contains("58.0"));	
	}
	
	@Test
	public void cuandoGeneroUnaEstadisticaConUnaListaVaciaDebeLanzaeUnaExcepcionYDevolverUnaListaVacia(){
		
		List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido>();
		Estadistica tiempoPromedio = new EstadisticaTiempoPromedioDeUso();
		
		tiempoPromedio.generarEstadistica(listaDeRecorridos);
		List<String> listaEsperada = tiempoPromedio.obtenerEstadisticaResultante();
		
		Assert.assertTrue(listaEsperada.isEmpty());
	}
	
	@Test
	public void generarUnaEstadisticaEnFormatoYMLDebeContenerLaLineaConElNombreDeLaEstadisticaYElValorDeTtiempoPromedioDeUso(){
		
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
		
		Estadistica tiempoPromedio = new EstadisticaTiempoPromedioDeUso();
		tiempoPromedio.generarEstadistica(listaDeRecorridos);
		
		List<String> lista = tiempoPromedio
				.generarListaEnFormatoYML(tiempoPromedio
						.obtenerEstadisticaResultante());

		Assert.assertTrue(lista.size()==1);
		Assert.assertTrue(lista.contains("Tiempo promedio de uso: 58.0"));	
	}
}