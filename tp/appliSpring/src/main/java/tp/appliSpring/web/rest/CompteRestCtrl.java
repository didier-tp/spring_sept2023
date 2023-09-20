package tp.appliSpring.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.converter.GenericMapper;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;
import tp.appliSpring.dto.CompteDto;

@RestController //@Component de type controller d'api rest
@RequestMapping(value="/rest/api-bank/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired
	private ServiceCompte serviceCompte;
	
	/*
	//V1 sans DTO
	//declencher en mode GET avec
	//http://localhost:8181/appliSpring/rest/api-bank/compte/1 ou 2 
	@GetMapping("/{id}")
	public Compte getCompteById(@PathVariable("id") long numeroCompte) {
		System.out.println("getCompteById() appelee avec numeroCompte="+numeroCompte);
		Compte compteEntity = serviceCompte.rechercherCompte( numeroCompte);
		System.out.println("getCompteById() retournant compteEntity="+compteEntity);
		return compteEntity;
		//NB: l'objet retourné sera automatiquement converti au format json
	}
	*/
	
	//V2 avec DTO:
	//declencher en mode GET avec
	//http://localhost:8181/appliSpring/rest/api-bank/compte/1 ou 2 
	@GetMapping("/{id}")
	public CompteDto getCompteById(@PathVariable("id") long numeroCompte) {
			System.out.println("getCompteById() appelee avec numeroCompte="+numeroCompte);
			Compte compteEntity = serviceCompte.rechercherCompte( numeroCompte);
			System.out.println("getCompteById() retournant compteEntity="+compteEntity);
			
			return GenericMapper.MAPPER.map(compteEntity, CompteDto.class);
			//NB: l'objet retourné sera automatiquement converti au format json
		}

	public CompteRestCtrl() {
	}

}
