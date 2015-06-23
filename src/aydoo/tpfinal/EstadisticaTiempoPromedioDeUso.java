package aydoo.tpfinal;

import java.util.LinkedList;
import java.util.List;

public class EstadisticaTiempoPromedioDeUso extends Estadistica {

	public EstadisticaTiempoPromedioDeUso() {
		this.nombreEstadistica = "Tiempo promedio de uso";
	}
	
	public List<String> generarEstadistica(List<Recorrido> listaDeRecorridos){
		
		List<String> listaDeResultados = new LinkedList<String>();
		int sumaDeTiempoEnRecorridos=0;
		
		try {
		
			for (Recorrido recorrido : listaDeRecorridos){
				
				sumaDeTiempoEnRecorridos += recorrido.getTiempoDeUso();
				
			}
		
			listaDeResultados.add(Integer.toString(sumaDeTiempoEnRecorridos/listaDeRecorridos.size()));
			
		}
		
		catch (Exception e){
			
			e.printStackTrace();
			
		}
		
		return listaDeResultados;
		
	}

}
