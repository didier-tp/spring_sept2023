package tp.appliSpring.core.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="COMPTE")
@NamedQuery(name = "Compte.findWithOperations" , 
           query="SELECT cpt FROM Compte cpt LEFT JOIN FETCH cpt.operations WHERE cpt.numero = ?1")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="numero")
    private Long numero;
    
    @Column(name="label" , length = 64)
    private String label;
    
    private Double solde;
    
    @OneToMany(mappedBy = "compte" , fetch = FetchType.LAZY )
    //@JsonIgnore
    private List<Operation> operations = new ArrayList<>(); //+get/set
    
  //+get/set , constructeur , toString()
    
	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}


	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}


	public Compte() {
		super();
	}
	


    //+get/set , constructeur , toString()


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