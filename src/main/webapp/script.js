

var geocoder;
var map;

function setAddress( stringAddress )
{
    testAddress = stringAddress;
}

function initialize() {
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng( 39.74504, -104.99311 );
    var myOptions = {
      zoom: 10,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
  }

  function codeAddress( add ) {
    var address = add;
    geocoder = new google.maps.Geocoder();
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        map.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
      } else {
        alert("Geocode was not successful for the following reason: " + status);
      }
    });

 }

function pop( add ) {
    var address = add;
    geocoder = new google.maps.Geocoder();
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
      } else {
        alert("Geocode was not successful for the following reason: " + status);
      }
    });

 }

function simulateClick( address ) {
     alert( address );
}
