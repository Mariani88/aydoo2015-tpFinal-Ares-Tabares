package aydoo.tpfinal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class EstadisticaBicicletaMasUsada extends Estadistica {

	public EstadisticaBicicletaMasUsada() {
		
		this.nombreEstadistica = "Bicicletas mas usadas";
	
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
	
	public List<String> generarListaEnFormatoYML(List<String> lista) {

		List<String> listaFormateada = new LinkedList<String>();
		listaFormateada.add(nombreEstadistica + ":");
		
		for (String string : lista){
			
			listaFormateada.add("	id: " + string);	
			
		} 

		return listaFormateada;
		
	}


}
