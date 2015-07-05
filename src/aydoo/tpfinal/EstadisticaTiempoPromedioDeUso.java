package aydoo.tpfinal;

import java.util.LinkedList;
import java.util.List;

public class EstadisticaTiempoPromedioDeUso extends Estadistica {

	public EstadisticaTiempoPromedioDeUso() {
		
		this.nombreEstadistica = "Tiempo promedio de uso";

	}
	
	public List<String> generarEstadistica(List<Recorrido> listaDeRecorridos){
		
		List<String> listaDeResultados = new LinkedList<String>();
		double sumaDeTiempoEnRecorridos=0.0;
		
		try {
		
			for (Recorrido recorrido : listaDeRecorridos){
				
				sumaDeTiempoEnRecorridos += recorrido.getTiempoDeUso();
				
			}
		
		//MANDAR LAS LINEAS DE ABAJO AL METODO A AGREGAR "AISLAR DATO" Y CONVERTIR METODO A VOID
			if (sumaDeTiempoEnRecorridos>0){
				
				listaDeResultados.add(Double.toString(Math.round(sumaDeTiempoEnRecorridos/listaDeRecorridos.size())));
			
			}
			
		}
		
		catch (Exception e){
			
			e.printStackTrace();
			
		}
		
		return listaDeResultados;
		
	}
	
	public List<String> generarListaEnFormatoYML(List<String> lista) {

		List<String> listaFormateada = new LinkedList<String>();
		listaFormateada.add(nombreEstadistica + ": " + lista.get(0));
		
		return listaFormateada;
		
	}

}
