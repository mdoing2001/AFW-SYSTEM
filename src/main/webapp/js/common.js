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
    
    var setUserInfoNavBar = function(userCode) {
    	var name = '';
    	switch(userCode) {
	    	case 'AA': name = '管理員'; break;
	    	case '189': name = '南京店'; break;
	    	case '201': name = '復興店'; break;
	    	case '221': name = '微風店'; break;
	    	case '222': name = '大安店'; break;
	    	case '169': name = '敦化店'; break;
	    	case '295': name = '忠孝店'; break;
    	}
    	$('#userName').val(name);
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
					if(response.message == false) {
						alert("請重新登入");
						window.location.href = root + "/AFW-I-System/login.html";
						return false;
					} else {
						console.log(response.userCode);
						setUserInfoNavBar(response.userCode);
						getAllUserAjax();
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
	
	window.onload = function() {
    	checkSession();
		
	};
	
	
})(jQuery);