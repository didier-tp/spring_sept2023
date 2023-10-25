package tp.appliSpring.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;

@Entity
public class Operation {

	 @Id //pour préciser identifiant / clef primaire
	 @GeneratedValue(strategy = GenerationType.IDENTITY) //pour auto_incr
	 private Long numero;
	
	 private String label;// "achat Xy"
	 
	 private Double montant;  //ex: -5 si achat , +2000 si reception salaire
	 
	 @ManyToOne()
	 @JoinColumn(name = "numCompte") //nom de la colonne "foreignKey" qui référence le compte lié à l'opération
	 private Compte compte; //avec get/set
	 

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", montant=" + montant + "]";
	}

	public Operation() {
		super();
	}
	

	public Operation(Long numero, String label, Double montant) {
		super();
		this.numero = numero;
		this.label = label;
		this.montant = montant;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	
	 
	 
}
