package com.proitc;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.proitc.dto.PersonaDto;

public class PersonaDtoTest {
	private Gson gson;
	private PersonaDto personaDTO;

	@Before
	public void init() {
		gson = new Gson();
		personaDTO = new PersonaDto(0,"Peter", "pedro@test.com", 24);
	}

	@Test
	public void test() {
		String json = gson.toJson(personaDTO);
		assertNotNull(json);
		System.out.println(json);
	}
}
