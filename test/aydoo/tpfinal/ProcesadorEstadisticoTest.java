package aydoo.tpfinal;

import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dtabar on 6/22/15.
 */
public class ProcesadorEstadisticoTest {

/*    @Test
    public void test(){
        List<Estadistica> listaDeEstadisticas = new ArrayList<>();
        List<Recorrido> listaDeRecorridos = new ArrayList<>();
        EstadisticaBicicletaMasUsada estadisticaBiciMasUsada = Mockito.mock(EstadisticaBicicletaMasUsada.class);
        EstadisticaBicicletaMenosUsada estadisticaBicicletaMenosUsada = Mockito.mock(EstadisticaBicicletaMenosUsada.class);
        EstadisticaRecorridoMasRealizado estadisticaRecorridoMasRealizado = Mockito.mock(EstadisticaRecorridoMasRealizado.class);
        EstadisticaTiempoPromedioDeUso estadisticaTiempoPromedioDeUso = Mockito.mock(EstadisticaTiempoPromedioDeUso.class);

        when(estadisticaBiciMasUsada.generarEstadistica(listaDeRecorridos)).thenReturn("419");
        when(estadisticaBiciMasUsada.getNombreEstadistica()).thenReturn("Bicicletas Mas Usadas");
        when(estadisticaBicicletaMenosUsada.generarEstadistica(listaDeRecorridos)).thenReturn("452");
        when(estadisticaBicicletaMenosUsada.getNombreEstadistica()).thenReturn("Bicicletas Menos Usadas");
        when(estadisticaRecorridoMasRealizado.generarEstadistica(listaDeRecorridos)).thenReturn("1;6");
        when(estadisticaRecorridoMasRealizado.getNombreEstadistica()).thenReturn("Recorrido mas realizado");
        when(estadisticaTiempoPromedioDeUso.generarEstadistica(listaDeRecorridos)).thenReturn(42);
        when(estadisticaTiempoPromedioDeUso.getNombreEstadistica()).thenReturn("Tiempo Promedio de uso");

        listaDeEstadisticas.add(estadisticaBiciMasUsada);
        listaDeEstadisticas.add(estadisticaBicicletaMenosUsada);
        listaDeEstadisticas.add(estadisticaRecorridoMasRealizado);
        listaDeEstadisticas.add(estadisticaTiempoPromedioDeUso);


        ProcesadorEstadistico procesadorEstadistico = new ProcesadorEstadistico(listaDeEstadisticas);
        procesadorEstadistico.procesarModoOnDemand("archivosAProcesar/directorioDePruebaConDosZip");
    }*/

    @Test
    public void cuandoElDirectorioDeEntradaTieneContenidoElProcesadorOnDemandDebeGenerarUnArchivoDeSalidaConTamanoMayorACero() throws IOException {
        List<Estadistica> listaDeEstadisticas = new ArrayList<>();
        EstadisticaBicicletaMasUsada estadisticaBiciMasUsada = new EstadisticaBicicletaMasUsada();
        EstadisticaBicicletaMenosUsada estadisticaBicicletaMenosUsada = new EstadisticaBicicletaMenosUsada();
        EstadisticaRecorridoMasRealizado estadisticaRecorridoMasRealizado = new EstadisticaRecorridoMasRealizado();
        EstadisticaTiempoPromedioDeUso estadisticaTiempoPromedioDeUso = new EstadisticaTiempoPromedioDeUso();

        listaDeEstadisticas.add(estadisticaBiciMasUsada);
        listaDeEstadisticas.add(estadisticaBicicletaMenosUsada);
        listaDeEstadisticas.add(estadisticaRecorridoMasRealizado);
        listaDeEstadisticas.add(estadisticaTiempoPromedioDeUso);

        ProcesadorEstadistico procesadorEstadistico = new ProcesadorEstadistico(listaDeEstadisticas);
        procesadorEstadistico.procesarModoOnDemand("archivosAProcesar/directorioDePruebaConDosZip");
        Path reporte = Paths.get("reportes/salidaUnica.yml");

        Assert.assertTrue(Files.size(reporte) > 0);
    }

/*    @Test
    public void testDaemon(){
        List<Estadistica> listaDeEstadisticas = new ArrayList<>();
        EstadisticaBicicletaMasUsada estadisticaBiciMasUsada = new EstadisticaBicicletaMasUsada();
        EstadisticaBicicletaMenosUsada estadisticaBicicletaMenosUsada = new EstadisticaBicicletaMenosUsada();
        EstadisticaRecorridoMasRealizado estadisticaRecorridoMasRealizado = new EstadisticaRecorridoMasRealizado();
        EstadisticaTiempoPromedioDeUso estadisticaTiempoPromedioDeUso = new EstadisticaTiempoPromedioDeUso();

        listaDeEstadisticas.add(estadisticaBiciMasUsada);
        listaDeEstadisticas.add(estadisticaBicicletaMenosUsada);
        listaDeEstadisticas.add(estadisticaRecorridoMasRealizado);
        listaDeEstadisticas.add(estadisticaTiempoPromedioDeUso);

        ProcesadorEstadistico procesadorEstadistico = new ProcesadorEstadistico(listaDeEstadisticas);
        Daemon daemon = new Daemon("archivosAProcesar/directorioDePruebaConDosZip",procesadorEstadistico);
        daemon.monitorear();
    }*/

    @Test
    public void cuandoSePasaUnaListaDeDosArchivosElProcesadorOnDemandDebeGenerarDosArchivosDeSalidaConTamanoMayorACero(){
        List<Estadistica> listaDeEstadisticas = new ArrayList<>();
        EstadisticaBicicletaMasUsada estadisticaBiciMasUsada = new EstadisticaBicicletaMasUsada();
        EstadisticaBicicletaMenosUsada estadisticaBicicletaMenosUsada = new EstadisticaBicicletaMenosUsada();
        EstadisticaRecorridoMasRealizado estadisticaRecorridoMasRealizado = new EstadisticaRecorridoMasRealizado();
        EstadisticaTiempoPromedioDeUso estadisticaTiempoPromedioDeUso = new EstadisticaTiempoPromedioDeUso();

        listaDeEstadisticas.add(estadisticaBiciMasUsada);
        listaDeEstadisticas.add(estadisticaBicicletaMenosUsada);
        listaDeEstadisticas.add(estadisticaRecorridoMasRealizado);
        listaDeEstadisticas.add(estadisticaTiempoPromedioDeUso);

        List<String> listaDeArchivosAProcesar = new ArrayList<>();
        listaDeArchivosAProcesar.add("archivosAProcesar/directorioDePruebaConDosZip/CSVConTresLineas.zip");
        listaDeArchivosAProcesar.add("archivosAProcesar/directorioDePruebaConDosZip/CSVConUnaLinea.zip");

        ProcesadorEstadistico procesadorEstadistico = new ProcesadorEstadistico(listaDeEstadisticas);
        procesadorEstadistico.procesarModoDaemon(listaDeArchivosAProcesar);
    }
}
