package com.publica.grupo1.controller.internalservices;

import java.util.List;

import com.publica.grupo1.controller.Controller;
import com.publica.grupo1.model.dao.internalevents.InternalEventsDAO;
import com.publica.grupo1.model.entities.internalevents.InternalEvents;

public class InternalEventsController implements Controller<InternalEvents>{

		InternalEventsDAO intEventsDAO;
		
		public InternalEventsController(InternalEventsDAO intEventsDAO) {
			this.intEventsDAO = intEventsDAO;
		}

		@Override
		public InternalEvents selectById(int id) {
			return intEventsDAO.selectById(id);
		}

		@Override
		public int insert(InternalEvents object) {
			return intEventsDAO.insert(object);
		}

		@Override
		public void delete(InternalEvents object) {
			intEventsDAO.delete(object);
		}

		@Override
		public void update(InternalEvents object) {
			intEventsDAO.update(object);
		}

		@Override
		public List<InternalEvents> getAll() {
			return intEventsDAO.getAll();
		}
		
		
	}

