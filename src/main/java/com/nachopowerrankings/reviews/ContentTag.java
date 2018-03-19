package com.nachopowerrankings.reviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ContentTag {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@JsonIgnore
	@ManyToMany
	private Collection<Review> reviews; // = new HashSet<>();

	public ContentTag(String name, Review... passedReviews) {
		this.name = name;
		reviews = new ArrayList<>(Arrays.asList(passedReviews));
		// for (Review review : passedReviews) {
		// reviews.add(review);
		// }

	}

	@SuppressWarnings("unused")
	private ContentTag() {
	};

	public Collection<Review> getReviews() {
		return reviews;
	}

	public long getId() {

		return id;
	}

	public String getName() {

		return name;
	}

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		return id == ((ContentTag) obj).id;
	}

}
