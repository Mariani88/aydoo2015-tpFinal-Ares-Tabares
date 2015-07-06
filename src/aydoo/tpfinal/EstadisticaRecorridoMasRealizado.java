package aydoo.tpfinal;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class EstadisticaRecorridoMasRealizado extends Estadistica {

	private Map<String, Integer> historial;

	public EstadisticaRecorridoMasRealizado() {

		this.nombreEstadistica = "Recorrido mas realizado";
		this.historial = new TreeMap<String, Integer>();
	}

	public void generarEstadistica(List<Recorrido> listaDeRecorridos) {

		String stringTemporal;

		for (Recorrido recorrido : listaDeRecorridos) {

			stringTemporal = recorrido.getOrigenEstacionId() + ";"
					+ recorrido.getDestinoEstacionId();

			if (this.historial.containsKey(stringTemporal)) {

				this.historial.put(stringTemporal,
						this.historial.get(stringTemporal) + 1);
			}else {
				this.historial.put(stringTemporal, 1);
			}
		}
	}

	
	public List<String> obtenerEstadisticaResultante() {

		List<String> listaDeResultados = new LinkedList<String>();
		
		if (!this.historial.isEmpty()){
			int maximo = Collections.max(this.historial.values());
	
			for (Entry<String, Integer> entry : this.historial.entrySet()) {
	
				if (entry.getValue() == maximo) {
	
					listaDeResultados.add(entry.getKey());
				}
			}
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