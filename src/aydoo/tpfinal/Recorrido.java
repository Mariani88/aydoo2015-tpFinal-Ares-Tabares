package aydoo.tpfinal;


public class Recorrido {

   
    private final String bicicletaId;
    private final String origenEstacionId;
    private final String origenNombre;
    private final String destinoEstacionId;
    private final String destinoNombre;
    private final int tiempoDeUso;

	public Recorrido(String bicicletaId,
			String origenEstacionId, String origenNombre, 
			String destinoEstacionId, String destinoNombre, String tiempoDeUso) {
	
		this.bicicletaId = bicicletaId;
		this.origenEstacionId = origenEstacionId;
		this.origenNombre = origenNombre;
		this.destinoEstacionId = destinoEstacionId;
		this.destinoNombre = destinoNombre;
		this.tiempoDeUso = Integer.parseInt(tiempoDeUso);
	}

    public String getBicicletaId() {
        return bicicletaId;
    }

    public String getOrigenEstacionId() {
        return origenEstacionId;
    }

    public String getOrigenNombre() {
        return origenNombre;
    }

    public String getDestinoEstacionId() {
        return destinoEstacionId;
    }

    public String getDestinoNombre() {
        return destinoNombre;
    }

    public int getTiempoDeUso() {
        return tiempoDeUso;
    }
}
