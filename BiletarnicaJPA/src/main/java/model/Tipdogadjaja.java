package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipdogadjaja database table.
 * 
 */
@Entity
@NamedQuery(name="Tipdogadjaja.findAll", query="SELECT t FROM Tipdogadjaja t")
public class Tipdogadjaja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTipDogadjaja;

	private String tip;

	//bi-directional many-to-one association to Dogadjaj
	@OneToMany(mappedBy="tipdogadjaja")
	private List<Dogadjaj> dogadjajs;

	public Tipdogadjaja() {
	}

	public int getIdTipDogadjaja() {
		return this.idTipDogadjaja;
	}

	public void setIdTipDogadjaja(int idTipDogadjaja) {
		this.idTipDogadjaja = idTipDogadjaja;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public List<Dogadjaj> getDogadjajs() {
		return this.dogadjajs;
	}

	public void setDogadjajs(List<Dogadjaj> dogadjajs) {
		this.dogadjajs = dogadjajs;
	}

	public Dogadjaj addDogadjaj(Dogadjaj dogadjaj) {
		getDogadjajs().add(dogadjaj);
		dogadjaj.setTipdogadjaja(this);

		return dogadjaj;
	}

	public Dogadjaj removeDogadjaj(Dogadjaj dogadjaj) {
		getDogadjajs().remove(dogadjaj);
		dogadjaj.setTipdogadjaja(null);

		return dogadjaj;
	}

}