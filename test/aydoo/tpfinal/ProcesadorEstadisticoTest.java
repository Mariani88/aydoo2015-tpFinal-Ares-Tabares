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

        ProcesadorEstadistico procesadorEstadistico = new ProcesadorEstadistico();
        procesadorEstadistico.procesarModoOnDemand("archivosAProcesar/directorioDePruebaConDosZip");
        Path reporte = Paths.get("reportes/salidaUnica.yml");

        Assert.assertTrue(Files.size(reporte) > 0);
    }

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

        ProcesadorEstadistico procesadorEstadistico = new ProcesadorEstadistico();
        procesadorEstadistico.procesarModoDaemon(listaDeArchivosAProcesar);
    }
}
