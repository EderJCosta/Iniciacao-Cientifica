using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ListComparator : IComparer<DeckBase.Card> {
    public int Compare(DeckBase.Card x, DeckBase.Card y)
    {
        if (x == null)
            return -1;
        if (y == null)
            return 1;

        return x.year - y.year;
    }

    // Use this for initialization
    void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
