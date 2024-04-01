package com.curso.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Como tenemos el conecto de mysql debemos de escluirlo
 * para mientras se crean la conexion, si no se
 * excluye genera un error al correr el servidor*/
//(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class SpringEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEcommerceApplication.class, args);
	}

}
