package aydoo.tpfinal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class ProcesadorDeDatos {

    protected String directorioDeEntrada;
    protected String directorioDeArchivosProcesados;
    protected List<Recorrido> listaDeRecorridos;


    public abstract List<Recorrido> procesarDirectorioDeEntrada();

    protected List<File> obtenerArchivosZipDentroDelDirectorio(){
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