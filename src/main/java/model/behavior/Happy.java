package model.behavior;

import model.util.Constants;

public class Happy extends Behavior {

	@Override
	public String getPicture() {
		return "/images/happy.jpg";
	}
	
	@Override
	public String express() {
		return Constants.EXPRESS_HAPPINESS;
	}

}
