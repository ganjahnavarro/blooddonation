(function($) {
	$(function() {
		$('.button-collapse').sideNav();
		
		$('.parallax').parallax();
		
		$('select').material_select();
		
		$('.datepicker').pickadate();
		
		$("table").tablesorter();
		
		$('ul.tabs').tabs();
		
		$('.modal').modal({
			dismissible: true,
			ready: function(modal, trigger) {
				console.log(modal, trigger);
			}
		});
		
		try {
			document.querySelector("input[type='number']").addEventListener("keypress",
				function(evt) {
					if (evt.which < 48 || evt.which > 57) {
						evt.preventDefault();
					}
				}
			);
		} catch (error) {
			console.log("No input type number found.");
		}
	});
})(jQuery);


function openConfirmation(message, action) {
	console.log($('#confirmation-modal-message').html());
	console.log($('#confirmation-modal-message').val());
	
	$('#confirmation-modal-message').html(message);
	$('#confirmation-modal-action').attr('href', action);
	$('#confirmation-modal').modal('open');
}

function closeConfirmation() {
	$('#confirmation-modal').modal('close');
}

