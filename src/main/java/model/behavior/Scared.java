package model.behavior;

import model.util.Constants;

public class Scared extends Behavior {

	@Override
	public String getPicture() {
		return "/images/fear.png";
	}
	
	@Override
	public String express() {
		return Constants.EXPRESS_FEAR;
	}

}
