$(document).ready(function() {
	$.ajaxSetup({
		cache : true
	});
	$.getScript('//connect.facebook.net/en_US/sdk.js', function() {
		FB.init({
			appId : '1613962585285555',
			version : 'v2.8'
		});
	});
	
	if (localStorage.hidePromotion) {
		$('.facebook-share').hide();
	}
});

function facebookShare() {
	FB.ui({
		method : 'share',
		href : 'http://blooddonation-bloodspace.rhcloud.com/'
	}, function(response) {
		console.log(response);
	});
};

function hidePromotion() {
	console.log("Hiding promotion..");
	localStorage.hidePromotion = true;
	$('.facebook-share').hide();
}

