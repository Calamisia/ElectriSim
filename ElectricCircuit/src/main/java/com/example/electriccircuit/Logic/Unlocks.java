package com.example.electriccircuit.Logic;

public class Unlocks {
    private String achievementBitString;

    public Unlocks(){
        achievementBitString = "0000000000";
    }

    public String getAchievementBitString() {
        return achievementBitString;
    }

    public void setAchievementBitString(String achievementBitString) {
        this.achievementBitString = achievementBitString;
    }

    public boolean isAchievementUnlocked(int achievementIndex){
        if (achievementBitString.charAt(achievementIndex) == 1)
            return true;
        else return false;
    }

    public boolean isLevelUnlocked(int levelAsked){
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += achievementBitString.charAt(i);
        }
        switch (levelAsked){
            case 6 :
                if( sum >= 1)
                    return true;
                else return false;
            case 7 :
                if( sum >= 3)
                    return true;
                else return false;
            case 8 :
                if( sum >= 5)
                    return true;
                else return false;
            case 9 :
                if( sum >= 9)
                    return true;
                else return false;
        }
        return false;
    }

}

// ADD A WAY TO UNLOCK ACHIEVEMENTS (i.e. detect if they have kept the budget under half)