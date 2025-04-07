package pe.gob.osinergmin.pgim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

 //@SpringBootApplication
@SpringBootApplication(exclude = JmxAutoConfiguration.class)//solo para local
public class PgimApplication {

	public static void main(String[] args) {
		//args = new String[] {"--spring.config.location=file:///opt/pgim_siged/temp/application.properties"};		
		SpringApplication.run(PgimApplication.class, args);
	}
	
	/*
	 * Inicializar BCryptPasswordEncoder
	 */
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}
