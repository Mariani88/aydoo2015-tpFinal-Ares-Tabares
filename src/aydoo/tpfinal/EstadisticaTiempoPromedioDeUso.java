package aydoo.tpfinal;

import java.util.List;

public class EstadisticaTiempoPromedioDeUso extends Estadistica {

	public EstadisticaTiempoPromedioDeUso() {
		this.nombreEstadistica = "Tiempo promedio de uso";
	}
	
	public Object generarEstadistica(List<Recorrido> listaDeRecorridos){
		
		int sumaDeTiempoEnRecorridos=0;
		
		try {
		
			for (Recorrido recorrido : listaDeRecorridos){
				
				sumaDeTiempoEnRecorridos += recorrido.getTiempoDeUso();
				
			}
		
			sumaDeTiempoEnRecorridos = sumaDeTiempoEnRecorridos/listaDeRecorridos.size();
			
		}
		
		catch (Exception e){
			
			e.printStackTrace();
			
		}
		
		return sumaDeTiempoEnRecorridos;
		
	}

}
