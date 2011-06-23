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
		hashTags : false,
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
}