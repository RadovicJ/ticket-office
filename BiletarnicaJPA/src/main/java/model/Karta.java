package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the karta database table.
 * 
 */
@Entity
@NamedQuery(name="Karta.findAll", query="SELECT k FROM Karta k")
public class Karta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKarta;

	@Temporal(TemporalType.DATE)
	private Date datumProdaje;

	//bi-directional many-to-one association to Dogadjaj
	@ManyToOne
	private Dogadjaj dogadjaj;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Sediste
	@ManyToOne
	private Sediste sediste;

	public Karta() {
	}

	public int getIdKarta() {
		return this.idKarta;
	}

	public void setIdKarta(int idKarta) {
		this.idKarta = idKarta;
	}

	public Date getDatumProdaje() {
		return this.datumProdaje;
	}

	public void setDatumProdaje(Date datumProdaje) {
		this.datumProdaje = datumProdaje;
	}

	public Dogadjaj getDogadjaj() {
		return this.dogadjaj;
	}

	public void setDogadjaj(Dogadjaj dogadjaj) {
		this.dogadjaj = dogadjaj;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Sediste getSediste() {
		return this.sediste;
	}

	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}

}