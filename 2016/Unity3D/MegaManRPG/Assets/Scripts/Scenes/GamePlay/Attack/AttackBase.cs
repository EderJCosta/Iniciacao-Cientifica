using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using System.Collections.Generic;

public enum TYPE_ATTACK
{
    NORMAL,
    FIRE,
    WATER,
    EARTH,
    AIR,
    POISON,
    ICE
}
public abstract class AttackBase : MonoBehaviour {

    public int damage;
    public TYPE_ATTACK typeAttack;
    public int accuracy;
    public int totalCharges;
    public int currentCharge;
    public Sprite icon;
    public string nameAttack;
    public Dictionary<TYPE_ATTACK, string> nameTypeAttacks;


    protected void Start()
    {
        currentCharge = totalCharges;
        
    }
    public void hit(CharBase target)
    {
        int randNumb = Random.Range(0, 100);
        if(currentCharge > 0 && randNumb < accuracy)
        {
            currentCharge--;
            target.applyDamage(damage);
            onHit();
        }
    }
    protected abstract void onHit();

    public string getNameTypeAttack(TYPE_ATTACK typeAttack)
    {
        nameTypeAttacks = new Dictionary<TYPE_ATTACK, string>();

        nameTypeAttacks.Add(TYPE_ATTACK.NORMAL, "Normal");
        nameTypeAttacks.Add(TYPE_ATTACK.AIR, "Air");
        nameTypeAttacks.Add(TYPE_ATTACK.EARTH, "Earth");
        nameTypeAttacks.Add(TYPE_ATTACK.FIRE, "Fire");
        nameTypeAttacks.Add(TYPE_ATTACK.ICE, "Ice");
        nameTypeAttacks.Add(TYPE_ATTACK.POISON, "Poison");
        nameTypeAttacks.Add(TYPE_ATTACK.WATER, "Water");

        return nameTypeAttacks[typeAttack];
    }
}
