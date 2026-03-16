package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the grad database table.
 * 
 */
@Entity
@NamedQuery(name="Grad.findAll", query="SELECT g FROM Grad g")
public class Grad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGrad;

	private String ime;

	//bi-directional many-to-one association to Venue
	@OneToMany(mappedBy="grad")
	private List<Venue> venues;

	public Grad() {
	}

	public int getIdGrad() {
		return this.idGrad;
	}

	public void setIdGrad(int idGrad) {
		this.idGrad = idGrad;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public List<Venue> getVenues() {
		return this.venues;
	}

	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}

	public Venue addVenue(Venue venue) {
		getVenues().add(venue);
		venue.setGrad(this);

		return venue;
	}

	public Venue removeVenue(Venue venue) {
		getVenues().remove(venue);
		venue.setGrad(null);

		return venue;
	}

}