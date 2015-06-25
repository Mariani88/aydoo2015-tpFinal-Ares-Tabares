package aydoo.tpfinal;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Recorrido {

    private final String usuarioId;
    private final String bicicletaId;
    private final GregorianCalendar origenFecha;
    private final String origenEstacionId;
    private final String origenNombre;
    private final GregorianCalendar destinoFecha;
    private final String destinoEstacionId;
    private final String destinoNombre;
    private final int tiempoDeUso;

    public Recorrido(String usuarioId, String bicicletaId, String origenFecha, String origenEstacionId, String origenNombre, String destinoFecha,String destinoEstacionId,String destinoNombre, String tiempoDeUso){
        this.usuarioId = usuarioId;
        this.bicicletaId = bicicletaId;
        this.origenFecha = this.convertirDateStringAGregorianCalendar(origenFecha) ;
        this.origenEstacionId = origenEstacionId;
        this.origenNombre = origenNombre;
        this.destinoFecha = this.convertirDateStringAGregorianCalendar(destinoFecha);
        this.destinoEstacionId = destinoEstacionId;
        this.destinoNombre = destinoNombre;
        this.tiempoDeUso = Integer.parseInt(tiempoDeUso);
    }

    private GregorianCalendar convertirDateStringAGregorianCalendar(String origenfecha){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(origenfecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal;
    }

    // Getters & Setters
    
    public String getUsuarioId() {
        return usuarioId;
    }

    public String getBicicletaId() {
        return bicicletaId;
    }

    public GregorianCalendar getOrigenFecha() {
        return origenFecha;
    }

    public String getOrigenEstacionId() {
        return origenEstacionId;
    }

    public String getOrigenNombre() {
        return origenNombre;
    }

    public GregorianCalendar getDestinoFecha() {
        return destinoFecha;
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
