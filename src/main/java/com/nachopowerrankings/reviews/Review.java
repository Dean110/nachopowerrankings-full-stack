package com.nachopowerrankings.reviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Review {
	@Id
	@GeneratedValue
	private long id;
	private String title;
	@Lob
	private String content;
	private String tldr;
	private String imageUrl;
	@ManyToOne
	private Category category;
	@ManyToMany(mappedBy = "reviews")
	private Collection<ContentTag> contentTags;
	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;

	public Collection<ContentTag> getContentTags() {
		return contentTags;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Category getCategory() {
		return category;
	}

	public String getImageUrl() {

		return imageUrl;
	}

	public String getTldr() {
		return tldr;
	}

	@SuppressWarnings("unused")
	private Review() {
	}

	public Review(String title, String content, Category category, String imageUrl, String tldr) {
		this.title = title;
		this.content = content;
		this.category = category;
		this.imageUrl = imageUrl;
		this.tldr = tldr;
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

		return id == ((Review) obj).id;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

}
