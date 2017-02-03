using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System;
using System.Linq;
using UnityEngine.SceneManagement;

public enum GAME_STATE
{
    PRE_BATTLE,
    BATTLE,
    WIN,
    LOSE
}
public enum BATTLE_STATE
{
    YOUR_TURN,
    ANIMATION,
    ENEMY_TURN,
    WAITING
}

public class GamePlayController : MonoBehaviour {

    //UI
    public GameObject battleUI;
    public GameObject win;
    public GameObject lose;

    public Text textDescriptionSelected;
    public Text textYearSelected;
    public Text textFieldDescriptionSelected;
    public Text textFieldYearSelected;

    public List<GameObject> fieldCardsUI;
    public List<GameObject> handCardsUI;

    public List<Image> cardsImage;
    public List<Sprite> spriteList;

    //game
    public BATTLE_STATE currentBattleState;
    public GAME_STATE currentGameState;

    private BATTLE_STATE nextBattleState;
    private GAME_STATE nextGameState;
    

    public string nameDeck;
    public int[] pontuacao;
    public int playerTurn;
    public List<DeckBase.Card> deck;
    public List<DeckBase.Card> deckUsed;
    public int difficulty; 
    public List<DeckBase.Card> fieldCards;
    public List<List<DeckBase.Card>> handCardsList;
    public DeckBase.Card cardSelected;
    public int cardPositionSelected;
    public int cardFieldPositionSelected;

    // Use this for initialization
    void Start () {
        //pegando as variaveis do gamesettings
        DeckBase.Card card;

        difficulty = PlayerPrefs.GetInt("difficulty")+1;
        nameDeck = PlayerPrefs.GetString("deck");
     
        int count = PlayerPrefs.GetInt("count");

        for(int i = 0; i < count; i++)
        {
            card = new DeckBase.Card();
            card.id = i;
            card.year = PlayerPrefs.GetInt("year" + i);
            card.description = PlayerPrefs.GetString("description" + i);
            deck.Add(card);
        }
        PlayerPrefs.DeleteAll();
        //mudando maquinas de estados
        changeGameState(GAME_STATE.BATTLE);
        changeBattleState(BATTLE_STATE.YOUR_TURN);

        drawInitialCards();
        //iniciando pontuação e vez
        pontuacao = new int[(1 + difficulty)];
        playerTurn = 0;
        for (int i = 0; i < pontuacao.Count(); i++)
        {
            pontuacao[i] = 4;
        }
        
    }

    public void changeTurn()
    {
        if(playerTurn == 0)
        {
            playerTurn = 1;
        }
        changeBattleState(BATTLE_STATE.ANIMATION);
    }

    public void setCardField()
    {
        System.Random r = new System.Random();
        int randomNumber;
        if(fieldCards.Count == 0)
        {
            randomNumber = r.Next(0, deck.Count());
            fieldCards.Add(deck[randomNumber]);
            deck.RemoveAt(randomNumber);
            for(int i = 0; i < fieldCards.Count(); i++)
            {
                fieldCardsUI[i].SetActive(true);
            }
            textFieldDescriptionSelected.text = fieldCards[0].description;
            textFieldYearSelected.text = "Ano: " + fieldCards[0].year;
            cardFieldPositionSelected = 0;
        }
        else
        {
            fieldCards.Add(cardSelected);
            ListComparator comparer = new ListComparator();
            fieldCards.Sort(comparer);
            
            switch (playerTurn)
            {
                case 0:

                    handCardsList[0].RemoveAt(cardPositionSelected);
                    handCardsUI[handCardsList[0].Count()].SetActive(false);
                    pontuacao[0] -= 1;
                    break;
                case 1:
                    handCardsList[1].RemoveAt(cardPositionSelected);
                    handCardsUI[4 + handCardsList[1].Count()].SetActive(false);
                    pontuacao[1] -= 1;
                    break;
                case 2:
                    handCardsList[2].RemoveAt(cardPositionSelected);
                    handCardsUI[8 + handCardsList[2].Count()].SetActive(false);
                    pontuacao[2] -= 1;
                    break;
                case 3:
                    handCardsList[3].RemoveAt(cardPositionSelected);
                    handCardsUI[12 + handCardsList[3].Count()].SetActive(false);
                    pontuacao[3] -= 1;
                    break;
            }
            cardFieldPositionSelected = fieldCards.IndexOf(cardSelected);
            if(handCardsList[0].Count() > 0)
            {
                cardSelected = handCardsList[0][0];
            }
            for (int i = 0; i < fieldCards.Count(); i++)
            {
                fieldCardsUI[i].SetActive(true);
            }
            
            
        }

        changeTurn();

    }

   

    public void getCardDeck()
    {
        if(deck.Count() > 0)
        {
            System.Random r = new System.Random();
            int randomNumber = r.Next(0, deck.Count());
            deckUsed.Add(handCardsList[playerTurn][cardPositionSelected]);
            handCardsList[playerTurn][cardPositionSelected] = deck[randomNumber];
            if(playerTurn == 0)
            {
                cardSelected = deck[randomNumber];

            }
            deck.RemoveAt(randomNumber);
            changeTurn();
        }else
        {
            deck = deckUsed;
            deckUsed = new List<DeckBase.Card>();
            getCardDeck();
        }
        

    }

    public void playCard(int i)
    {
        if(i == 1)
        {
            if(cardFieldPositionSelected != (fieldCards.Count() - 1))
            {
                if (cardSelected.year > fieldCards[cardFieldPositionSelected].year && cardSelected.year < fieldCards[cardFieldPositionSelected +1].year)
                {
                    setCardField();
                }
                else
                {
                    getCardDeck();
                }
            }
            else
            {
                if (cardSelected.year > fieldCards[cardFieldPositionSelected].year)
                {
                    setCardField();
                }
                else
                {
                    getCardDeck();
                }
            }
        }
        else
        {
            if(cardFieldPositionSelected != 0)
            {
                if (cardSelected.year < fieldCards[cardFieldPositionSelected].year && cardSelected.year > fieldCards[cardFieldPositionSelected -1].year)
                {
                    setCardField();
                }
                else
                {
                    getCardDeck();
                }
            }
            else
            {
                if (cardSelected.year < fieldCards[cardFieldPositionSelected].year)
                {
                    setCardField();
                }
                else
                {
                    getCardDeck();
                }
            }
           
        }
        loadCardsImage();
        if(handCardsList[0].Count() > 0)
        {
            selectCard(0);
        }
        selectFieldCard(cardFieldPositionSelected);
    }

    public void drawInitialCards()
    {
        // iniciando hands
        System.Random r = new System.Random();
        int drawLetter = r.Next(0, deck.Count);

        setCardField();

        handCardsList = new List<List<DeckBase.Card>>();
        for (int i = 0; i < difficulty+1; i++)
        {
            List<DeckBase.Card> handCards = new List<DeckBase.Card>();
            for (int j = 0; j < 4; j++)
            {
                drawLetter = r.Next(0, deck.Count);
                handCards.Add(deck[drawLetter]);
                deck.RemoveAt(drawLetter);
            }
            handCardsList.Add(handCards);

        }
        cardSelected = handCardsList[0][0];
        cardFieldPositionSelected = 0;

        loadCardsImage();
        selectCard(0);
        selectFieldCard(cardFieldPositionSelected);
    }


    public void loadCardsImage()
    {
        spriteList = new List<Sprite>();
        Sprite[] teste;
        teste = Resources.LoadAll<Sprite>("ArtWorks/Deck/Commons/" + nameDeck);
        for (int i = 0; i < handCardsList[0].Count(); i++)
        {
            cardsImage[i].sprite = teste[handCardsList[0][i].id];
        }
        cardsImage[4].sprite = teste[cardSelected.id];
        cardsImage[5].sprite = teste[fieldCards[cardFieldPositionSelected].id];
    }
	
	// Update is called once per frame
	void Update () {
        gameStateMachine();
	}

    private void gameStateMachine()
    {
        currentGameState = nextGameState;
        switch (currentGameState)
        {
            case GAME_STATE.PRE_BATTLE:
                break;
            case GAME_STATE.BATTLE:
                battleStateMachine();
                break;
            case GAME_STATE.LOSE:
                break;
            case GAME_STATE.WIN:
                break;
        }
    }
    private void battleStateMachine()
    {
        currentBattleState = nextBattleState;
        switch (currentBattleState)
        {
            case BATTLE_STATE.ANIMATION:
                changeBattleState(BATTLE_STATE.WAITING);
                break;
            case BATTLE_STATE.YOUR_TURN:
                break;
            case BATTLE_STATE.ENEMY_TURN:
                for(playerTurn = 1; playerTurn <= difficulty; playerTurn++)
                {
                    System.Random random = new System.Random();
                    int randomNumber = random.Next(0, handCardsList[playerTurn].Count());
                    cardSelected = handCardsList[playerTurn][randomNumber];
                    cardPositionSelected = randomNumber;


                    int diference = int.MaxValue;
                    for(int j = 0; j < fieldCards.Count(); j++)
                    {
                        if (Math.Abs(cardSelected.year - fieldCards[j].year) < diference)
                        {
                            cardFieldPositionSelected = j;
                            diference = cardSelected.year - fieldCards[j].year;
                        }
                    }

                    randomNumber = fieldCards[cardFieldPositionSelected].year;
                    while(randomNumber == fieldCards[cardFieldPositionSelected].year)
                    {
                        randomNumber = random.Next(fieldCards[cardFieldPositionSelected].year - 1, fieldCards[cardFieldPositionSelected].year + 2);
                    }
                    
                    if (randomNumber < fieldCards[cardFieldPositionSelected].year)
                    {
                        playCard(-1);
                    }
                    else
                    {
                        playCard(1);
                    }
                    
                }
                playerTurn = 0;
                break;
            case BATTLE_STATE.WAITING:
                Boolean victory = false;
                for(int i = 0;i < pontuacao.Count(); i++)
                {
                    if(pontuacao[i] == 0)
                    {
                        victory = true;
                    }
                }
                if(victory == false)
                {
                    if (playerTurn == 0)
                    {
                        changeBattleState(BATTLE_STATE.YOUR_TURN);
                    }
                    else
                    {
                        changeBattleState(BATTLE_STATE.ENEMY_TURN);
                    }
                }
                else
                {
                    battleUI.SetActive(false);
                    if (pontuacao[0] == 0)
                    {
                        win.SetActive(true);
                    }
                    else
                    {
                        lose.SetActive(true);
                    }

                }
                break;
        }
    }

    public void changeGameState(GAME_STATE newState)
    {
        nextGameState = newState;
        switch (nextGameState)
        {
            case GAME_STATE.PRE_BATTLE:
                break;
            case GAME_STATE.BATTLE:
                break;
            case GAME_STATE.LOSE:
                break;
            case GAME_STATE.WIN:
                break;
        }
    }
    public void changeBattleState(BATTLE_STATE newState)
    {
        nextBattleState = newState;
        switch (nextBattleState)
        {
            case BATTLE_STATE.WAITING:
                break;
            case BATTLE_STATE.YOUR_TURN:
                break;
            case BATTLE_STATE.ANIMATION:
                break;
            case BATTLE_STATE.ENEMY_TURN:
                break;
        }
    }

    public void selectCard(int position)
    {
        textDescriptionSelected.text = handCardsList[0][position].description;
        textYearSelected.text = "Ano: ?";
        cardSelected = handCardsList[0][position];
        cardPositionSelected = position;
        loadCardsImage();
        
    }

    public void selectFieldCard(int position)
    {
        textFieldDescriptionSelected.text = fieldCards[position].description;
        textFieldYearSelected.text = "Ano: " + fieldCards[position].year;
        //cardSelected = fieldCards[position];
        cardFieldPositionSelected = position;
        loadCardsImage();
    }

    public void goToGameSettings()
    {
        SceneManager.LoadScene("GameSettings");
    }

    public void exitMatch()
    {
        SceneManager.LoadScene("GameSettings");
    }
}

    
