package model.emotion;

import model.behavior.Scared;

public class Fear extends Emotion {
	
	public Fear() {
		this.outwardBehavior = new Scared();
	}

}