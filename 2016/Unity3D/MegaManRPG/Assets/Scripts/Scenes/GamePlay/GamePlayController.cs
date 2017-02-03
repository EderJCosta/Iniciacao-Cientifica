using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

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
  ANIMATION_ATTACK,
  ENEMY_TURN,
  WAITING
}
public class GamePlayController : MonoBehaviour {

    public GAME_STATE currentGameState;
    private GAME_STATE nextGameState;

    public BATTLE_STATE currentBattleState;
    private BATTLE_STATE nextBattleState;

    //General
    public PlayerBehaviour player;
    private AttackBase selectedAttack;
    public EnemyBehaviour selectedEnemy;
    private AttackBase selectedAttackForHit;
    private CharBase target;
    private CharBase attacker;
    public float minTimaToAnimationAttack;
    private float currentMinTimeToAnimationAttack;
    public float timeToWaitNextTurn;
    private float currentTimeToWaitNextTurn;
    public LevelController levelController;
    //UI
    public GameObject preBattleUI;
    public GameObject battleUI;
    public GameObject initialOptionsUI;
    public GameObject attackSelectionUI;
    public GameObject winUI;
    public GameObject loseUI;
    public Slider progressSlider;
    public Image iconAttackEnemy;
    public Image iconAttackPlayer;
    public Text levelValue;
    public Text expValue;


    //Attack INFO
    public Image icon;
    public Text damageValue;
    public Text chargesValue;
    public Text typeAttack;
    public Dropdown AttackList;


    // Use this for initialization
    void Start () {
        changeGameState(GAME_STATE.PRE_BATTLE);
        changeBattleState(BATTLE_STATE.WAITING);
        AttackList.options = player.getAttackNames();
        hideInterface();
        levelValue.text = ApplicationController.getCurrentPlayerLevel().ToString();
        expValue.text = ApplicationController.getExperience().ToString();
    }

    private void hideInterface()
    {
        iconAttackEnemy.enabled = false;
        iconAttackPlayer.enabled = false;
        preBattleUI.SetActive(true);
        battleUI.SetActive(false);
        winUI.SetActive(false);
        loseUI.SetActive(false);
        progressSlider.gameObject.SetActive(false);
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
            case GAME_STATE.WIN:
                break;
            case GAME_STATE.LOSE:
                break;
        }
    }
    private void battleStateMachine()
    {
        currentBattleState = nextBattleState;
        switch (currentBattleState)
        {
            
            case BATTLE_STATE.WAITING:
                bool isPlayerTurn = target == player;
                currentTimeToWaitNextTurn += Time.deltaTime;
                if (currentTimeToWaitNextTurn < timeToWaitNextTurn) return;
                if (!target.isDead)
                {
                    if (target == player)
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

                    if (target == player)
                    {
                        changeGameState(GAME_STATE.LOSE);
                    }
                    else
                    {
                        changeGameState(GAME_STATE.WIN);
                    }
                }
               


                currentTimeToWaitNextTurn = 0;
                break;
            case BATTLE_STATE.YOUR_TURN:
                break;
            case BATTLE_STATE.ANIMATION_ATTACK:
                currentMinTimeToAnimationAttack += Time.deltaTime;

                if (currentMinTimeToAnimationAttack < minTimaToAnimationAttack) return;
                if (!attacker.charAnimator.GetCurrentAnimatorStateInfo(0).IsName("Attack"))
                {
                    selectedAttackForHit.hit(target);
                    currentMinTimeToAnimationAttack = 0;
                    iconAttackEnemy.enabled = false;
                    iconAttackPlayer.enabled = false;
                    changeBattleState(BATTLE_STATE.WAITING);
                }
               
                break;
            case BATTLE_STATE.ENEMY_TURN:
                prepareAttack();
                break;
        }
    }
    public void backToMap()
    {
        SceneManager.LoadScene("Map");
    }
    public void startBattle()
    {
        hideInterface();
        changeGameState(GAME_STATE.BATTLE);
    }
    public void updateProgressUI()
    {
        progressSlider.maxValue = levelController.getCurrentLevelSetup(ApplicationController.getLevel()).enemiesToDefeat;
        progressSlider.value = ApplicationController.getProgressLevel();

    }
    public void changeGameState(GAME_STATE newState)
    {
        nextGameState = newState;
        switch (nextGameState)
        {
            case GAME_STATE.PRE_BATTLE:
                break;
            case GAME_STATE.BATTLE:
                changeBattleState(BATTLE_STATE.YOUR_TURN);
                preBattleUI.SetActive(false);
                battleUI.SetActive(true);
                break;
            case GAME_STATE.WIN:
                battleUI.SetActive(false);
                winUI.SetActive(true);
                progressSlider.gameObject.SetActive(true);
                ApplicationController.setProgressLevel(ApplicationController.getProgressLevel() + 1);
                updateProgressUI();
                break;
            case GAME_STATE.LOSE:
                battleUI.SetActive(false);
                loseUI.SetActive(true);
                progressSlider.gameObject.SetActive(true);
                ApplicationController.setProgressLevel(0);
                updateProgressUI();
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
                initialOptionsUI.SetActive(true);
                break;
            case BATTLE_STATE.ANIMATION_ATTACK:
                attacker.charAnimator.SetTrigger("Attack");
                attacker.AttackAnimator.SetTrigger("Attack");

                break;
            case BATTLE_STATE.ENEMY_TURN:
                break;
        }
    }

    //BATTLE ACTIONS
    public void openAttackSelection()
    {
        hideAllBattleUI();
        attackSelectionUI.SetActive(true); 
        selectAttack();
    }
    public void selectAttack()
    {
        selectedAttack = player.attacks[AttackList.value];
        updateAttackInfo();
        
    }
    private void updateAttackInfo() {
        damageValue.text = selectedAttack.damage.ToString();
        chargesValue.text = selectedAttack.currentCharge.ToString();
        icon.sprite = selectedAttack.icon;
        typeAttack.text = selectedAttack.getNameTypeAttack(selectedAttack.typeAttack);
    }

    public void openItemsSelection()
    {
        hideAllBattleUI();
    }

    public void openInitialOptions()
    {
        hideAllBattleUI();
        initialOptionsUI.SetActive(true);
    }

    private void hideAllBattleUI()
    {
        initialOptionsUI.SetActive(false);
        attackSelectionUI.SetActive(false);
    }


    public void prepareAttack()
    {
        updateAttackInfo();
        hideAllBattleUI();
        
        switch (nextBattleState)
        {
  
            case BATTLE_STATE.YOUR_TURN:
                selectedAttackForHit = selectedAttack;
                target = selectedEnemy;
                attacker = player;
                iconAttackPlayer.enabled = true;
                iconAttackPlayer.sprite = selectedAttackForHit.icon;
                break;
          
            case BATTLE_STATE.ENEMY_TURN:
                AttackBase selectedEnemyAttack = selectedEnemy.attacks[Random.Range(0, selectedEnemy.attacks.Count)];
                selectedAttackForHit = selectedEnemyAttack;
                target = player;
                attacker = selectedEnemy;
                iconAttackEnemy.enabled = true;
                iconAttackEnemy.sprite = selectedAttackForHit.icon;
                break;
        }
        changeBattleState(BATTLE_STATE.ANIMATION_ATTACK);
    }

    public void tryRun()
    {

    }
}
