package com.nachopowerrankings.reviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewsPopulator implements CommandLineRunner {

	@Resource
	private CategoryRepository categoryRepo;
	@Resource
	private ReviewRepository reviewRepo;
	@Resource
	private CommentRepository commentRepo;
	@Resource
	private ContentTagRepository contentTagRepo;

	@Override
	public void run(String... args) throws Exception {
		Category fastFood = new Category("Fast Food",
				"Fast food nachos are great for when you want to challenge your driving skills with the ultimate finger food.");
		fastFood = categoryRepo.save(fastFood);
		Category homeMade = new Category("Home Made", "Review of recipes for making nachos at home.");
		homeMade = categoryRepo.save(homeMade);
		Category restaurant = new Category("Restaurant Nachos",
				"The big leagues of nachos, when creating nachos gets serious.");
		restaurant = categoryRepo.save(restaurant);
		Review review1 = reviewRepo.save(new Review("Taco Bell Nachos Supreme",
				"This is the nickel of the nacho world, ubiquitous, yet not really necessary. However, it does provide a stable baseline that other nachos have to acheive to be considered nachos. The chips are fresh with a crunch and adequete saltiness.  The nacho cheese is the goold standard for fast food cheese sauce, creamy, hot, and smooth in a way that only processed cheese can achieve.  Toppings are minimal, but they work to add value to the dish. The taco beef is classic, sour cream and tomatoes add a coolness that helps contrast the sharpness of the cheese sauce, but the refried beans often miss the mark being too clumpy most of the time.",
				fastFood, "/images/NachosSupreme.jpg", "A baseline C, better than not having nachos."));
		Review review2 = reviewRepo.save(new Review("Taco Bell Nachos Bell Grande",
				"Taco Bell's premium nachos offering is a larger version of their Nachos Supreme. There is nothing to seperate it from it's smaller brethern. This isn't a bad thing. As we described in our review of the Nachos Supreme, the ingredients used are adequete, but nothing makes the Nachos Bell Grande stand out from the rest.",
				fastFood, "/images/NachosBellGrande.jpg", "A bigger version of the Nachos Supreme, C."));

		Review review3 = reviewRepo.save(new Review("Wikipedia Nachos",
				"This version of nachos is why you can't use wikipedia as a research source on academic papers. The chips are store bought, not a problem for home made nachos, but the arrangement is bush league and reeks of a no talent nacho clown trying to make a fancy plate for Pinterest. The cheese is frightning, it appears to have the surface tension of grade school children's slime. Slivers of onions convey a sense of true laziness in the preparer.  And finally, the coup de grâce of the entire dish are the whole olives. Adding no value of their own, the only purpose they serve is as a warning that noone should give this dish the time of day.",
				homeMade, "/images/wikiNachosSupreme.jpg", "A strict F-."));
		Review review4 = new Review("Baker's Pulled Pork Nachos",
				"Baker's delivers hugely on their nachos! With quality ingredients and a portion size that places them on the all time greats list of value for your dollar. The cheese is melted evenly over the house made chips. The pulled pork is sweet and tangy and shredded in a way that it can be broken up onto each chip. The lettuce can be an uneven cut, but it's not enough to distract you from the greatness of the dish.  Salsa is served on the side, which is smart because of the amount of good stuff going on with the other toppings.  It lets the consumer use the salsa for the random chips that don't have as many toppings as the others.",
				restaurant, "/images/bakersNachos.jpg", "Greater than the sum of its parts, a solid A.");
		reviewRepo.save(review4);
		Review review5 = new Review("Smokey Bones \'The Manchego\'",
				"Smokey Bones reaches for the brass ring with \'The Manchego\' and almost gets there. The chips are sometimes a little sketchy, with previous visits yielding chips that were stale.  However, most of the times I've had this dish they have been excellent. The combination of cheese sauce and shredded cheese to the dish provides a depth of flavor in the cheese category. Pulled pork and shredded chicken are both excellent and combine with the other fresh toppings to provide the quintessintial American nacho experience.",
				restaurant, "/images/smokeyBonesNachos.jpg", "Classic American offering, B+.");
		reviewRepo.save(review5);
		ContentTag contentTag1 = new ContentTag("Beefy", review1, review2);
		contentTagRepo.save(contentTag1);
		ContentTag contentTag2 = new ContentTag("Run for the border", review1, review2);
		contentTagRepo.save(contentTag2);
		ContentTag contentTag3 = new ContentTag("Oink Oink", review5, review4);
		contentTagRepo.save(contentTag3);
		ContentTag contentTag4 = new ContentTag("Cheesey", review5, review1, review2);
		contentTagRepo.save(contentTag4);
		contentTagRepo.save(new ContentTag("Nachos", review1, review2, review3, review4, review5));
	}

}
