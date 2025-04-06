package model.action;

import model.Tamagotchi;
import model.emotion.EmotionType;

public class Feed implements TamagotchiAction {

    @Override
    public void apply(Tamagotchi tamagotchi) {
        tamagotchi.increaseEmotionValue(EmotionType.JOY, 3);
        tamagotchi.increaseEmotionValue(EmotionType.SADNESS, -1);
        tamagotchi.increaseEmotionValue(EmotionType.ANGER, -2);
        tamagotchi.increaseEmotionValue(EmotionType.FEAR, -1);
    }

}
