package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the venue database table.
 * 
 */
@Entity
@NamedQuery(name="Venue.findAll", query="SELECT v FROM Venue v")
public class Venue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVenue;

	private String naziv;

	//bi-directional many-to-one association to Dogadjaj
	@OneToMany(mappedBy="venue")
	private List<Dogadjaj> dogadjajs;

	//bi-directional many-to-one association to Grad
	@ManyToOne
	private Grad grad;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	public Venue() {
	}

	public int getIdVenue() {
		return this.idVenue;
	}

	public void setIdVenue(int idVenue) {
		this.idVenue = idVenue;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Dogadjaj> getDogadjajs() {
		return this.dogadjajs;
	}

	public void setDogadjajs(List<Dogadjaj> dogadjajs) {
		this.dogadjajs = dogadjajs;
	}

	public Dogadjaj addDogadjaj(Dogadjaj dogadjaj) {
		getDogadjajs().add(dogadjaj);
		dogadjaj.setVenue(this);

		return dogadjaj;
	}

	public Dogadjaj removeDogadjaj(Dogadjaj dogadjaj) {
		getDogadjajs().remove(dogadjaj);
		dogadjaj.setVenue(null);

		return dogadjaj;
	}

	public Grad getGrad() {
		return this.grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}