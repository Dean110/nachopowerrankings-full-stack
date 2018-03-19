package com.nachopowerrankings.reviews;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CategoryTest {
	private static final String SUMMARY = "A category of Testing";

	@Test
	public void shouldHaveNameAndSummary() {
		Category underTest = new Category("Testing", SUMMARY);

		assertThat(underTest.getName(), is("Testing"));
		assertThat(underTest.getSummary(), is(SUMMARY));
	}

}
