$(document).ready(function(){
	$("#send").click(function(){
		WriteToUnity($("#text").val());
	});
	
	
	$("#start").click(function(){
		setCallback();
	});
	
	setCallback();
	

});

function setCallback() {
	var unity = GetUnity();
	if(unity && unity != null){
		unity.SendMessage("Main Camera", "SetCallback", "UnityCallback");
	}else{
		setTimeout(setCallback, 1000);
	}
}

//
//一定の条件が成立するまで処理を待つ関数
//
function wait(interval)
{
  setTimeout(nextWait, interval);

  function nextWait()
  {
    wait(interval);
  }
}

function sleep(val, cb) {
}

// Using the above call from Unity, this will receive
function UnityCallback( arg )
{
	var obj =  eval("("+arg+")");
	
	$("#time").html(obj.time);
	$("#pos-x").html(obj.transform.position.x);
	$("#pos-y").html(obj.transform.position.y);
	$("#pos-z").html(obj.transform.position.z);
	$("#ang-x").html(obj.transform.eulerAngles.x);
	$("#ang-y").html(obj.transform.eulerAngles.y);
	$("#ang-z").html(obj.transform.eulerAngles.z);
	$("#scl-x").html(obj.transform.localScale.x);
	$("#scl-y").html(obj.transform.localScale.y);
	$("#scl-z").html(obj.transform.localScale.z);
}

function WriteToUnity( arg ) {
	var unity = GetUnity();
	unity.SendMessage("Main Camera", "WriteMessage", arg );
}
