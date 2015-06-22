package aydoo.tpfinal;

import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class ProcesadorDeDatosOnDemand extends ProcesadorDeDatos {
    public ProcesadorDeDatosOnDemand(String directorio) {
        super();
        this.directorioDeEntrada = directorio;
        this.listaDeRecorridos = new LinkedList<>();
        if (System.getProperty("os.name").equals("Linux")){
            this.directorioDeArchivosProcesados = "/projects/aydoo/aydoo2015-tpFinal-Ares-Tabares/archivosProcesados/";
        }

        else{
            this.directorioDeArchivosProcesados = "C:\\GitProjects\\RepositorioDeArchivos\\";
        }

    }


    @Override
    public List<Recorrido> procesarDirectorioDeEntrada() {
        List<Recorrido> listaDeRecorridos = new ArrayList();
        List<File> archivosZipDentroDelDirectorio = this.obtenerArchivosZipDentroDelDirectorio();
        for (File archivoZip: archivosZipDentroDelDirectorio){
            listaDeRecorridos.addAll(this.procesarArchivoZip(archivoZip.toString()));
        }
        return listaDeRecorridos;
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
            String[] lineaSeparadaPorComas = linea.split(";");
            Recorrido recorrido = new Recorrido(lineaSeparadaPorComas[0],lineaSeparadaPorComas[1],lineaSeparadaPorComas[2],lineaSeparadaPorComas[3],lineaSeparadaPorComas[4],lineaSeparadaPorComas[5],lineaSeparadaPorComas[6],lineaSeparadaPorComas[7],lineaSeparadaPorComas[8]);
            listaDeRecorridos.add(recorrido);
        }
        return listaDeRecorridos;
    }


}
