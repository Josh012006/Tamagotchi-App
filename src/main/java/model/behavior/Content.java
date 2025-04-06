package model.behavior;

import model.util.Constants;

public class Content extends Behavior {
	@Override
	public String getPicture() {
		return "/images/content.png";
	}
	
	@Override
	public String express() {
		return Constants.EXPRESS_CONTENT;
	}

}
