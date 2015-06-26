package aydoo.tpfinal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import static java.nio.file.StandardCopyOption.*;

public class ProcesadorEstadistico {

    private String nombreDirectorioDeEntrada;
    private final String nombreDirectorioDeArchivosProcesados;
    private final String nombreDirectorioDeSalidaDeReportes;
    private List<Estadistica> estadisticasDisponibles;
    private final String encabezado;

    public ProcesadorEstadistico() {
        this.nombreDirectorioDeArchivosProcesados = "archivosProcesados/";
        this.nombreDirectorioDeSalidaDeReportes = "reportes/";
        this.prepararEstadisticas();
        this.encabezado = "usuarioid;bicicletaid;origenfecha;origenestacionid;origennombre;destinofecha;destinoestacionid;destinonombre;tiempouso";
        this.crearDirectoriosDeSalida();
    }

    public static void main(String[] args) {
        switch (args.length){

        	//Modo on-Demand
        	case 1:
                if (validarDirectorioDeEntrada(args[0])){
                	System.out.println("Procesando Archivos ...");
                    ProcesadorEstadistico procesadorOnDemand = new ProcesadorEstadistico();
                    procesadorOnDemand.procesarModoOnDemand(args[0]);
                    System.out.println("Completado");
                    break;
                }
                else{
                    imprimirMensajeDeErrorDirectorioInvalido();
                    break;
                }
            //Modo Daemon
            case 2:
                if (args[1].equals("demonio")){
                    if (validarDirectorioDeEntrada(args[0])){
                    	System.out.println("Modo demonio iniciado, para terminar presionar Ctrl+C");
                        ProcesadorEstadistico procesadorDaemon = new ProcesadorEstadistico();
                        Daemon daemon = new Daemon(args[0],procesadorDaemon);
                        daemon.monitorear();
                        break;
                    }
                }
                else{
                    imprimirMensajeDeErrorArgumentosInvalidos();
                    break;
                }
                
            default:
                imprimirMensajeDeErrorArgumentosInvalidos();
                break;

        }

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

                    InputStream inputStream = zip.getInputStream(zipEntry);
                    listaDeRecorridos.addAll(this.generarRecorridos(inputStream));
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            zip.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }



        return listaDeRecorridos;
    }

    private List<Recorrido> generarRecorridos(InputStream contenidoDelCSV){
        List<Recorrido> listaDeRecorridos = new ArrayList<>();
        Scanner scanner = new Scanner(contenidoDelCSV);
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            if (!this.encabezado.equals(linea)){
                String[] lineaSeparadaPorComas = linea.split(";");
                if (lineaSeparadaPorComas.length == 9){
                    Recorrido recorrido = new Recorrido(lineaSeparadaPorComas[0],lineaSeparadaPorComas[1],lineaSeparadaPorComas[2],lineaSeparadaPorComas[3],lineaSeparadaPorComas[4],lineaSeparadaPorComas[5],lineaSeparadaPorComas[6],lineaSeparadaPorComas[7],lineaSeparadaPorComas[8]);
                    listaDeRecorridos.add(recorrido);
                }
            }
        }
        return listaDeRecorridos;
    }

    private List<String> generarReporteEstadisticas(List<Recorrido> listaDeRecorridos){
        List<String> contenidoEnFormatoYML = new ArrayList<>();
        for (Estadistica estadistica: this.estadisticasDisponibles){
            contenidoEnFormatoYML.addAll(estadistica.generarListaEnFormatoYML(estadistica.generarEstadistica(listaDeRecorridos)));
            }
        return contenidoEnFormatoYML;
    }

    private void guardarReporteEstadisticasADisco(String nombreArchivoSalida, List<String> contenidoEnFormatoYML){
        Path archivo = Paths.get(this.nombreDirectorioDeSalidaDeReportes + nombreArchivoSalida);
        try {
            Files.write(archivo, contenidoEnFormatoYML, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void procesarModoOnDemand(String directorioDeEntrada){
        this.nombreDirectorioDeEntrada = directorioDeEntrada;
        List<Recorrido> listaDeRecorridos = new ArrayList();
        List<File> archivosZipDentroDelDirectorio = this.obtenerArchivosZipDentroDelDirectorio();

        for (File archivoZip: archivosZipDentroDelDirectorio){
            listaDeRecorridos.addAll(this.procesarArchivoZip(archivoZip.toString()));
        }
        this.guardarReporteEstadisticasADisco("salidaUnica.yml",this.generarReporteEstadisticas(listaDeRecorridos));
    }

    public void procesarModoDaemon(List<String> archivosAProcesar){
        for (String archivoAProcesar: archivosAProcesar ){
            List<Recorrido> listaDeRecorridos = new ArrayList();
            listaDeRecorridos.addAll(this.procesarArchivoZip(archivoAProcesar));
            File archivo = new File(archivoAProcesar);    
            this.guardarReporteEstadisticasADisco( archivo.getName().subSequence(0, archivo.getName().length()-4) + ".salida.yml", this.generarReporteEstadisticas(listaDeRecorridos));
            Path origen = Paths.get(archivoAProcesar);
            Path destino = Paths.get(this.nombreDirectorioDeArchivosProcesados + archivo.getName());

            try {
                Files.move(origen,destino,REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<File> obtenerArchivosZipDentroDelDirectorio(){
        List<File> listaDeZips = new ArrayList<>();
        File directorio = new File (this.nombreDirectorioDeEntrada);

        if(directorio.exists()){
            File[] listaDeArchivos = directorio.listFiles();


            for (File file : listaDeArchivos){
                if (file.isFile() && file.getName().endsWith(".zip")){
                    listaDeZips.add(file);
                }
            }
        }

        return listaDeZips;
    }

    private void prepararEstadisticas(){
        this.estadisticasDisponibles = new ArrayList<>();
        this.estadisticasDisponibles.add(new EstadisticaBicicletaMasUsada());
        this.estadisticasDisponibles.add(new EstadisticaBicicletaMenosUsada());
        this.estadisticasDisponibles.add(new EstadisticaRecorridoMasRealizado());
        this.estadisticasDisponibles.add(new EstadisticaTiempoPromedioDeUso());
    }

    private void crearDirectoriosDeSalida(){
        Path pathADirectorioDeArchivosProcesados = Paths.get(this.nombreDirectorioDeArchivosProcesados);
        Path pathADirectorioDeSalidaDeReportes = Paths.get(this.nombreDirectorioDeSalidaDeReportes);

        if(!Files.exists(pathADirectorioDeArchivosProcesados)){
            try {
                Files.createDirectory(pathADirectorioDeArchivosProcesados);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!Files.exists(pathADirectorioDeSalidaDeReportes)){
            try {
                Files.createDirectory(pathADirectorioDeSalidaDeReportes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean validarDirectorioDeEntrada(String argumento){
        boolean esValido;
        File directorioDeEntrada = new File(argumento);
        if(directorioDeEntrada.isDirectory()){
            String[] contenidoDelDirectorio = directorioDeEntrada.list();
            if (contenidoDelDirectorio.length > 0){
                esValido = true;
            }
            else{
                esValido = false;
            }
        }
        else{
            esValido = false;
        }

        return esValido;
    }

    private static void imprimirMensajeDeErrorDirectorioInvalido(){
        System.out.println("El directorio ingresado es invalido.");
    }

    private static void imprimirMensajeDeErrorArgumentosInvalidos(){
        System.out.println("Error: Debe proveer directorio y un modo de ejecucion valido");
        System.out.println("Ejemplos: java aydoo.tpfinal.ProcesadorEstadistico <directorio> demonio");
        System.out.println("o");
        System.out.println("java aydoo.tpfinal.ProcesadorEstadistico <directorio>");
    }
}