package tp.appliSpring.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name = "Compte.findWithSoldeMini" , query = "SELECT c FROM Compte c WHERE c.solde > ?1")
@NamedQuery(name = "Compte.findByIdWithOperations" , 
            query = "SELECT c FROM Compte c LEFT JOIN FETCH c.operations WHERE c.numero = ?1")
//le mot clef FETCH dans la requete correspond à une sorte de EAGER "que pour cette requete"
//restriction : EAGER ou FETCH ne fonctionne pas à deux niveaux consécutifs , si besoin ---> EntityGraph
public class Compte {

	 @Id //pour préciser identifiant / clef primaire
	 @GeneratedValue(strategy = GenerationType.IDENTITY) //pour auto_incr
	 private Long numero;
	
	 private String label;
	 
	 private Double solde;
	 
	 @OneToMany(mappedBy = "compte" , fetch = FetchType.LAZY) //mappedBy = "nomJavaRelationInverse" @ManyToOne
	 @JsonIgnore
	 private List<Operation> operations = new ArrayList<>(); //+get/set
	 

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

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	 
	
	
	 
}
