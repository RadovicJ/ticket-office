package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the dogadjaj database table.
 * 
 */
@Entity
@NamedQuery(name="Dogadjaj.findAll", query="SELECT d FROM Dogadjaj d")
public class Dogadjaj implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDogadjaj;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;

	private String naziv;

	private String opis;

	//bi-directional many-to-one association to Tipdogadjaja
	@ManyToOne
	private Tipdogadjaja tipdogadjaja;

	//bi-directional many-to-one association to Venue
	@ManyToOne
	private Venue venue;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="dogadjaj")
	private List<Karta> kartas;

	//bi-directional many-to-one association to Tipsedista
	@OneToMany(mappedBy="dogadjaj")
	private List<Tipsedista> tipsedistas;

	public Dogadjaj() {
	}

	public int getIdDogadjaj() {
		return this.idDogadjaj;
	}

	public void setIdDogadjaj(int idDogadjaj) {
		this.idDogadjaj = idDogadjaj;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Tipdogadjaja getTipdogadjaja() {
		return this.tipdogadjaja;
	}

	public void setTipdogadjaja(Tipdogadjaja tipdogadjaja) {
		this.tipdogadjaja = tipdogadjaja;
	}

	public Venue getVenue() {
		return this.venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public List<Karta> getKartas() {
		return this.kartas;
	}

	public void setKartas(List<Karta> kartas) {
		this.kartas = kartas;
	}

	public Karta addKarta(Karta karta) {
		getKartas().add(karta);
		karta.setDogadjaj(this);

		return karta;
	}

	public Karta removeKarta(Karta karta) {
		getKartas().remove(karta);
		karta.setDogadjaj(null);

		return karta;
	}

	public List<Tipsedista> getTipsedistas() {
		return this.tipsedistas;
	}

	public void setTipsedistas(List<Tipsedista> tipsedistas) {
		this.tipsedistas = tipsedistas;
	}

	public Tipsedista addTipsedista(Tipsedista tipsedista) {
		getTipsedistas().add(tipsedista);
		tipsedista.setDogadjaj(this);

		return tipsedista;
	}

	public Tipsedista removeTipsedista(Tipsedista tipsedista) {
		getTipsedistas().remove(tipsedista);
		tipsedista.setDogadjaj(null);

		return tipsedista;
	}

}