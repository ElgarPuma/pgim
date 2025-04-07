package pe.gob.osinergmin.pgim.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/***
 * Clase que permite configurar los parámetros generales del broker de mensajes FE-BE-FE y BE-FE
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
	private PropertiesConfig propertiesConfig;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        String urlPgimCliente = this.propertiesConfig.getUrlPgimCliente();

        registry.addEndpoint("/mensajes-websocket") // Esta configuración es importante porque vamos a utilizarla para
                                                // conectarnos desde el FE.
                .setAllowedOrigins(urlPgimCliente) // Configurar el origins para CORS.
                .withSockJS(); // Para utilizar SockJS, recordemos que STOMP lo utilizará.
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // El prefijo es para el nombre de los eventos que se van a implementar.
        registry.enableSimpleBroker("/mensajes/"); // Prefijo del nombre de los eventos.

        // Prefijo del destino, esto es cuando publicamos un mensaje hacia un destino
        registry.setApplicationDestinationPrefixes("/app"); // Prefijo para el destino en donde vamos a publicar.
    }

}
