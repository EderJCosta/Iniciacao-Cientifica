using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class DeckBase : MonoBehaviour{

    
    [System.Serializable]
    public class Card
    {
        public int id;
        public int year;
        public string description;
    }
    public string nameDeck;
    public List<Card> cardList;


    public void setNameDeck(string nameDeck)
    {
        this.nameDeck = nameDeck;
    }
    public void setCardList(List<Card> cardLIst)
    {
        this.cardList = cardList;
    }

    

	// Use this for initialization
	void Start () {

	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
