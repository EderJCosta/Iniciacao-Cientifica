using UnityEngine;
using System.Collections;

public class ApplicationController : MonoBehaviour {

    private static int currentLevel;
    private static float baseExp = 300;
    private static float expMultiplier = 1.5f;

    public static void setLevel(int level)
    {
        currentLevel = level;
    }
    public static int getLevel()
    {
        return currentLevel;
    }

    public static int getProgressLevel()
    {
        return PlayerPrefs.GetInt("progressLevel" + currentLevel);
    }

    public static int getExperience()
    {
        return PlayerPrefs.GetInt("experiencePlayer");
    }

    public static void setExperience(int expToAdd)
    {
         int newExperience = getExperience() + expToAdd;
        int nextExpNeed = (int)(baseExp * getCurrentPlayerLevel() * expMultiplier);
        int diffExp;

        if (newExperience > nextExpNeed)
        {
          diffExp = newExperience - nextExpNeed;
          PlayerPrefs.SetInt("playerLevel", getCurrentPlayerLevel() + 1);
            setExperience(diffExp);
        }
        else
        {
            PlayerPrefs.SetInt("experiencePlayer", newExperience);
        }
    }

    public static int getCurrentPlayerLevel()
    {
        return PlayerPrefs.GetInt("playerLevel");
    }

    public static void setProgressLevel(int level)
    {
        PlayerPrefs.GetInt("progressLevel" + currentLevel, level);
    }
}
