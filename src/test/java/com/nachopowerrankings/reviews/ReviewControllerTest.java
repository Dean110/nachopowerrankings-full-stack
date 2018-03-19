package com.nachopowerrankings.reviews;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class ReviewControllerTest {
	@InjectMocks
	private ReviewController underTest;
	@Mock
	private CategoryRepository categoryRepo;
	@Mock
	private ReviewRepository reviewRepo;
	@Mock
	private CommentRepository commentRepo;
	@Mock
	private Category category1; // = new Category(NAME, SUMMARY);
	@Mock
	private Category category2;
	@Mock
	private Review review1;
	@Mock
	private Comment comment1;
	@Mock
	private Model model;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddASingleCategoryToModel() {
		Long arbritaryCategoryId = 42L;
		when(categoryRepo.findOne(arbritaryCategoryId)).thenReturn(category1);
		underTest.showCategory(arbritaryCategoryId, model);
		verify(model).addAttribute("selectedCategory", category1);
	}

	@Test
	public void shouldReturnSingleCategoryView() {
		String templateName = underTest.showCategory(42L, model);
		assertThat(templateName, is("single-category-view"));
	}

	@Test
	public void shouldAddAllCategoriesToModel() {
		Collection<Category> allCategories = Arrays.asList(category1, category2);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		underTest.showAllCategories(model);
		verify(model).addAttribute("categories", allCategories);
	}

	@Test
	public void shouldReturnAllCategoriesView() {
		String templateName = underTest.showAllCategories(model);
		assertThat(templateName, is("all-categories-view"));
	}

	@Test
	public void shouldAddASingleReviewToModel() {
		Long arbritaryReviewId = 42L;
		when(reviewRepo.findOne(arbritaryReviewId)).thenReturn(review1);
		underTest.showReview(arbritaryReviewId, model);
		verify(model).addAttribute("selectedReview", review1);
	}

	@Test
	public void shouldReturnSingleReviewView() {
		String templateName = underTest.showReview(42L, model);
		assertThat(templateName, is("single-review-view"));

	}

	// @Test
	// public void shouldAddCommentToRepo() {
	// String testAuthor = "Raphael";
	// Long testDate = 101L;
	// String testContent = "Test";
	// when(commentRepo.save(comment1)).thenReturn(comment1);
	// String result = underTest.addComment(testAuthor, review1, testDate,
	// testContent, model);
	// assertThat(result, is("redirect:single-review-view"));
	// verify(model)result.
	// }
}
