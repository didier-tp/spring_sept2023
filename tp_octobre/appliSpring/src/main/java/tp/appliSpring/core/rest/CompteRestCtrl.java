package tp.appliSpring.core.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	@GetMapping(value="/{numCompte}" ) 
	public CompteL0 getCompteByNumero(@PathVariable("numCompte") Long num) { 
	     return dtoConverter.compteToCompteL0(serviceCompte.rechercherCompteParNumero(num)); 
	}
	
	@DeleteMapping(value="/{numCompte}" ) 
	public ResponseEntity<?> deleteCompteByNumero(@PathVariable("numCompte") Long num) { 
	    try {
			serviceCompte.supprimerCompte(num);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			//on retourne 200/OK ou 204/NO_CONTENT
		} catch (Exception e) {
			//e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	//URL de déclenchement: .../appliSpring/api-rest/compte
	//en mode POST avec dans la partie "body" :
	// { "numero" : null , "label" : "nouveauCompte" , "solde" : 50.0 }
	//ou bien
	//{  "label" : "nouveauCompte" , "solde" : 50.0 }
	@PostMapping(value="" ) 
	public CompteL0 postCompte(@RequestBody CompteL0 compteDto) { 
	     Compte compte = dtoConverter.compteL0ToCompte(compteDto);
	     Compte compteSauvegardeAvecClefPrimaireAutoIncrementee = serviceCompte.sauvegarderCompte(compte);
	     return dtoConverter.compteToCompteL0(compteSauvegardeAvecClefPrimaireAutoIncrementee);
	}

	/*
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
	*/
	
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
