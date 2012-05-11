(function ($) {
	$.fn.extend($.fn, {
		version : "0.0.0.2",
		wrapLabel : function() {
		    $("body :input[label]").each(function() {
		    	var $item = $(this),
					label = $item.attr('label'),
					id =  $item.attr('id'),
					forLabel = ( id && ' for="'+ id +'"' || "" );
		    	label && $item.wrap('<div class="clearfix"/>')
						.before('<label'+ forLabel +'>'+ label +': </label>')
						.wrap('<div class="input"/>');
		    });
		} 
	}); 
})(jQuery);