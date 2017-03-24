package interactions;

import places.RentedPlace;
import users.IGiveReview;
import users.IReceiveReview;
import users.User;

public class Review {
	
	private final IGiveReview giver;
	private final IReceiveReview receiver;
	private int rating;
	private String description;
	private int likes;
	
	public Review(IGiveReview giver, IReceiveReview receiver, int rating, String description) {
		this.giver = giver;
		this.receiver = receiver;
		if (rating > 0 && rating < 11) this.rating = rating;
		this.description = description;//may be empty
		this.likes = 0;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void like(){
		this.likes++;
	}
	
	public String getDescription() {
		return description;
	}
	
	public IGiveReview getGiver() {
		return giver;
	}
	
	public int getRating() {
		return rating;
	}
	
	
	
	

}
