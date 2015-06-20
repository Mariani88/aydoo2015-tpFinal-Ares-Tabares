package aydoo.tpfinal;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Recorrido {

    private String usuarioId;
    private String bicicletaId;
    private GregorianCalendar origenFecha;
    private String origenEstacionId;
    private String origenNombre;
    private GregorianCalendar destinoFecha;
    private String destinoEstacionId;
    private String destinoNombre;
    private int tiempoDeUso;



    public Recorrido(String usuarioId, String bicicletaId, String origenFecha, String origenEstacionId, String origenNombre, String destinoFecha,String destinoEstacionId,String destinoNombre, String tiempoDeUso) {
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
        //2010-12-30 19:39:03
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
