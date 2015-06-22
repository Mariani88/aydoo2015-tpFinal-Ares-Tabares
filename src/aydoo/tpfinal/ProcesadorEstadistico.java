package aydoo.tpfinal;


import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ProcesadorEstadistico {

    private String directorioDeEntrada;
    private String directorioDeArchivosProcesados;
    private String directorioDeSalidaDeReportes;
    private List<Estadistica> estadisticasDisponibles;

    public ProcesadorEstadistico(List<Estadistica> estadisticasDisponibles) {
        this.directorioDeArchivosProcesados = "archivosProcesados/";
        this.directorioDeSalidaDeReportes = "reportes/";
        this.estadisticasDisponibles = estadisticasDisponibles;
    }

    private List<Recorrido> procesarArchivoZip(String rutaAZip) {
        List<Recorrido> listaDeRecorridos = new ArrayList<>();
        ZipFile zip;
        try {
            zip = new ZipFile(rutaAZip);
            Enumeration contenidoDelZip = zip.entries();

            while(contenidoDelZip.hasMoreElements()){
                ZipEntry zipEntry = (ZipEntry) contenidoDelZip.nextElement();
                try {
                    String contenidoDelCSV = IOUtils.toString(zip.getInputStream(zipEntry), StandardCharsets.UTF_8.name());
                    listaDeRecorridos.addAll(this.generarRecorridos(contenidoDelCSV));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return listaDeRecorridos;
    }

    private List<Recorrido> generarRecorridos(String contenidoDelCSV){
        List<Recorrido> listaDeRecorridos = new ArrayList<>();
        Scanner scanner = new Scanner(contenidoDelCSV);
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            if (!linea.equals("usuarioid;bicicletaid;origenfecha;origenestacionid;origennombre;destinofecha;destinoestacionid;destinonombre;tiempouso")){
                String[] lineaSeparadaPorComas = linea.split(";");
                Recorrido recorrido = new Recorrido(lineaSeparadaPorComas[0],lineaSeparadaPorComas[1],lineaSeparadaPorComas[2],lineaSeparadaPorComas[3],lineaSeparadaPorComas[4],lineaSeparadaPorComas[5],lineaSeparadaPorComas[6],lineaSeparadaPorComas[7],lineaSeparadaPorComas[8]);
                listaDeRecorridos.add(recorrido);
            }
        }
        return listaDeRecorridos;
    }

    private List<String> generarReporteEstadisticas(List<Recorrido> listaDeRecorridos){
        List<String> contenidoEnFormatoYML = new ArrayList<>();
        for (Estadistica estadistica: this.estadisticasDisponibles){
            contenidoEnFormatoYML.add(estadistica.getNombreEstadistica() + ":");
            contenidoEnFormatoYML.add("     " + estadistica.generarEstadistica(listaDeRecorridos));
        }
        return contenidoEnFormatoYML;
    }

    private void guardarReporteEstadisticasADisco(String nombreArchivoSalida, List<String> contenidoEnFormatoYML){
        Path archivo = Paths.get(this.directorioDeSalidaDeReportes + nombreArchivoSalida);
        try {
            Files.write(archivo, contenidoEnFormatoYML, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void procesarModoOnDemand(String directorioDeEntrada){
        this.directorioDeEntrada = directorioDeEntrada;
        List<Recorrido> listaDeRecorridos = new ArrayList();
        List<File> archivosZipDentroDelDirectorio = this.obtenerArchivosZipDentroDelDirectorio();

        for (File archivoZip: archivosZipDentroDelDirectorio){
            listaDeRecorridos.addAll(this.procesarArchivoZip(archivoZip.toString()));
        }
        this.guardarReporteEstadisticasADisco("salidaUnica.yml",this.generarReporteEstadisticas(listaDeRecorridos));
    }

    private List<File> obtenerArchivosZipDentroDelDirectorio(){
        List<File> listaDeZips = new ArrayList<>();
        File directorio = new File (this.directorioDeEntrada);

        if(directorio.exists()){
            File[] listaDeArchivos = directorio.listFiles();


            for (File file : listaDeArchivos){
                if (file.isFile()){

                    if(file.getName().endsWith(".zip")){
                        listaDeZips.add(file);
                    }
                }
            }
        }

        return listaDeZips;
    }
}
