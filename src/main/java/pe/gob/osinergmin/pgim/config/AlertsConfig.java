package pe.gob.osinergmin.pgim.config;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.controllers.MensajeController;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.MensajeDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTO;
import pe.gob.osinergmin.pgim.services.IprocesoAlertaService;
import pe.gob.osinergmin.pgim.utils.TipoMensaje;

@Component
@EnableScheduling
@Slf4j
public class AlertsConfig {

  @Autowired
  private IprocesoAlertaService iprocesoAlertaService;

  @Autowired
  private MensajeController mensajeController;

  @Scheduled(cron = "0 0 8 ? * 1", zone = "America/Lima")
  public void NotificarAlertasCumplimientoSemanal() {
    log.info("cronpgim.semanal - Hora: " + (new Date()).toString());
        try {
      List<PgimIprocesoAlertaDTO> lPgimIprocesoAlertaDTONotificado = new ArrayList<PgimIprocesoAlertaDTO>();
      lPgimIprocesoAlertaDTONotificado = this.iprocesoAlertaService.notificarAlertasCumpliemientoSemanal(this.obtenerAuditoriaAlertas());

      // Enviando mensaje para los clientes:
      for (PgimIprocesoAlertaDTO pgimIprocesoAlertaDTO : lPgimIprocesoAlertaDTONotificado) {
        MensajeDTO mensajeDTO = new MensajeDTO();
        mensajeDTO.setTitulo(pgimIprocesoAlertaDTO.getDescNoAlerta());
        mensajeDTO.setTipo(TipoMensaje.ALERTA_TAREA);
        mensajeDTO.setNombreUsuarioOrigen(null);
        mensajeDTO.setNombreUsuarioDestino(pgimIprocesoAlertaDTO.getNoUsuarioDestino());
        mensajeDTO.setFecha(new Date());
        mensajeDTO.setTexto(pgimIprocesoAlertaDTO.getDescDeAlerta());
        this.mensajeController.enviarMensaje(mensajeDTO);
      }

    } catch (Exception e) {
      log.error("PROGRAMADOR: Ocurrió un error al procesar las alertas de cumplimiento semanal: ");
      log.error(e.getMessage(), e);
    }
  }

  @Scheduled(cron = "10 0 8 * * ?", zone = "America/Lima")
  public void NotificarAlertasCumplimientoDiario() {
    log.info("cronpgim.diario - Hora: " + (new Date()).toString());
    try {
      List<PgimIprocesoAlertaDTO> lPgimIprocesoAlertaDTONotificado = new ArrayList<PgimIprocesoAlertaDTO>();
      lPgimIprocesoAlertaDTONotificado = this.iprocesoAlertaService.notificarAlertasCumpliemientoDiario(this.obtenerAuditoriaAlertas());

      // Enviando mensaje para los clientes:
      for (PgimIprocesoAlertaDTO pgimIprocesoAlertaDTO : lPgimIprocesoAlertaDTONotificado) {
        MensajeDTO mensajeDTO = new MensajeDTO();
        mensajeDTO.setTitulo(pgimIprocesoAlertaDTO.getDescNoAlerta());
        mensajeDTO.setTipo(TipoMensaje.ALERTA_TAREA);
        mensajeDTO.setNombreUsuarioOrigen(null);
        mensajeDTO.setNombreUsuarioDestino(pgimIprocesoAlertaDTO.getNoUsuarioDestino());
        mensajeDTO.setFecha(new Date());
        mensajeDTO.setTexto(pgimIprocesoAlertaDTO.getDescDeAlerta());
        this.mensajeController.enviarMensaje(mensajeDTO);
      }

    } catch (Exception e) {
      log.error("PROGRAMADOR: Ocurrió un error al procesar las alertas de cumplimiento diario: ");
      log.error(e.getMessage(), e);
    }
  }

  public AuditoriaDTO obtenerAuditoriaAlertas() throws Exception {

    InetAddress localHost = InetAddress.getLocalHost();
    String direccionRemota = localHost.getHostAddress();

    AuditoriaDTO auditoriaDTO = new AuditoriaDTO();

    auditoriaDTO.setTerminal(direccionRemota);
    auditoriaDTO.setFecha(new Date());
    auditoriaDTO.setUsername("PROGRAMADOR");

    return auditoriaDTO;
  }

}
