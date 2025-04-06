package model.emotion;

import model.behavior.Sad;

public class Sadness extends Emotion {
	
	public Sadness() {
		this.outwardBehavior = new Sad();
	}

}
