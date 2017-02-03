using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class GameSettingsController : MonoBehaviour {

    public Dropdown dropdownDifficulty;
    public  Dropdown dropdownDeck;
    
    public  List<DeckBase> deckList;


	// Use this for initialization
	void  Start () {
        List<Dropdown.OptionData> names = new List<Dropdown.OptionData>();
        foreach (DeckBase deck in deckList)
        {
            Dropdown.OptionData option = new Dropdown.OptionData();
            option.text = deck.nameDeck;
            names.Add(option);
        }
        dropdownDeck.options = names;
	}

    public void goToMainMenu()
    {
        SceneManager.LoadScene("MainMenu");
    }
	// Update is called once per frame
	void Update () {
		
	}


    public void playGame()
    {
       string teste = dropdownDeck.captionText.text;
       foreach (DeckBase deck in deckList)
        {
            if (teste == deck.nameDeck)
            {                
                PlayerPrefs.SetInt("difficulty", dropdownDifficulty.value);
                PlayerPrefs.SetString("deck", deck.nameDeck);
                PlayerPrefs.SetInt("count", deck.cardList.Count);
                for(int i = 0; i < deck.cardList.Count; i++)
                {
                    PlayerPrefs.SetInt("year" + i, deck.cardList[i].year);
                    PlayerPrefs.SetString("description" + i, deck.cardList[i].description);
                }

            }
        }
        SceneManager.LoadScene("GamePlay");
    }
}
