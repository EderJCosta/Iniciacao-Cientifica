using UnityEngine;
using System.Collections;
using System;

public class EnemyBehaviour : CharBase {

    public String nameEnemy;
    public int experience;
    protected override void onDie()
    {
        ApplicationController.setExperience(experience);
    }

    // Use this for initialization
    void Start () {
        base.Start();
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
