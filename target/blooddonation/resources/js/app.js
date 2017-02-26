(function($) {
	$(function() {
		$('.button-collapse').sideNav();
		
		$('.parallax').parallax();
		
		$('select').material_select();
		
		$('.datepicker').pickadate({
		    format: 'MM/dd/yyyy' });
		
		$("table").tablesorter();
		
		$('ul.tabs').tabs();
		
		$('.modal').modal({
			dismissible: true,
			ready: function(modal, trigger) {
				console.log(modal, trigger);
			}
		});
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