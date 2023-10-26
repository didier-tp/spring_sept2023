package tp.appliSpring.core.dto;

//DTO (Data Transfert Object) de niveau 0 sans détails , sans liaison avec d'autres objet

/*
//@Getter @Setter de lombok utilisable depuis 12ans
public class CompteL0 {
	 private Long numero;
	 private String label;
	 private Double solde;
	 
}
*/



public record CompteL0(Long numero,String label,Double solde) implements CompteDto {
	//constructeur prédéfini
	//.toString() prédéfini
	//pas de .set() car record sont immutable
	//pas de .getLabel() mais .label() !!!
}