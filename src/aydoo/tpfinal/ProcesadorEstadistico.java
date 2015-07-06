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
import java.util.LinkedList;
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
    static double tiempoInicial;
    
    public ProcesadorEstadistico() {
        this.nombreDirectorioDeArchivosProcesados = "archivosProcesados/";
        this.nombreDirectorioDeSalidaDeReportes = "reportes/";
        this.prepararEstadisticas();
        this.crearDirectoriosDeSalida();
    }

    public static void main(String[] args) {
    	
        switch (args.length){

        	//Modo on-Demand
        	case 1:
                if (validarDirectorioDeEntrada(args[0])){
                	tiempoInicial = System.currentTimeMillis();
                	System.out.println("Procesando Archivos ...");
                    ProcesadorEstadistico procesadorOnDemand = new ProcesadorEstadistico();
                    procesadorOnDemand.procesarModoOnDemand(args[0]);
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

    private void procesarArchivoZip(String rutaAZip) {
    	
        ZipFile zip;
        
        try {
            zip = new ZipFile(rutaAZip);
            Enumeration<? extends ZipEntry> contenidoDelZip = zip.entries();
            
            while(contenidoDelZip.hasMoreElements()){
                ZipEntry zipEntry = (ZipEntry) contenidoDelZip.nextElement();
                
                try {
                    InputStream inputStream = zip.getInputStream(zipEntry);
                    this.generarRecorridos(inputStream);
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
    }

    private void generarRecorridos(InputStream contenidoDelCSV){
    	
        List<Recorrido> listaDeRecorridos = new LinkedList<Recorrido> ();
        Scanner scanner = new Scanner(contenidoDelCSV);
        
        String linea = scanner.nextLine();
        
        while (scanner.hasNextLine()) {
           linea = scanner.nextLine();
			String[] lineaSeparadaPorComas = linea.split(";");
			
			if (lineaSeparadaPorComas.length == 9) {
				Recorrido recorrido = new Recorrido(lineaSeparadaPorComas[0],
						lineaSeparadaPorComas[1], lineaSeparadaPorComas[2],
						lineaSeparadaPorComas[3], lineaSeparadaPorComas[4],
						lineaSeparadaPorComas[5], lineaSeparadaPorComas[6],
						lineaSeparadaPorComas[7], lineaSeparadaPorComas[8]);
				listaDeRecorridos.add(recorrido);
			}
            
			volcarDatosEstadisticos(listaDeRecorridos, scanner);
        }
       
        scanner.close();
    }

	private void volcarDatosEstadisticos(List<Recorrido> listaDeRecorridos,
			Scanner scanner) {
		if (listaDeRecorridos.size() == 2000 || !scanner.hasNextLine()){
			
			for (Estadistica estadistica: this.estadisticasDisponibles){
				estadistica.generarEstadistica(listaDeRecorridos);
			}
			
			listaDeRecorridos.clear();
		}
	}

  

	private List<String> generarReporteEstadisticas(){
        
		List<String> contenidoEnFormatoYML = new ArrayList<String>();
        
		for (Estadistica estadistica: this.estadisticasDisponibles){
        	
        	List<String> listaDeDatosEstadisticos = estadistica.obtenerEstadisticaResultante();
        	List<String> listaEnFormatoYML = estadistica.generarListaEnFormatoYML( listaDeDatosEstadisticos);
        	
            contenidoEnFormatoYML.addAll( listaEnFormatoYML );
            }
        
        return contenidoEnFormatoYML;
    }

    private void guardarReporteEstadisticasADisco(String nombreArchivoSalida, List<String> contenidoEnFormatoYML){
        
    	Path archivo = Paths.get(this.nombreDirectorioDeSalidaDeReportes + nombreArchivoSalida);
    	double tiempo  = (System.currentTimeMillis() - tiempoInicial)/1000 ; 
    	
    	contenidoEnFormatoYML.add("tiempo insumido:"+ tiempo+ " segundos");
        
        try {
            Files.write(archivo, contenidoEnFormatoYML, StandardCharsets.UTF_8);    
            System.out.println ("tiempo insumido:" + tiempo+ " segundos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void procesarModoOnDemand(String directorioDeEntrada){
    	
        this.nombreDirectorioDeEntrada = directorioDeEntrada;       
        List<File> archivosZipDentroDelDirectorio = this.obtenerArchivosZipDentroDelDirectorio();

        float procesados = 0;
        float total = archivosZipDentroDelDirectorio.size();
        for (File archivoZip: archivosZipDentroDelDirectorio){
        	
           this.procesarArchivoZip(archivoZip.toString());
           Path origen = Paths.get(archivoZip.toString());
           Path destino = Paths.get(this.nombreDirectorioDeArchivosProcesados + archivoZip.getName());

           try {
               Files.move(origen,destino,REPLACE_EXISTING);
               procesados+=1;
               System.out.println (procesados*100/total + "% completado");
           } catch (IOException e) {
               e.printStackTrace();
           }
        }
        
        this.guardarReporteEstadisticasADisco("salidaUnica.yml",this.generarReporteEstadisticas());
    }

    public void procesarModoDaemon(List<String> archivosAProcesar){
    	
        for (String archivoAProcesar: archivosAProcesar ){
        	
        	File archivo = new File(archivoAProcesar);
        	System.out.println("procesando archivo:"+ archivo.getName()+ " espere...");
            this.procesarArchivoZip(archivoAProcesar);
           
            int finDeNombre = archivo.getName().length()-4;
            String nombreYML =  archivo.getName().subSequence(0, finDeNombre) + ".salida.yml";
            
            this.guardarReporteEstadisticasADisco( nombreYML, this.generarReporteEstadisticas());
            Path origen = Paths.get(archivoAProcesar);
            Path destino = Paths.get(this.nombreDirectorioDeArchivosProcesados + archivo.getName());

            try {
                Files.move(origen,destino,REPLACE_EXISTING);
                System.out.println("archivo:"+ archivo.getName() + " procesado");
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