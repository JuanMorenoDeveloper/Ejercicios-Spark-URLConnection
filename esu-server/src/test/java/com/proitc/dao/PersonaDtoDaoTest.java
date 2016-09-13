package com.proitc.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.proitc.dto.PersonaDto;

public class PersonaDtoDaoTest {

	private List<PersonaDto> personaDTOs;
	private GenericDao<PersonaDto, Integer> personaDao;

	@Before
	public void init() {
		personaDTOs = Arrays.asList(new PersonaDto(0, "Peter", "peter@test.com", 24),
				new PersonaDto(1, "Jhon", "jhon@test.com", 30), new PersonaDto(2, "Maria", "mary@test.com", 27),
				new PersonaDto(3, "Charles", "charles@test.com", 19), new PersonaDto(4, "Ana", "ana@test.com", 20));
		personaDao = new PersonaDtoDao(personaDTOs);
	}

	@Test
	public void daoTest() {
		PersonaDto personaDto = personaDao.findById(1);
		assertEquals("Jhon", personaDto.getNombre());
		List<PersonaDto> personaDtos = personaDao.findAll();
		assertEquals(personaDtos.size(), this.personaDTOs.size());		
	}
}
