package aydoo.tpfinal;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class TareaProcesarZips extends TimerTask{
	
	private ProcesadorEstadistico procesadorEstadistico;
	private String directorioAMonitorear;
	
	public TareaProcesarZips (String directorioAMonitorear,
			ProcesadorEstadistico procesadorEstadistico){
		
		this.directorioAMonitorear = directorioAMonitorear;
		this.procesadorEstadistico = procesadorEstadistico;
	}
	
	@Override
	public void run() {
		 List<String> contenidoInicialDelDirectorio = this.getContenidoDelDirectorio(this.directorioAMonitorear);
		 int detectado = contenidoInicialDelDirectorio.size();
		 
		 if (detectado > 0){
			 
			 System.out.println( detectado + " archivos nuevos detectados");
			 this.procesadorEstadistico.procesarModoDaemon(contenidoInicialDelDirectorio);
		 }
	}
	
    private List<String> getContenidoDelDirectorio(String directorio){
        List<String> contenidoDelDirectorio = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directorio))) {
            for (Path path : directoryStream) {
                contenidoDelDirectorio.add(path.toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return contenidoDelDirectorio;
    }
}