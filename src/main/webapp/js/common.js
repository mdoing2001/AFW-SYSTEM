(function($){
	
	var root = location.protocol + '//' + location.host;
	
	$('#logOut').on('click', function() {
    	logOut();
    });
	
    var logOut = function() {
    	$.ajax({
			url:root + "/AFW-I-System/new/user/logout",
			type:'get',
			dataType : 'json',
			contentType: 'application/json',
			success : function(response) {
				//alert("success");
				if(response.status == 'success') {
					window.location.href = root + "/AFW-I-System/login.html";
				}
			},
			error : function(e) {
				alert("error");
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
    };
	
	var checkSession = function() {
		$.ajax({
			url:root + "/AFW-I-System/session/user/check",
			type:'get',
			dataType : 'json',
			contentType: 'application/json',
			success : function(response) {
				//alert("success");
				if(response.status == 'success') {
					if(response.message == 'false') {
						alert("請重新登入");
						window.location.href = root + "/AFW-I-System/login.html";
						return false;
					}
				}
			},
			error : function(e) {
				alert("error");
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		
		return true;
	};
	
	
})(jQuery);