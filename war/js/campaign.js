/*
 * MovingBoxes demo script
 */

function initMovingBoxes(initCallback, completedCallback) {

	$('#slider').movingBoxes({
		startPanel : 2, // start with this panel
		width : 700, // overall width of movingBoxes (not including
		// navigation arrows)
		panelWidth : .3, // current panel width adjusted to 70% of
		// overall width
		fixedHeight : true,
		imageRatio : 1 / 1, // Image ratio set to 16:9
		buildNav : true, // if true, navigation links will be added
		navFormatter : function() {
			return "&#9679;";
		}, // function which gets
		// nav text from span
		// inside the panel
		// header
		wrap : true,
		initialized : initCallback,
		completed : completedCallback,

	});

	// Add a slide
	// var imageNumber = 1,
	// panel = '',
	// slider = $('#slider');
	//
	// var i, t = '', len = $('#slider .mb-panel').length + 1;
	// for ( i=1; i<len; i++ ){
	// t += '<a href="#" rel="' + i + '">' + i + '</a> ';
	// }

	// // Report events to firebug console
	// $('.mb-slider').bind('initialized.movingBoxes initChange.movingBoxes
	// beforeAnimation.movingBoxes completed.movingBoxes',function(e, slider,
	// tar){
	//	
	// if (window.console && window.console.firebug){
	// var txt = slider.$el[0].id + ': ' + e.type + ', now on panel #' +
	// slider.curPanel + ', targeted panel is ' + tar;
	// console.debug( txt );
	// }
	// });

}