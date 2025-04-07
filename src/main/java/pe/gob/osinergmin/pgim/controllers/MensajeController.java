package pe.gob.osinergmin.pgim.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import pe.gob.osinergmin.pgim.dtos.MensajeDTO;

/**
 * @descripción: Controlador que permite gestionar los mensajes entre clientes y el servidor.
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 05/12/2022
 */
@Controller
public class MensajeController extends BaseController {

    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public MensajeController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * Permite escuchar y renviar el mensaje a todos los clientes conectados.
     * @param mensajeDTO
     * @return
     */
    @MessageMapping("/mensaje")
    @SendTo("/mensajes/mensaje")
    public MensajeDTO recibeMensaje(MensajeDTO mensajeDTO) {

        mensajeDTO.setFecha(new Date());

        return mensajeDTO;
    }

    /**
     * Permite enviar el mensaje a todos los clientes conectados.
     * Se usa desde el BE.
     * @param mensajeDTO
     */
    public void enviarMensaje(MensajeDTO mensajeDTO) {
        this.simpMessagingTemplate.convertAndSend("/mensajes/mensaje", mensajeDTO);
    }

}
