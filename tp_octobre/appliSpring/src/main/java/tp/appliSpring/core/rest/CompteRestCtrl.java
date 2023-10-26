package tp.appliSpring.core.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	//RECHERCHE MULTIPLE
	//URL de déclenchement: .../appliSpring/api-rest/compte
	//                      .../appliSpring/api-rest/compte?soldeMini=0
	@GetMapping(value="" ) 
	public List<Compte> getComptesByCriteria(@RequestParam(value="soldeMini",required=false) Double soldeMini){
		if(soldeMini==null)
			return serviceCompte.rechercherTousComptes();
		else
			return serviceCompte.rechercherComptesAvecSoldeMini(soldeMini);
	}


}
