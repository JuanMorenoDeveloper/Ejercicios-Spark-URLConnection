package com.proitc.server;

import spark.Spark;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.proitc.dao.PersonaDtoDao;
import com.proitc.dto.PersonaDto;

public class Server {
	private static final Logger log = LoggerFactory.getLogger(Server.class.getName());

	private static List<PersonaDto> personaDtos = Arrays.asList(new PersonaDto(0, "Peter", "peter@test.com", 24),
			new PersonaDto(1, "Jhon", "jhon@test.com", 30), new PersonaDto(2, "Maria", "mary@test.com", 27),
			new PersonaDto(3, "Charles", "charles@test.com", 19), new PersonaDto(4, "Ana", "ana@test.com", 20));

	private static PersonaDtoDao personaDtoDao = new PersonaDtoDao(personaDtos);

	public static void main(String[] args) {
		log.info("Iniciando servidor en puerto 8080");
		Spark.port(8080);
		Gson gson = new Gson();
		//// @formatter:off
		Spark.get("/",
				(request, response) -> "Servicio demo para consultas de personas, <br> "
						+ "Puedes usar las siguientes rutas:<br>"
						+ "<a href='http://localhost:8080/persona/1'>http://localhost:8080/persona/1</a>"
						+ "->Muestra los datos de una persona<br>"
						+ "<a href='http://localhost:8080/persona/'>http://localhost:8080/persona/</a>"
						+ "->Muestra el listado de personas<br>");
		// @formatter:on
		Spark.get("persona/:id", "application/json",
				(request, response) -> personaDtoDao.findById(Integer.parseInt(request.params("id"))), gson::toJson);
		Spark.get("persona/", "application/json", (request, response) -> personaDtoDao.findAll(), gson::toJson);
		//Post como x-www-urlencoded
		Spark.post("persona/", (request, response) -> {
			PersonaDto personaDto = gson.fromJson(request.queryParams("persona"), PersonaDto.class);
			personaDtoDao.save(personaDto);
			return "Guardado correctamente";
		});
		Spark.put("persona/", (request, response) -> {
			PersonaDto personaDto = gson.fromJson(request.queryParams("persona"), PersonaDto.class);
			personaDtoDao.update(personaDto);
			return "Actualizado correctamente";
		});
		Spark.delete("persona/", (request, response) -> {
			PersonaDto personaDto = gson.fromJson(request.queryParams("persona"), PersonaDto.class);
			personaDtoDao.delete(personaDto);
			return "Eliminado correctamente";
		});
	}

}
