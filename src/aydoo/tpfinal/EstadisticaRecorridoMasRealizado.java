package aydoo.tpfinal;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class EstadisticaRecorridoMasRealizado extends Estadistica {

	public EstadisticaRecorridoMasRealizado() {
		this.nombreEstadistica = "Recorrido mas realizado";
	}
	
public List<String> generarEstadistica(List<Recorrido> listaDeRecorridos){
		
		List<String> listaDeResultados = new LinkedList<String>();

		try {

			Map<String,Integer> mapa = new TreeMap<String,Integer>();
			String stringTemporal;

			for	(Recorrido recorrido : listaDeRecorridos){
				
				stringTemporal = recorrido.getOrigenEstacionId() + ";" + recorrido.getDestinoEstacionId();
				
				
				if(!mapa.containsKey(stringTemporal)){
					
					mapa.put(stringTemporal,1);
					
					
				}
				
				else{
					
					mapa.put(stringTemporal, mapa.get(stringTemporal)+1);
					
				}
				
				
			}
			
			listaDeResultados = buscarMaximo(mapa);
		}
		
		catch (Exception e){
			
			e.printStackTrace();
			
		}
		 
		 return listaDeResultados;
		
	}

	private List<String> buscarMaximo(Map<String, Integer> mapa) {
		
		List<String> listaDeResultados = new LinkedList<String>();
		int maximo = Collections.max(mapa.values());
	
		 for (Entry<String, Integer> entry : mapa.entrySet()) {  
		        
			 if (entry.getValue()==maximo) {
		            
				 listaDeResultados.add(entry.getKey());     
		     
			 }
		 }
	
		 return listaDeResultados;
}

}
