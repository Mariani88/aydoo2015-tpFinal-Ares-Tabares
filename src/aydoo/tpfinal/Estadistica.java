package aydoo.tpfinal;

import java.util.List;

public abstract class Estadistica {

	protected String nombreEstadistica;
	protected String[] nombreCampos;

	protected List<String> generarEstadistica(List<Recorrido> listaDeRecorridos) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNombreEstadistica() {
		return nombreEstadistica;
	}

	public String[] getNombreCampos() {
		return nombreCampos;
	}
}
