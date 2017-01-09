package com.proitc.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import com.google.gson.Gson;
import com.proitc.dto.PersonaDto;

public class Client {
	public static final String GET = "GET";
	public static final String POST = "POST";

	public static void main(String[] args) {
		int port = 8080;
		String schema = "http://";
		String address = "localhost";
		PersonaDto personaDtos[];
		String resultado = null;
		personaDtos = sendGet(schema + address + ":" + port + "/persona/", null);
		System.out.println("Arreglo");
		for (PersonaDto personaDto : personaDtos) {
			System.out.println(personaDto);
		}
		System.out.println("Objecto con id 2");
		personaDtos = sendGet(schema + address + ":" + port + "/persona/", "2");
		for (PersonaDto personaDto : personaDtos) {
			System.out.println(personaDto);
		}
		resultado = sendPost(schema + address + ":" + port + "/persona/",
				"{\"id\":15,\"nombre\":\"Marc\",\"email\":\"marc@test.com\",\"edad\":47}");
		System.out.println(resultado);
	}

	public static PersonaDto[] sendGet(String urlServer, String args) {
		Gson gson = new Gson();
		String inputLine;
		PersonaDto personaDtos[] = null;
		URL url;
		try {
			if (Objects.isNull(args)) {
				url = new URL(urlServer);
			} else {
				url = new URL(urlServer + args);
			}
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod(GET);
			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			while (Objects.nonNull(inputLine = in.readLine())) {
				if (Objects.isNull(args)) {
					personaDtos = gson.fromJson(inputLine, PersonaDto[].class);
				} else {
					personaDtos = new PersonaDto[1];
					System.out.println(inputLine);
					personaDtos[0] = gson.fromJson(inputLine, PersonaDto.class);
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return personaDtos;
	}

	public static String sendPost(String urlServer, String args) {
		StringBuffer result = new StringBuffer();
		String inputLine;
		try {
			URL url = new URL(urlServer);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod(POST);
			httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpURLConnection.setRequestProperty("charset", "utf-8");
			OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
			out.write("persona=" + args);
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			while (Objects.nonNull(inputLine = in.readLine())) {
				result.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
