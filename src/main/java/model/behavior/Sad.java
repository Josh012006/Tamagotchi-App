package model.behavior;

import model.util.Constants;

public class Sad extends Behavior {

	@Override
	public String getPicture() {
		return "/images/sad.jpg";
	}

	@Override
	public String express() {
		return Constants.EXPRESS_SADNESS;
	}
}
