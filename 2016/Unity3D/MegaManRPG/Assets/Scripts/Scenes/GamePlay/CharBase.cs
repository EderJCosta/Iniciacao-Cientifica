using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using System.Collections.Generic;

public abstract class CharBase : MonoBehaviour {

    public int totalLife;
    public int attackBase;
    public int defenseBase;
    public bool isDead;
    private int currentLife;
    public Sprite image;
    public List<AttackBase> attacks;
    public Animator charAnimator;
    public Animator AttackAnimator;
    //UI
    public Slider lifeSlider;


	// Use this for initialization
	protected void Start () {
       

        //para poder rodar o metodo start no AttackBase
        List<AttackBase> instantiatedAttacks = new List<AttackBase>();
        foreach(AttackBase attack in attacks)
        {
            GameObject newAttack = Instantiate(attack.gameObject) as GameObject;
            newAttack.GetComponent<AttackBase>().damage += attackBase;
            instantiatedAttacks.Add(newAttack.GetComponent<AttackBase>());
        }
        attacks = instantiatedAttacks;
	}

    public void setupLife()
    {
        currentLife = totalLife;
        updateLifeUI();
    }

    private void updateLifeUI()
    {
        lifeSlider.maxValue = totalLife;
        lifeSlider.value = currentLife;
    }
	
	// Update is called once per frame
	void Update () {
	
	}

    public void applyDamage(int damage)
    {
        int realDamage = damage - defenseBase;
        currentLife -= realDamage > 0 ? realDamage: 0;
        if (currentLife <= 0)
        {
            die();
        }
        updateLifeUI();
    }
    private void die()
    {
        isDead = true;
        onDie();
    }
    protected abstract void onDie();
}
