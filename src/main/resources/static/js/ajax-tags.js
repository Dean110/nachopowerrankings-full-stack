const xhr = new XMLHttpRequest()

xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {

		const res = xhr.responseText;

		const tagList = document.getElementById('AJAXcontainer');

		tagList.innerHTML = res;

	}
}

// need this const form for creating tag method - don't delete
const form = document.getElementById('add-content-tag-form');

// exemption message if newTag box empty on submit
// form.addEventListener('submit', function() {
// })

// creating ajax call to create new tag by method created in Controller
form.addEventListener('submit', function(event) {
	
	event.preventDefault();
	
	const tagInputBox = document.getElementById('add-content-tag-name-input').value;
	
	if (tagInputBox == "") {
		alert("Empty tag name, try again!")
		return false;
	}

	const reviewId = document.getElementById('add-content-tag-reviewId').value;
	const contentTagName = document.getElementById('add-content-tag-name-input').value;
	
	xhr.open('POST', 'http://localhost:8080/add-content-tag', true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("name=" + contentTagName + "&reviewId=" + reviewId);
})

document.querySelectorAll('.content-tag-remove-button').forEach(function(button){
	button.addEventListener("click", function(event){
		event.preventDefault();
		const tagToBeRemovedId = button.parentElement.querySelector('.content-tag-remove-tag-id').value;
		// console.log(tagToBeRemovedId);
		const reviewId = button.parentElement.querySelector('.content-tag-remove-review-id').value;
		// console.log(reviewId);
		xhr.open('POST', 'http://localhost:8080/remove-content-tag', true);	
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.send("contentTagId=" + tagToBeRemovedId + "&reviewId="+ reviewId);
	});
});
// creating ajax call to delete tag by method used created in Controller
// const removeForm = document.getElementById('deleteTagForm');
// removeForm.addEventListener('submit', function(event) {
// 	event.preventDefault();
// 	const removeTagId = document.getElementById('removeTagId').value;
// 	const removeTagDescriptionId = document.getElementById('removeTagDescriptionId').value;
	
// 	xhr.open('POST', 'http://localhost:8080/remove-tag', true);
// 	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
// 	xhr.send("reviewId=" + removeTagId + "&tagDescription="	+ removeTagDescriptionId);

// })

// const commentBox = document.getElementById('newCommentForm');
// commentBox.addEventListener('submit', function(event) {
	
	
// 	const commentDetailsInput = document.getElementById('leaveCommentBox').value;
// 	const userHandleInput = document.getElementById('userHandle').value;
	
// 	if (commentDetailsInput == "" || userHandleInput == "") {
// 		event.preventDefault();
// 		alert("Please enter a User Handle and Leave Comment to submit a Comment");
// 		return false;
// 	}

// })
