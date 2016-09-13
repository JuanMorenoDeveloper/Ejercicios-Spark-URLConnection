package com.proitc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.proitc.dto.PersonaDto;

public class PersonaDtoDao implements GenericDao<PersonaDto, Integer> {
	private final Logger log = Logger.getLogger(PersonaDtoDao.class.getName());
	private List<PersonaDto> personaDtos;

	public PersonaDtoDao(List<PersonaDto> personaDtos) {
		this.personaDtos = new ArrayList<>(personaDtos);
	}

	@Override
	public PersonaDto findById(Integer id) {
		return personaDtos.get(id);
	}

	@Override
	public List<PersonaDto> findAll() {
		return personaDtos;
	}

	@Override
	public void save(PersonaDto t) {
		personaDtos.add(t);
	}

	@Override
	public void update(PersonaDto t) {
		if (personaDtos.contains(t)) {
			personaDtos.remove(t);
			personaDtos.add(t);
		}
		log.warning("No se actualizo ningún registro");
	}

	@Override
	public void delete(PersonaDto t) {
		personaDtos.remove(t);
	}

}
