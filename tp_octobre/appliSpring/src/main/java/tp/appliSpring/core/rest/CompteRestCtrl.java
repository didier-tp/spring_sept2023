package tp.appliSpring.core.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.core.converter.DtoConverter;
import tp.appliSpring.core.dto.CompteDto;
import tp.appliSpring.core.dto.CompteL0;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;

@RestController 
@RequestMapping(value="/api-rest/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired
	private ServiceCompte serviceCompte;
	
	@Autowired
	private DtoConverter dtoConverter;
	
	//RECHERCHE UNIQUE selon RESOURCE-ID:
	//URL de déclenchement: .../appliSpring/api-rest/compte/1
	
	/*
	@GetMapping(value="/{numCompte}" ) 
	public Compte getCompteByNumero(@PathVariable("numCompte") Long num) { 
	 return serviceCompte.rechercherCompteParNumero(num); 
	}
	*/
	
	@GetMapping(value="/{numCompte}" ) 
	public ResponseEntity<CompteDto> getCompteByNumero(@PathVariable("numCompte") Long num) { 
	 try {
		Compte compte = serviceCompte.rechercherCompteParNumero(num);
		return new ResponseEntity<CompteDto>( dtoConverter.compteToCompteL0(compte) , HttpStatus.OK);

	} catch (Exception e) {
		//e.printStackTrace();
		//return new ResponseEntity<Compte>(HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	} 
	}
	
	//RECHERCHE MULTIPLE
	//URL de déclenchement: .../appliSpring/api-rest/compte
	//                      .../appliSpring/api-rest/compte?soldeMini=0
	@GetMapping(value="" ) 
	public List<CompteL0> getComptesByCriteria(@RequestParam(value="soldeMini",required=false) Double soldeMini){
		if(soldeMini==null)
			return dtoConverter.map(serviceCompte.rechercherTousComptes(),CompteL0.class);
		else
			return dtoConverter.map(serviceCompte.rechercherComptesAvecSoldeMini(soldeMini),CompteL0.class);
	}


}
