package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the sediste database table.
 * 
 */
@Entity
@NamedQuery(name="Sediste.findAll", query="SELECT s FROM Sediste s")
public class Sediste implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSediste;

	private int broj;

	private int red;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="sediste")
	private List<Karta> kartas;

	//bi-directional many-to-one association to Tipsedista
	@ManyToOne
	private Tipsedista tipsedista;

	public Sediste() {
	}

	public int getIdSediste() {
		return this.idSediste;
	}

	public void setIdSediste(int idSediste) {
		this.idSediste = idSediste;
	}

	public int getBroj() {
		return this.broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public int getRed() {
		return this.red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public List<Karta> getKartas() {
		return this.kartas;
	}

	public void setKartas(List<Karta> kartas) {
		this.kartas = kartas;
	}

	public Karta addKarta(Karta karta) {
		getKartas().add(karta);
		karta.setSediste(this);

		return karta;
	}

	public Karta removeKarta(Karta karta) {
		getKartas().remove(karta);
		karta.setSediste(null);

		return karta;
	}

	public Tipsedista getTipsedista() {
		return this.tipsedista;
	}

	public void setTipsedista(Tipsedista tipsedista) {
		this.tipsedista = tipsedista;
	}

}