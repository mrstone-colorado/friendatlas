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

function addElement(first_name) {
  var ni = document.getElementById('scrollDown');
  var numi = document.getElementById('scrollDown');
  var num = first_name;
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my'+num+'Div';
  newdiv.setAttribute('id',divIdName);
  newdiv.setAttribute('color', 'FFFAF0' );
  newdiv.innerHTML = " <a href='/main?name=" + num + "'>" + num + "</a>";
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