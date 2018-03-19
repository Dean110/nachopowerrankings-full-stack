package com.nachopowerrankings.reviews;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class MappingTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private CategoryRepository categoryRepo;
	@Resource
	private ReviewRepository reviewRepo;
	@Resource
	private ContentTagRepository contentTagRepo;
	@Resource
	private CommentRepository commentRepo;

	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = new Category("Testing", "A new way to test");
		category = categoryRepo.save(category);
		long categoryId = category.getId();
		entityManager.flush(); // forces pending stuff to happen?
		entityManager.clear();
		category = categoryRepo.findOne(categoryId);
		assertThat(category.getName(), is("Testing"));
	}

	@Test
	public void shouldSaveAndLoadReview() {
		// Arrange
		Category category = new Category("Testing", "A new way to test");
		category = categoryRepo.save(category);
		Review review = new Review("Test Review", "Stuff About Nachos", category, "image", "TLDR");
		// Act
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		entityManager.flush();
		entityManager.clear();
		review = reviewRepo.findOne(reviewId);

		// Assert
		assertThat(review.getTitle(), is("Test Review"));

	}

	@Test
	public void shouldSaveAndLoadAContentTag() {
		// Arrange
		Category category = new Category("Testing", "A new way to test");
		category = categoryRepo.save(category);
		Review review = new Review("Test Review", "Stuff About Nachos", category, "image", "TLDR");
		review = reviewRepo.save(review);
		ContentTag underTest = new ContentTag("TestName", review);
		// Act
		underTest = contentTagRepo.save(underTest);
		long underTestId = underTest.getId();
		entityManager.flush();
		entityManager.clear();
		underTest = contentTagRepo.findOne(underTestId);
		// Assert
		assertThat(underTest.getName(), is("TestName"));
	}

	@Test

	public void shouldHaveTwoReviewsAndOneCategory() {
		Category category = new Category("Testing", "A new way to test");
		category = categoryRepo.save(category);
		Review review1 = new Review("Test Review", "Stuff About Nachos", category, "image", "TLDR");
		review1 = reviewRepo.save(review1);
		long review1Id = review1.getId();
		Review review2 = new Review("Test Review2", "Stuff About more Nachos", category, "image", "TLDRTT");
		review2 = reviewRepo.save(review2);
		long review2Id = review2.getId();
		entityManager.flush();
		entityManager.clear();

		Iterable<Review> reviews = reviewRepo.findAll();
		assertThat(reviews, containsInAnyOrder(review1, review2));

		review1 = reviewRepo.findOne(review1Id);
		review2 = reviewRepo.findOne(review2Id);
		assertThat(review1.getTitle(), is("Test Review"));
		assertThat(review2.getTitle(), is("Test Review2"));
		assertThat(review1.getCategory(), is(category));
		assertThat(review2.getCategory(), is(category));

	}

	@Test
	public void shouldHaveTwoContentTagsOnTwoReviews() {
		Category category = new Category("Testing", "A new way to test");
		category = categoryRepo.save(category);
		Review review1 = new Review("Test Review", "Stuff About Nachos", category, "image", "TLDR");
		review1 = reviewRepo.save(review1);
		long review1Id = review1.getId();
		Review review2 = new Review("Test Review2", "Stuff About more Nachos", category, "image", "TLDRTT");
		review2 = reviewRepo.save(review2);
		long review2Id = review2.getId();
		ContentTag contentTag1 = new ContentTag("TestTag1", review1, review2);
		contentTag1 = contentTagRepo.save(contentTag1);
		long contentTag1Id = contentTag1.getId();
		ContentTag contentTag2 = new ContentTag("TestTag2", review1, review2);
		contentTag2 = contentTagRepo.save(contentTag2);
		long contentTag2Id = contentTag2.getId();
		entityManager.flush();
		entityManager.clear();
		review1 = reviewRepo.findOne(review1Id);
		review2 = reviewRepo.findOne(review2Id);
		contentTag1 = contentTagRepo.findOne(contentTag1Id);
		contentTag2 = contentTagRepo.findOne(contentTag2Id);
		assertThat(review1.getContentTags(), containsInAnyOrder(contentTag1, contentTag2));
		assertThat(review2.getContentTags(), containsInAnyOrder(contentTag1, contentTag2));

	}

	@Test

	public void shouldReturnAListOfReviews() {
		Category category = new Category("Testing", "A new way to test");
		category = categoryRepo.save(category);
		Review review1 = new Review("Test Review", "Stuff About Nachos", category, "image", "TLDR");
		review1 = reviewRepo.save(review1);
		Review review2 = new Review("Test Review2", "Stuff About more Nachos", category, "image", "TLDRTT");
		review2 = reviewRepo.save(review2);
		entityManager.flush();
		entityManager.clear();

		Iterable<Review> reviews = reviewRepo.findAll();
		assertThat(reviews, containsInAnyOrder(review1, review2));
	}

	@Test
	public void shouldReturnAListOfCategories() {
		Category category = new Category("Testing", "A new way to test");
		categoryRepo.save(category);
		Category category2 = new Category("Testing1", "A new way to test1");
		categoryRepo.save(category2);

		entityManager.flush();
		entityManager.clear();
		Iterable<Category> categories = categoryRepo.findAll();
		assertThat(categories, containsInAnyOrder(category, category2));
	}

	@Test
	public void shouldReturnAListOfReviewsWithACommonCategory() {
		Category category1 = new Category("Testing", "A new way to test");
		categoryRepo.save(category1);
		Long category1Id = category1.getId();
		Category category2 = new Category("Testing1", "A new way to test1");
		categoryRepo.save(category2);
		Review review1 = new Review("Test Review", "Stuff About Nachos", category1, "image", "TLDR");
		review1 = reviewRepo.save(review1);
		Review review2 = new Review("Test Review2", "Stuff About more Nachos", category1, "image", "TLDRTT");
		review2 = reviewRepo.save(review2);
		Review review3 = new Review("Test Review3", "Stuff About Tacos", category2, "image", "TLDRTT");
		review3 = reviewRepo.save(review3);
		entityManager.flush();
		entityManager.clear();
		Iterable<Review> reviewsFromCategory1 = categoryRepo.findOne(category1Id).getReviews();
		assertThat(reviewsFromCategory1, containsInAnyOrder(review1, review2));
		assertThat(reviewsFromCategory1, not(hasItem(review3)));
	}

	@Test
	public void shouldSaveAndLoadAComment() {
		Category category = new Category("Testing", "A new way to test");
		category = categoryRepo.save(category);
		Review review = new Review("Test Review", "Stuff About Nachos", category, "image", "TLDR");
		review = reviewRepo.save(review);
		Comment testComment = new Comment("TestName", review, "");
		testComment = commentRepo.save(testComment);
		long testCommentId = testComment.getId();
		entityManager.flush();
		entityManager.clear();
		testComment = commentRepo.findOne(testCommentId);
		assertThat(testComment.getAuthor(), is("TestName"));
	}

	@Test
	public void shouldHaveTwoCommentsAndOneReview() {
		Category category = new Category("Testing", "A new way to test");
		category = categoryRepo.save(category);
		Review review1 = new Review("Test Review", "Stuff About Nachos", category, "image", "TLDR");
		review1 = reviewRepo.save(review1);
		long review1Id = review1.getId();
		Comment testComment1 = new Comment("TestName1", review1, "");
		testComment1 = commentRepo.save(testComment1);
		long testComment1Id = testComment1.getId();
		Comment testComment2 = new Comment("TestName2", review1, "");
		testComment2 = commentRepo.save(testComment2);
		long testComment2Id = testComment2.getId();
		entityManager.flush();
		entityManager.clear();

		Iterable<Comment> comments = commentRepo.findAll();
		assertThat(comments, containsInAnyOrder(testComment1, testComment2));

		testComment1 = commentRepo.findOne(testComment1Id);
		testComment2 = commentRepo.findOne(testComment2Id);
		review1 = reviewRepo.findOne(review1Id);
		assertThat(testComment1.getAuthor(), is("TestName1"));
		assertThat(testComment2.getAuthor(), is("TestName2"));
		assertThat(testComment1.getReview(), is(review1));
		assertThat(testComment2.getReview(), is(review1));
		assertThat(review1.getComments(), containsInAnyOrder(testComment1, testComment2));

	}

}
