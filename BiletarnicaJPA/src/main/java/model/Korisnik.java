package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	private String ime;

	private String password;

	private String prezime;

	private String username;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="korisnik")
	private List<Karta> kartas;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;

	//bi-directional many-to-one association to Venue
	@OneToMany(mappedBy="korisnik")
	private List<Venue> venues;

	public Korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Karta> getKartas() {
		return this.kartas;
	}

	public void setKartas(List<Karta> kartas) {
		this.kartas = kartas;
	}

	public Karta addKarta(Karta karta) {
		getKartas().add(karta);
		karta.setKorisnik(this);

		return karta;
	}

	public Karta removeKarta(Karta karta) {
		getKartas().remove(karta);
		karta.setKorisnik(null);

		return karta;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Venue> getVenues() {
		return this.venues;
	}

	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}

	public Venue addVenue(Venue venue) {
		getVenues().add(venue);
		venue.setKorisnik(this);

		return venue;
	}

	public Venue removeVenue(Venue venue) {
		getVenues().remove(venue);
		venue.setKorisnik(null);

		return venue;
	}

}