var langMap = {
	title : "Store",
	error_msg : "Network error,please try again",
	btn_text : "Back to the game",
	redirec_btn_text : "Refresh",
	operate_app : "Please operate at app"
}

~function(){
	$.each(langMap,function(key,value){
		if($("."+key)){
			$("."+key).html(value);
		}
	})
	$(".content").fadeIn();
}()

