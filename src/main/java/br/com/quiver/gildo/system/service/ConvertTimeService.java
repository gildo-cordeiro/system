package br.com.quiver.gildo.system.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.quiver.gildo.system.model.Time;
import br.com.quiver.gildo.system.repositories.TimeRepository;

/**
 * Classe negocial que vai fazer validações e chamar os metodos necessarios do repository 
 * @author gildo_cordeiro
 *
 */
@Service
public class ConvertTimeService {
	
	@Autowired
	private TimeRepository timeRepository;
	
	public void calcular(Time secondtTime) {
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		String time = null;
		
		if(secondtTime.getSeconds() != null && secondtTime.getSeconds() > 0) {
			df.setTimeZone(timeZone);	
			time = df.format(new Date(secondtTime.getSeconds()*1000L));
			

			Time t = new Time();
			t.setTimeFormat(time);
			t.setSeconds(secondtTime.getSeconds());
			
			timeRepository.save(t);
		}
		
	}
	
	public List<Time> getTimeFormat(){
		return timeRepository.findAll();
	}

}
