package aydoo.tpfinal;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class EstadisticaBicicletaMenosUsada extends Estadistica {
	
	@Override
	public Object generarEstadistica(List<Recorrido> listaDeRecorridos){Map<String,Integer> mapa = new TreeMap<String,Integer>();
	
	String bicicletaId=null;

	try {	

		for(Recorrido recorrido : listaDeRecorridos){

			if(!mapa.containsKey(recorrido.getBicicletaId())){

				mapa.put(recorrido.getBicicletaId(),1);

			}

			else{

				mapa.put(recorrido.getBicicletaId(), mapa.get(recorrido.getBicicletaId())+1);

			}

		}

		int minimo = Collections.min(mapa.values());

		for (Entry<String, Integer> entry : mapa.entrySet()) {  

			if (entry.getValue()==minimo) {

				bicicletaId = entry.getKey();     

			}

		}

	}

	catch (Exception e){

		e.printStackTrace();

	}

	return bicicletaId;

	} 

}
