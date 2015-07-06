package aydoo.tpfinal;

import java.util.LinkedList;
import java.util.List;

public class EstadisticaTiempoPromedioDeUso extends Estadistica {

	private double sumaDeTiempoEnRecorridos;
	private int cantidadDeBicicletas;
	
	public EstadisticaTiempoPromedioDeUso() {
		
		this.nombreEstadistica = "Tiempo promedio de uso";
		this.sumaDeTiempoEnRecorridos = 0.0;
		this.cantidadDeBicicletas = 0;
	}
	
	public void generarEstadistica(List<Recorrido> listaDeRecorridos) {
		
		for (Recorrido recorrido : listaDeRecorridos) {

			sumaDeTiempoEnRecorridos += recorrido.getTiempoDeUso();
		}
		
		this.cantidadDeBicicletas+=listaDeRecorridos.size();
	}
	
	@Override
	public List<String> obtenerEstadisticaResultante() {
		
		List<String> listaDeResultados = new LinkedList<String>();
		
		if (sumaDeTiempoEnRecorridos > 0) {

			listaDeResultados
					.add(Double.toString(Math.round(sumaDeTiempoEnRecorridos
							/ this.cantidadDeBicicletas)));
		}
		
		return listaDeResultados;
	}
	
	public List<String> generarListaEnFormatoYML(List<String> lista) {

		List<String> listaFormateada = new LinkedList<String>();
		listaFormateada.add(nombreEstadistica + ": " + lista.get(0));
		
		return listaFormateada;
		
	}
}