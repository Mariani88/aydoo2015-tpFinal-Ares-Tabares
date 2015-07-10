package aydoo.tpfinal;

import java.util.Timer;
import java.util.TimerTask;

public class Daemon {

	private Timer temporizadorDeMonitoreo;
	private TimerTask tarea;

	public Daemon(String directorioAMonitorear,
			ProcesadorEstadistico procesadorEstadistico) {
		
		this.temporizadorDeMonitoreo = new Timer();
		this.tarea = new TareaProcesarZips(directorioAMonitorear, procesadorEstadistico);
	}
	
	public void monitorear() {
		this.temporizadorDeMonitoreo.schedule(this.tarea, 0, 5000);
	}

	public void pararMonitoreo() {
		this.temporizadorDeMonitoreo.cancel();
	}
}
