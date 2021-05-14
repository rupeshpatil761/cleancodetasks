package com.epam.engx.cleancode.functions.task2;


import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Level;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.NotActivUserException;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Review;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.User;

import java.util.TreeMap;

public abstract class Account implements User {

    private TreeMap<Integer, Level> thresholdToLevel = new TreeMap<>();

    public Level getActivityLevel() {
        validateAccountForLevel();
        return getLevelByReviews(getReviewAnswers());
    }

    private void validateAccountForLevel() {
        if (isAccountRegistered() || getVisitNumber() <= 0) {
            throw new NotActivUserException();
        }
    }

    private boolean isAccountRegistered(){
        return !isRegistered() ? true : false;
    }

    private int getReviewAnswers(){
        int reviewAnswers = 0;
        for (Review r : getAllReviews()) {
          reviewAnswers += r.getAnswers().size();
        }
        return reviewAnswers;
    }

    private Level getLevelByReviews(int reviewAnswers) {
        for (Integer threshold : thresholdToLevel.keySet()) {
            if (reviewAnswers >= threshold) {
                return thresholdToLevel.get(threshold);
            }
        }
        return Level.defaultLevel();
    }

    public void setThresholdToLevel(TreeMap<Integer, Level> thresholdToLevel) {
        this.thresholdToLevel = thresholdToLevel;
    }
}
