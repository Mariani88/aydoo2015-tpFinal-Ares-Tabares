package aydoo.tpfinal;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class ProcesadorEstadisticoTest {

    @Test
    public void cuandoElDirectorioDeEntradaTieneContenidoElProcesadorOnDemandDebeGenerarUnArchivoDeSalidaConTamanoMayorACero() throws IOException {

        ProcesadorEstadistico procesadorEstadistico = new ProcesadorEstadistico();
        procesadorEstadistico.procesarModoOnDemand("archivosAProcesar/directorioDePruebaConUnZipQueTieneUnSoloCSVConUnaLinea");
        Path reporte = Paths.get("reportes/salidaUnica.yml");

        Assert.assertTrue(Files.size(reporte) > 0);
    }

    @Test
    public void cuandoSePasaUnaListaDeDosArchivosElProcesadorOnDemandDebeGenerarDosArchivosDeSalidaConTamanoMayorACero() throws IOException {

        List<String> listaDeArchivosAProcesar = new ArrayList<>();
        listaDeArchivosAProcesar.add("archivosAProcesar/directorioDePruebaConDosZip/CSVConTresLineas.zip");
        listaDeArchivosAProcesar.add("archivosAProcesar/directorioDePruebaConDosZip/CSVConUnaLinea.zip");

        ProcesadorEstadistico procesadorEstadistico = new ProcesadorEstadistico();
        procesadorEstadistico.procesarModoDaemon(listaDeArchivosAProcesar);
        Path reporteCSVConTresLineas = Paths.get("reportes/CSVConTresLineas.salida.yml");
        Path reporteCSVConUnaLinea = Paths.get("reportes/CSVConUnaLinea.salida.yml");

        Assert.assertTrue(Files.size(reporteCSVConTresLineas) > 0);
        Assert.assertTrue(Files.size(reporteCSVConUnaLinea) > 0);
        
        volverArchivoAlOrigen();
    }
    
	private void volverArchivoAlOrigen() throws IOException {
		Path origen = Paths.get("archivosProcesados");
		Path destino = Paths.get("archivosAProcesar/directorioDePruebaConDosZip");
		
		 Files.move(origen,destino,REPLACE_EXISTING);
	}
}
