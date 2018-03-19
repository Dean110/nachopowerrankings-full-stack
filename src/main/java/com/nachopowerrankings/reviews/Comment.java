package com.nachopowerrankings.reviews;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private long id;
	private String author;
	@ManyToOne
	private Review review;
	@Lob
	private String content;

	public Comment(String author, Review review, String content) {
		this.author = author;
		this.review = review;
		this.content = content;

	}

	@SuppressWarnings("unused")
	private Comment() {
	};

	public long getId() {

		return id;
	}

	public String getAuthor() {
		return author;
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

		return id == ((Comment) obj).id;
	}

	public Review getReview() {

		return review;
	}

	public Object getContent() {

		return content;
	}

}
