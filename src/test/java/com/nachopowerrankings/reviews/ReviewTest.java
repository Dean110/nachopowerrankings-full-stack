package com.nachopowerrankings.reviews;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ReviewTest {
	private static final String TLDR = "T";
	private static final String IMAGE_URL = "./testImage";

	@Test
	public void shouldReturnCategoryImgUrlTitleContentAndTldr() {
		Review underTest = new Review("Test", "Test Content", new Category("Testing", "A bunch of testing"), IMAGE_URL,
				TLDR);

		assertThat(underTest.getTitle(), is("Test"));
		assertThat(underTest.getContent(), is("Test Content"));
		assertThat(underTest.getCategory().getName(), is("Testing"));
		assertThat(underTest.getImageUrl(), is(IMAGE_URL));
		assertThat(underTest.getTldr(), is(TLDR));
	}
}