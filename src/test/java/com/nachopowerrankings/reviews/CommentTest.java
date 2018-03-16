package com.nachopowerrankings.reviews;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CommentTest {
	@Test
	public void shouldHaveAuthorReviewTimeAndContent() {
		Review review = new Review(null, null, null, null, null);
		Comment underTest = new Comment("Ben", review, 101L, "This is a test comment.");
		assertThat(underTest.getAuthor(), is("Ben"));
		assertThat(underTest.getReview(), is(review));
		assertThat(underTest.getTime(), is(101L));
		assertThat(underTest.getContent(), is("This is a test comment."));
	}

}
