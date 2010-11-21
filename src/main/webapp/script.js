// JavaScript Document
function initialize() 
{
    var latlng = new google.maps.LatLng(39.74504, -104.99311);
    var myOptions = {
      zoom: 10,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_canvas"),
        myOptions);
}

function addElement() {
  var ni = document.getElementById('scrollDown');
  var numi = document.getElementById('scrollDown');
  var num = (document.getElementById('scrollDown').value -1)+ 2;
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my'+num+'Div';
  newdiv.setAttribute('id',divIdName);
  newdiv.setAttribute('color', 'FFFAF0' );
  newdiv.innerHTML = 'Element Number '+num+' has been added! <a href=\'#\' onclick=\'removeElement('+divIdName+')\'>Remove the div "'+divIdName+'"</a>';
  ni.appendChild(newdiv);
}

function removeElement(divNum) {
  var d = document.getElementById('myDiv');
  var olddiv = document.getElementById(divNum);
  d.removeChild(olddiv);
}

function populate()
{	

	var amount = document.getElementById('scrollDown');
	while ( amount.hasAttributes() )
	{
		removeElement();
	}
	
	for ( i = 0; i < 100; i++ )
	{
		addElement();
	}
}