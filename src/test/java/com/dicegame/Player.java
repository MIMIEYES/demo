package com.dicegame;

import org.apache.commons.lang.math.RandomUtils;

/**
 * Created by Pierreluo on 2017/6/27.
 */
public class Player {
    private Dice[] dices;
    private DiceGame diceGame;

    public Player(int diceCount, int diceCapacity, int winValue) {
        init(diceCount, diceCapacity, winValue);
    }

    private void init(int diceCount, int diceCapacity, int winValue) {
        dices = new Dice[diceCount];
        for(int i = 0; i < diceCount; i++) {
            dices[i] = new Dice(diceCapacity);
        }
        diceGame = new DiceGame(winValue);
    }

    public boolean play() {
        for(Dice dice : dices) {
            dice.setCurrentValueIndex(RandomUtils.nextInt(6));
        }
        return diceGame.result(dices);
    }

}
