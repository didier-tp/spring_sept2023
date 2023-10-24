package tp.appliSpring.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Compte.findWithSoldeMini" , query = "SELECT c FROM Compte c WHERE c.solde > ?1")
public class Compte {

	 @Id //pour préciser identifiant / clef primaire
	 @GeneratedValue(strategy = GenerationType.IDENTITY) //pour auto_incr
	 private Long numero;
	
	 private String label;
	 
	 private Double solde;
	 
	 

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}

	public Compte() {
		super();
	}
	

	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
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

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}
	 
	 
}
