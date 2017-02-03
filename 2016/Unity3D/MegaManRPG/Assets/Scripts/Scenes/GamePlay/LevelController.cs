using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine.UI;

public class LevelController : MonoBehaviour {
    [System.Serializable]
    public class LevelSetup
    {
        public List<EnemyBehaviour> enemies;
        public int enemiesToDefeat;
        public EnemyBehaviour boss;
    }

    public List<LevelSetup> setupLevels;
    public GamePlayController gameController;

    //UI
    public Image imageEnemy;
    public Slider lifeEnemy;
    public Text nameEnemy;
    public Animator enemyAnimator;
    public Animator attackEnemyAnimator;

	// Use this for initialization
	void Start () {
        selectEnemy();
	}

    private void selectEnemy()
    {
        int indexLevel = ApplicationController.getLevel() - 1;

        indexLevel = indexLevel < 0 ? 0 : indexLevel; 
        LevelSetup currentLevelSetup = setupLevels[indexLevel];
        EnemyBehaviour enemyToInstance;
        if(ApplicationController.getProgressLevel() >= currentLevelSetup.enemiesToDefeat)
        {
            enemyToInstance = currentLevelSetup.boss;
            
        }
        else
        {
            enemyToInstance = currentLevelSetup.enemies[UnityEngine.Random.Range(0, currentLevelSetup.enemies.Count)];
        }

        GameObject newEnemy = Instantiate(enemyToInstance.gameObject) as GameObject;
        gameController.selectedEnemy = newEnemy.GetComponent<EnemyBehaviour>();


        EnemyBehaviour selectedEnemy = gameController.selectedEnemy;

        selectedEnemy.lifeSlider = lifeEnemy;
        imageEnemy.sprite = selectedEnemy.image;
        nameEnemy.text =selectedEnemy.nameEnemy;
        selectedEnemy.setupLife();

        selectedEnemy.AttackAnimator = attackEnemyAnimator;
        selectedEnemy.charAnimator = enemyAnimator;

    }

    // Update is called once per frame
    void Update () {
	
	}

    public LevelSetup getCurrentLevelSetup(int indexLevel)
    {
       return  setupLevels[indexLevel];
    }
}
