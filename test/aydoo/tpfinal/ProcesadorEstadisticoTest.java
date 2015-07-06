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

	private static final String DIRECTORIO_CON_DOS_ZIPS = "archivosAProcesar/directorioDePruebaConDosZip";
	private static final String DIRECTORIO_CON_UN_ZIP = "archivosAProcesar/directorioDePruebaConUnZipQueTieneUnSoloCSVConUnaLinea";

	
    @Test
    public void cuandoElDirectorioDeEntradaTieneContenidoElProcesadorOnDemandDebeGenerarUnArchivoDeSalidaConTamanoMayorACero() throws IOException {

        ProcesadorEstadistico procesadorEstadistico = new ProcesadorEstadistico();
        procesadorEstadistico.procesarModoOnDemand(DIRECTORIO_CON_UN_ZIP);
        Path reporte = Paths.get("reportes/salidaUnica.yml");

        Assert.assertTrue(Files.size(reporte) > 0);
        
        volverArchivoAlOrigen("archivosProcesados/CSVConUnaLinea.zip", DIRECTORIO_CON_UN_ZIP+"/CSVConUnaLinea.zip");
    }

    @Test
    public void cuandoSePasaUnaListaDeDosArchivosElProcesadorOnDemandDebeGenerarDosArchivosDeSalidaConTamanoMayorACero() throws IOException {

        List<String> listaDeArchivosAProcesar = new ArrayList<>();
        listaDeArchivosAProcesar.add(DIRECTORIO_CON_DOS_ZIPS+"/CSVConTresLineas.zip");
        listaDeArchivosAProcesar.add(DIRECTORIO_CON_DOS_ZIPS+"/CSVConUnaLinea.zip");

        ProcesadorEstadistico procesadorEstadistico = new ProcesadorEstadistico();
        procesadorEstadistico.procesarModoDaemon(listaDeArchivosAProcesar);
        Path reporteCSVConTresLineas = Paths.get("reportes/CSVConTresLineas.salida.yml");
        Path reporteCSVConUnaLinea = Paths.get("reportes/CSVConUnaLinea.salida.yml");

        Assert.assertTrue(Files.size(reporteCSVConTresLineas) > 0);
        Assert.assertTrue(Files.size(reporteCSVConUnaLinea) > 0);
        
		volverArchivoAlOrigen("archivosProcesados/CSVConTresLineas.zip",DIRECTORIO_CON_DOS_ZIPS+"/CSVConTresLineas.zip");
		volverArchivoAlOrigen("archivosProcesados/CSVConUnaLinea.zip",DIRECTORIO_CON_DOS_ZIPS+"/CSVConUnaLinea.zip");
    }
    
	private void volverArchivoAlOrigen(String directorioOrigen, String directorioDestino)
			throws IOException {
		Path origen = Paths.get(directorioOrigen);
		Path destino = Paths.get(directorioDestino);

		Files.move(origen, destino, REPLACE_EXISTING);
	}
}
