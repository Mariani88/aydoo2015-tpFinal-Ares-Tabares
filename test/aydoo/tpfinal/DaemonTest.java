package aydoo.tpfinal;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class DaemonTest {

	private static final String DIRECTORIO_CON_DOS_ZIPS = "archivosAProcesar/directorioDePruebaConDosZip";
	private static final String DIRECTORIO_CON_UN_ZIP = "archivosAProcesar/directorioDePruebaConUnZipQueTieneUnSoloCSVConUnaLinea";

	@Test
	public void cuandoElDirectorioDeEntradaTieneContenidoAlProcesarloEnModoDeamonDebeGenerarUnArchivoDeSalidaConTamanoMayorACero()
			throws IOException, InterruptedException {

		ProcesadorEstadistico procesadorEstadistico = new ProcesadorEstadistico();
		Path reporte = Paths.get("reportes/CSVConUnaLinea.salida.yml");

		Daemon daemon = new Daemon(DIRECTORIO_CON_UN_ZIP, procesadorEstadistico);

		daemon.monitorear();

		Thread.sleep(1000);
		Assert.assertTrue(Files.size(reporte) > 0);

		volverArchivoAlOrigen("archivosProcesados/CSVConUnaLinea.zip", DIRECTORIO_CON_UN_ZIP+"/CSVConUnaLinea.zip");
	}

	@Test
	public void cuandoSePasaUnaListaDeDosArchivosAlProcesarloEnModoDeamonDebeGenerarDosArchivosDeSalidaConTamanoMayorACero()
			throws IOException, InterruptedException {

		ProcesadorEstadistico procesadorEstadistico = new ProcesadorEstadistico();
		Daemon daemon = new Daemon(DIRECTORIO_CON_DOS_ZIPS,procesadorEstadistico);

		Path reporteCSVConTresLineas = Paths.get("reportes/CSVConTresLineas.salida.yml");
		Path reporteCSVConUnaLinea = Paths.get("reportes/CSVConUnaLinea.salida.yml");

		daemon.monitorear();
		Thread.sleep(1000);

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
