package tp.appliSpring.core.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import tp.appliSpring.core.dto.CompteL0;
import tp.appliSpring.core.entity.Compte;

@Component
public class DtoConverter {
	
	
	public CompteL0 compteToCompteL0(Compte compte) {
		return new CompteL0(compte.getNumero(),compte.getLabel(),compte.getSolde());
	}
	
	public <S,D> D map(S source , Class<D> targetClass) {
		D target  = null;
		if(source.getClass().getSimpleName().equals("Compte")
				&& targetClass.getSimpleName().equals("CompteL0"))
				target = (D) compteToCompteL0((Compte)source);
		/*
		try {
			target = targetClass.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException("echec GenericMapper.map",e);
		} 
		return target;
		 */
		
		return target;
	}
	
	
	public  <S,D> List<D> map(List<S> sourceList , Class<D> targetClass){
		return  sourceList.stream()
			   .map((S source)->map(source,targetClass))
			   .collect(Collectors.toList());
	}
	
	

}
