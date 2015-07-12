package aydoo.tpfinal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EstadisticaBicicletaMasTiempoUsada extends Estadistica{
	
	private Map<String,Integer> historial;
	private int maximo;
	private List<String> maximos;
	
	public EstadisticaBicicletaMasTiempoUsada() {
		
		this.nombreEstadistica = "Bicicletas mas tiempo usadas";
		this.historial = new HashMap<String,Integer>();
		this.maximos = new LinkedList <String> ();
		this.maximo = -1;
	}
	

	private void aislarMaximos (String id, int tiempoDeUso){
		if ( tiempoDeUso > this.maximo){
			this.maximos.clear();
			this.maximos.add(id);
			this.maximo = tiempoDeUso;
		
		}else if (tiempoDeUso == this.maximo){
			this.maximos.add(id);
		}
	}
	
	
	@Override
	public void generarEstadistica(List<Recorrido> listaDeRecorridos){

		for (Recorrido recorrido : listaDeRecorridos) {
			
			String idBicicleta = recorrido.getBicicletaId();
			
			if (this.historial.containsKey(idBicicleta)) {

				int tiempoDeUso = this.historial.get(idBicicleta) + recorrido.getTiempoDeUso();
				this.historial.put(idBicicleta, tiempoDeUso);
				this.aislarMaximos(idBicicleta, tiempoDeUso);
			} else {
				this.historial.put(idBicicleta, recorrido.getTiempoDeUso());
				this.aislarMaximos(idBicicleta, recorrido.getTiempoDeUso());
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
		
		listaFormateada.add("Tiempo de la bicicleta mas utilizada: "+obtenerTiempoMaximoEnSegundos());

		return listaFormateada;	
	}


	private int obtenerTiempoMaximoEnSegundos() {
		return this.maximo * 60;
	}
}
