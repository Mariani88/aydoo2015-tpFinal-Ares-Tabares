package aydoo.tpfinal;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EstadisticaBicicletaMenosUsada extends Estadistica {

	private Map<String,Integer> historial;
	
	public EstadisticaBicicletaMenosUsada() {
		
		this.nombreEstadistica = "Bicicletas menos usadas";
		this.historial = new HashMap<String,Integer>();
	}
	
	@Override
	public void generarEstadistica(List<Recorrido> listaDeRecorridos) {

		for (Recorrido recorrido : listaDeRecorridos) {

			if (this.historial.containsKey(recorrido.getBicicletaId())) {

				int uso = this.historial.get( recorrido.getBicicletaId() ) + 1;
				this.historial.put(recorrido.getBicicletaId(),uso);

			}else {
				this.historial.put(recorrido.getBicicletaId(), 1);
			}
		}

	}
	
	public List<String> obtenerEstadisticaResultante() {
		
		List<String> listaDeResultados = new LinkedList<String>();
		
		if ( !this.historial.isEmpty()){
			int minimo = Collections.min(this.historial.values());
			
			for (Entry<String, Integer> entry : this.historial.entrySet()) {  
	
				if (entry.getValue()==minimo) {
			
					listaDeResultados.add(entry.getKey());     
				}
			}
		}
	
		return listaDeResultados;	
	}
	
	public List<String> generarListaEnFormatoYML(List<String> lista) {

		List<String> listaFormateada = new LinkedList<String>();
		listaFormateada.add(nombreEstadistica + ":");
		
		for (String string : lista){
			
			listaFormateada.add("    id: " + string);	
		} 

		return listaFormateada;
	}
}