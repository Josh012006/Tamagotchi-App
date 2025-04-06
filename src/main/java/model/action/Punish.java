package model.action;

import model.Tamagotchi;
import model.emotion.EmotionType;

public class Punish implements TamagotchiAction {

	 @Override
	    public void apply(Tamagotchi tamagotchi) {
	        tamagotchi.increaseEmotionValue(EmotionType.JOY, -4);
	        tamagotchi.increaseEmotionValue(EmotionType.SADNESS, 3);
	        tamagotchi.increaseEmotionValue(EmotionType.ANGER, 3);
	        tamagotchi.increaseEmotionValue(EmotionType.FEAR, 5);
	    }

}
