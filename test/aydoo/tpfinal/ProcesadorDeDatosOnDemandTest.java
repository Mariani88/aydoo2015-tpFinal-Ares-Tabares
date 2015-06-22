package aydoo.tpfinal;


import org.junit.Assert;
import org.junit.Test;


public class ProcesadorDeDatosOnDemandTest {

/*    @Test
    public void cuandoElDirectorioNoTieneArchivosLaListaDeRecorridosDebeSerNula(){
        String directorio;

        if (System.getProperty("os.name").equals("Linux")){

            directorio = "/projects/aydoo/aydoo2015-tpFinal-Ares-Tabares/archivosAProcesar/directorioVacio";

        }

        else{

            directorio = "C:\\GitProjects\\RepositorioDeArchivos\\";

        }
        ProcesadorDeDatos procesadorDeDatos = new ProcesadorDeDatosOnDemand(directorio);

        Assert.assertTrue(procesadorDeDatos.procesarDirectorioDeEntrada().isEmpty());

    }*/

    @Test
    public void cuandoElDirectorioNoExisteLaListaDeRecorridosDebeSerNula(){
        String directorio;

        if (System.getProperty("os.name").equals("Linux")){

            directorio = "/projects/aydoo/aydoo2015-tpFinal-Ares-Tabares/archivProcesar/directorioVacio";

        }

        else{

            directorio = "C:\\GitProjects\\RepositorioDeArchivos\\directorioInexistente";

        }
        ProcesadorDeDatos procesadorDeDatos = new ProcesadorDeDatosOnDemand(directorio);

        Assert.assertTrue(procesadorDeDatos.procesarDirectorioDeEntrada().isEmpty());

    }

/*    @Test
    public void cuandoElDirectorioTieneUnSoloArchivoZipConUnSoloCSVConUnaLineaLaListaDeRecorridosDebeTenerUnSoloRecorrido(){
        String directorio;

        if (System.getProperty("os.name").equals("Linux")){

            directorio = "/projects/aydoo/aydoo2015-tpFinal-Ares-Tabares/archivosAProcesar/directorioDePruebaConUnZipQueTieneUnSoloCSVConUnaLinea";

        }

        else{

            directorio = "C:\\GitProjects\\RepositorioDeArchivos\\";

        }
        ProcesadorDeDatos procesadorDeDatos = new ProcesadorDeDatosOnDemand(directorio);

        Assert.assertEquals(1,procesadorDeDatos.procesarDirectorioDeEntrada().size());

    }*/
}
