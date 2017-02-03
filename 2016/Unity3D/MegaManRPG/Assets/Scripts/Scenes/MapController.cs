using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;

public class MapController : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
    public void goToShop()
    {
        SceneManager.LoadScene("Shop");
    }
    public void backToMainMenu()
    {
        SceneManager.LoadScene("MainMenu");
    }
    public void goToGamePlay(int level)
    {
        ApplicationController.setLevel(level);
        SceneManager.LoadScene("GamePlay");
    }
}
