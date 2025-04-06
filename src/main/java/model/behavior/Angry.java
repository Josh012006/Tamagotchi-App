package model.behavior;

import model.util.Constants;

public class Angry extends Behavior {
	@Override
	public String getPicture() {
		return "/images/angry.png";
	}
	
	@Override
	public String express() {
		return Constants.EXPRESS_ANGER;
	}
}
