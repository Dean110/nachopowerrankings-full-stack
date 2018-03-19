package com.nachopowerrankings.reviews;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewRestController {
	@Resource
	private ReviewRepository reviewRepo;
	@Resource
	private ContentTagRepository contentTagRepo;

	@RequestMapping("/contentTags/{id}")
	public ContentTag findOneContentTagName(@PathVariable long id) {

		return contentTagRepo.findOne(id);
	}

	@RequestMapping("/contentTags")
	public Iterable<ContentTag> findAllContentTags() {
		return contentTagRepo.findAll();
		// for (ContentTag tag : contentTags) {
		// contentTagNames += tag.getName();
		// contentTagNames += "\n";
		// }
		// return contentTagNames;
	}
}