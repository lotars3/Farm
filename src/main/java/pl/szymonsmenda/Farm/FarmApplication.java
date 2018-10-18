package pl.szymonsmenda.Farm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.szymonsmenda.Farm.controller.Controller;

@SpringBootApplication
public class FarmApplication {

	public static void main(String[] args) {
		new Controller().start();
	}
}
