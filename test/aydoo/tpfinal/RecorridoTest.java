package aydoo.tpfinal;


import org.junit.Assert;
import org.junit.Test;

public class RecorridoTest {

    @Test
    public void cuandoLeoUnaLineaDeStringsConTiempoDeUso7SeCreaElObjetoRecorridoYElTiempoDeUsoEsDelTipoIntYTieneValor7(){
        String usuarioid = "1036";
        String bicicletaid = "403";
        String origenfecha = "2010-12-30 19:39:03";
        String origenestacionid = "6";
        String origennombre = "DERECHO";
        String destinofecha = "2010-12-30 19:46:03";
        String destinoestacionid = "3";
        String destinonombre = "RETIRO";
        String tiempouso = "7";
        Recorrido recorrido = new Recorrido(usuarioid,bicicletaid,origenfecha,origenestacionid,origennombre,destinofecha,destinoestacionid,destinonombre,tiempouso);

        Assert.assertEquals(7,recorrido.getTiempoDeUso());
    }
}
