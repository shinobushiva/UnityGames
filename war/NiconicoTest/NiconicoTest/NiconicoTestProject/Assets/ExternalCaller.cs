using UnityEngine;
using System.Collections;

public class ExternalCaller : MonoBehaviour {
	
	private string str = null;
	private float showUntil = 0f;
	private string callback = null;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		if(callback != null) {
			string str = "{";
			str+="'transform':{";
			str+="'position':{";
			str+="'x':"+transform.position.x+",";
			str+="'y':"+transform.position.y+",";
			str+="'z':"+transform.position.z+"}";
			str+=",'eulerAngles':{";
			str+="'x':"+transform.eulerAngles.x+",";
			str+="'y':"+transform.eulerAngles.y+",";
			str+="'z':"+transform.eulerAngles.z+"}";
			str+=",'localScale':{";
			str+="'x':"+transform.localScale.x+",";
			str+="'y':"+transform.localScale.y+",";
			str+="'z':"+transform.localScale.z+"}";
			str+="},";
			str+="'time':"+Time.time;
			str+="}";
			Application.ExternalCall(callback, str);
		}
	}
	
	void OnGUI() {
		if(showUntil > Time.time){
			GUILayout.Label(str);
		}
		
		GUILayout.Label(callback);
	}
	
	public void SetCallback(string str){
		callback = str;
	}
	
	public void WriteMessage(string str) {
		this.str = str;
		showUntil = Time.time + 5.0f;
	}
}
