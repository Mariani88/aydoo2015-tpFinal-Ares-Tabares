package aydoo.tpfinal;

import java.util.List;

public abstract class Estadistica {

	protected String nombreEstadistica;

	protected abstract void generarEstadistica(List<Recorrido> listaDeRecorridos);
	
	public abstract List<String> obtenerEstadisticaResultante ();

	protected abstract List<String> generarListaEnFormatoYML(List<String> lista);
		
	
	
	public String getNombreEstadistica() {
		return nombreEstadistica;
	}
	
}

