'use strict';

(function(){

	var plugin_name = "skill_points";
	var lang_list = ['ru', 'en'];

	if(CKEDITOR.plugins.get(plugin_name)){
		return false;
	}

	var dialog_name = plugin_name;
	var command = plugin_name;
	var button = command.charAt(0).toUpperCase() + command.substr(1);
	var result_class = "proton_" + plugin_name + "_anchor";
	var img_class = "cke_" + plugin_name;
	var tag_name = "em";
	var priority = 1;

	CKEDITOR.plugins.add('skill_points', {
		requires : ['dialog'],
		hidpi : false,
		lang : lang_list,
		init: function(editor){
			var req = 'div'+ '[!data-plugin,!width,!height]' /* attrs */ +
				'(' + result_class + ')' /* classes */ +
				'{width,height}' /* styles */;

				editor.addComand(command, new CKEDITOR, dialogComand(command, {
					allowedContent: req
				}));

				editor.ui.addButton(button,{
					label : editor.lang[plugin_name].button_label,
					command : command
				})

				var dialog_path = this.path + 'dialogs/' + plugin_name + '.js';
				CKEDITOR.dialog.add(dialog_name, dialog_path);

				editor.on('doubleclick', function(e){
					var element = e.data.element;

					
				})
		},

		afterInit : function(editor){

		}
	})

})();