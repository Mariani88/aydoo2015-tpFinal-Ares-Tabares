package aydoo.tpfinal;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class EstadisticaBicicletaMenosUsada extends Estadistica {

	public EstadisticaBicicletaMenosUsada() {
		
		this.nombreEstadistica = "Bicicletas menos usadas";

	}
	
	@Override
	public List<String> generarEstadistica(List<Recorrido> listaDeRecorridos){
		
		List<String> listaDeResultados = new LinkedList<String>();
		Map<String,Integer> mapa = new TreeMap<String,Integer>();
		
		try {	

			for(Recorrido recorrido : listaDeRecorridos){
	
				if(!mapa.containsKey(recorrido.getBicicletaId())){
	
					mapa.put(recorrido.getBicicletaId(),1);
	
				}
	
				else{
	
					mapa.put(recorrido.getBicicletaId(), mapa.get(recorrido.getBicicletaId())+1);
	
				}
	
			}
	
			listaDeResultados = buscarMinimo(mapa);
	
		}
	
		catch (Exception e){
	
			e.printStackTrace();
	
		}

	return listaDeResultados;

	}
	
	private List<String> buscarMinimo(Map<String, Integer> mapa) {
		
		List<String> listaDeResultados = new LinkedList<String>();
		
		int minimo = Collections.min(mapa.values());

		for (Entry<String, Integer> entry : mapa.entrySet()) {  

			if (entry.getValue()==minimo) {
		
				listaDeResultados.add(entry.getKey());     

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
