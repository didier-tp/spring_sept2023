package tp.appliSpring.core.converter;

import org.springframework.stereotype.Component;

import tp.appliSpring.core.dto.CompteL0;
import tp.appliSpring.core.entity.Compte;

@Component
public class DtoConverter {
	
	
	public CompteL0 compteToCompteL0(Compte compte) {
		return new CompteL0(compte.getNumero(),compte.getLabel(),compte.getSolde());
	}
	
	

}
