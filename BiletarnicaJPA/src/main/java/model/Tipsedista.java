package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipsedista database table.
 * 
 */
@Entity
@NamedQuery(name="Tipsedista.findAll", query="SELECT t FROM Tipsedista t")
public class Tipsedista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTipSedista;

	private int cena;

	private String kategorija;

	private int raspolozivost;

	//bi-directional many-to-one association to Sediste
	@OneToMany(mappedBy="tipsedista")
	private List<Sediste> sedistes;

	//bi-directional many-to-one association to Dogadjaj
	@ManyToOne
	private Dogadjaj dogadjaj;

	public Tipsedista() {
	}

	public int getIdTipSedista() {
		return this.idTipSedista;
	}

	public void setIdTipSedista(int idTipSedista) {
		this.idTipSedista = idTipSedista;
	}

	public int getCena() {
		return this.cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public String getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public int getRaspolozivost() {
		return this.raspolozivost;
	}

	public void setRaspolozivost(int raspolozivost) {
		this.raspolozivost = raspolozivost;
	}

	public List<Sediste> getSedistes() {
		return this.sedistes;
	}

	public void setSedistes(List<Sediste> sedistes) {
		this.sedistes = sedistes;
	}

	public Sediste addSediste(Sediste sediste) {
		getSedistes().add(sediste);
		sediste.setTipsedista(this);

		return sediste;
	}

	public Sediste removeSediste(Sediste sediste) {
		getSedistes().remove(sediste);
		sediste.setTipsedista(null);

		return sediste;
	}

	public Dogadjaj getDogadjaj() {
		return this.dogadjaj;
	}

	public void setDogadjaj(Dogadjaj dogadjaj) {
		this.dogadjaj = dogadjaj;
	}

}