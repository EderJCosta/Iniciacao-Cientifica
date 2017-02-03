using UnityEngine;
using System.Collections;
using System;
using System.Collections.Generic;
using UnityEngine.UI;

public class PlayerBehaviour : CharBase {

    

	// Use this for initialization
	void Start () {
        base.Start();
        setupLife();
    }
	
	// Update is called once per frame
	void Update () {
	
	}
    protected override void onDie()
    {
        throw new NotImplementedException();
    }

    public List<Dropdown.OptionData> getAttackNames()
    {
        List<Dropdown.OptionData> names = new List<Dropdown.OptionData>();
        foreach(AttackBase attack in attacks)
        {
            Dropdown.OptionData option = new Dropdown.OptionData();
            option.text = attack.nameAttack;
            option.image = attack.icon;
            names.Add(option);
        }
        return names;
    }
}
