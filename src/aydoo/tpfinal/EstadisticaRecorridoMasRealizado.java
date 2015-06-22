package aydoo.tpfinal;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class EstadisticaRecorridoMasRealizado extends Estadistica {
	
public String generarEstadistica(List<Recorrido> listaDeRecorridos){
		
		String recorridoMasRealizado=null;

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
			
			recorridoMasRealizado = buscarMaximo(recorridoMasRealizado, mapa);
		}
		
		catch (Exception e){
			
			e.printStackTrace();
			
		}
		 
		 return recorridoMasRealizado;
		
	}

private String buscarMaximo(String recorridoMasRealizado,
		Map<String, Integer> mapa) {
	int maximo = Collections.max(mapa.values());
	
	 for (Entry<String, Integer> entry : mapa.entrySet()) {  
	        
		 if (entry.getValue()==maximo) {
	            
			 recorridoMasRealizado = (entry.getKey());     
	     
		 }
	 }
	return recorridoMasRealizado;
}

}
