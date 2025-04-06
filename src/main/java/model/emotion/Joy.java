package model.emotion;

import model.behavior.Happy;

public class Joy extends Emotion {
	
	public Joy() {
		this.outwardBehavior = new Happy();
	}

}
