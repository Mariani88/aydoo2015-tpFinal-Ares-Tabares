package aydoo.tpfinal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EstadisticaBicicletaMasUsada extends Estadistica {

	private Map<String,Integer> historial;
	private int maximo;
	private List<String> maximos;
	
	public EstadisticaBicicletaMasUsada() {
		
		this.nombreEstadistica = "Bicicletas mas usadas";
		this.historial = new HashMap<String,Integer>();
		this.maximos = new LinkedList <String> ();
		this.maximo = -1;
	}
	
	
	private void aislarMaximos (String id, int uso){
		
		if ( uso > this.maximo){
			this.maximos.clear();
			this.maximos.add(id);
			this.maximo = uso;
		
		}else if (uso == this.maximo){
			this.maximos.add(id);
		}
	}
	
	
	@Override
	public void generarEstadistica(List<Recorrido> listaDeRecorridos){

		for (Recorrido recorrido : listaDeRecorridos) {
			
			String idBicicleta = recorrido.getBicicletaId();
			
			if (this.historial.containsKey(idBicicleta)) {

				int uso = this.historial.get(idBicicleta) + 1;
				this.historial.put(idBicicleta, uso);
				this.aislarMaximos(idBicicleta, uso);
			} else {
				this.historial.put(idBicicleta, 1);
				this.aislarMaximos(idBicicleta, 1);
			}
		}
	}

	
	public List<String> obtenerEstadisticaResultante() {
		
		List<String> listaDeResultados = new LinkedList<String>();
		
		if (!this.historial.isEmpty()){
		
			listaDeResultados = this.maximos;//.addAll(this.maximos.keySet());
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