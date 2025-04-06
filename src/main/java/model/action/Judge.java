package model.action;

import model.Tamagotchi;
import model.emotion.EmotionType;

public class Judge implements TamagotchiAction {

    @Override
    public void apply(Tamagotchi tamagotchi) {
        tamagotchi.increaseEmotionValue(EmotionType.ANGER, 5);
        tamagotchi.increaseEmotionValue(EmotionType.JOY, -3);
        tamagotchi.increaseEmotionValue(EmotionType.SADNESS, 2);
        tamagotchi.increaseEmotionValue(EmotionType.FEAR, 1);
    }

}
