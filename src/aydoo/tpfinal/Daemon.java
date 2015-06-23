package aydoo.tpfinal;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Daemon {

    private String directorioAMonitorear;
    ProcesadorEstadistico procesadorEstadistico;

    public Daemon(String directorioAMonitorear, ProcesadorEstadistico procesadorEstadistico){
        this.directorioAMonitorear = directorioAMonitorear;
        this.procesadorEstadistico = procesadorEstadistico;
    }

    public void monitorear(){

        boolean loop;
        //Primer estado del directorio
        List<String> contenidoInicialDelDirectorio = this.getContenidoDelDirectorio(this.directorioAMonitorear);
        List<String> contenidoActualDelDirectorio;
        loop = true;

        if (contenidoInicialDelDirectorio.size() > 0 ){
            this.procesadorEstadistico.procesarModoDaemon(contenidoInicialDelDirectorio);
            contenidoInicialDelDirectorio = this.getContenidoDelDirectorio(this.directorioAMonitorear);
        }

        while (loop){
            try {
                Thread.sleep(5000);
                contenidoActualDelDirectorio = this.getContenidoDelDirectorio(this.directorioAMonitorear);

                if(!contenidoActualDelDirectorio.equals(contenidoInicialDelDirectorio)){
                    List<String> archivosNuevos = this.encontrarArchivosNuevos(contenidoInicialDelDirectorio,contenidoActualDelDirectorio);
                    if(archivosNuevos.size() > 0){
                        this.procesadorEstadistico.procesarModoDaemon(archivosNuevos);
                    }
                    contenidoInicialDelDirectorio = contenidoActualDelDirectorio;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    private List<String> encontrarArchivosNuevos(List<String> archivosPrevios,List<String> archivosActuales){
        List<String> archivosNuevos = new ArrayList<>();
        boolean yaExiste;
        for (String nombreArchivoActual: archivosActuales){
            yaExiste = false;
            for (String nombreArchivoPrevio: archivosPrevios){
                if (nombreArchivoActual.equals(nombreArchivoPrevio)){
                    yaExiste = true;
                    break;
                }
            }

            if (!yaExiste){
                archivosNuevos.add(nombreArchivoActual);
            }
        }

        return archivosNuevos;
    }
}
