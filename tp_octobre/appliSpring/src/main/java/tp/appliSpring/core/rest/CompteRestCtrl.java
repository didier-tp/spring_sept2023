package tp.appliSpring.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;

@RestController 
@RequestMapping(value="/api-rest/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired
	private ServiceCompte serviceCompte;
	
	//RECHERCHE UNIQUE selon RESOURCE-ID:
	//URL de déclenchement: .../appliSpring/api-rest/compte/1
	@GetMapping(value="/{numCompte}" ) 
	public Compte getCompteByNumero(@PathVariable("numCompte") Long num) { 
	 return serviceCompte.rechercherCompteParNumero(num); 
	}
	
	//...


}
