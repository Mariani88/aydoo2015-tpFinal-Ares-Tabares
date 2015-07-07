package aydoo.tpfinal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EstadisticaRecorridoMasRealizado extends Estadistica {

	private Map<String, Integer> historial;
	private List<String> maximos;
	private int maximo;
	
	public EstadisticaRecorridoMasRealizado() {

		this.nombreEstadistica = "Recorrido mas realizado";
		this.historial = new HashMap<String, Integer>();
		this.maximos = new LinkedList<String> ();
		this.maximo = -1;
	}

	private void aislarMaximos (String extremos, int uso){
		
		if (uso > this.maximo){
			
			this.maximo = uso;
			this.maximos.clear();
			this.maximos.add(extremos);
		
		}else if (uso == this.maximo){
			this.maximos.add(extremos);
		}
	}
	
	public void generarEstadistica(List<Recorrido> listaDeRecorridos) {

		String stringTemporal;

		for (Recorrido recorrido : listaDeRecorridos) {

			stringTemporal = recorrido.getOrigenEstacionId() + ";"
					+ recorrido.getDestinoEstacionId();

			if (this.historial.containsKey(stringTemporal)) {

				int uso = this.historial.get(stringTemporal) + 1;
				this.historial.put(stringTemporal,uso);
				this.aislarMaximos(stringTemporal, uso);
			}else {
				this.historial.put(stringTemporal, 1);
				this.aislarMaximos(stringTemporal, 1);
			}
		}
	}

	
	public List<String> obtenerEstadisticaResultante() {

		List<String> listaDeResultados = new LinkedList<String>();
		
		if (!this.historial.isEmpty()){
			listaDeResultados = this.maximos;
		}
		
		return listaDeResultados;
	}

	public List<String> generarListaEnFormatoYML(List<String> lista) {

		List<String> listaFormateada = new LinkedList<String>();
		listaFormateada.add(nombreEstadistica + ":");

		for (String string : lista) {

			String[] partes = string.split(";");
			listaFormateada.add("    id origen: " + partes[0]);
			listaFormateada.add("    id destino: " + partes[1]);
		}

		return listaFormateada;
	}
}