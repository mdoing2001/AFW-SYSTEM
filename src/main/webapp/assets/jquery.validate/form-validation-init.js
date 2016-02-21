
/**
* Theme: Velonic Admin Template
* Author: Coderthemes
* Form Validator
*/

!function($) {
    "use strict";

    var FormValidator = function() {
        this.$commentForm = $("#commentForm"), //this could be any form, for example we are specifying the comment form
        this.$signupForm = $("#signupForm");
        this.$basicform = $("#company2-form");
    };

    //init
    FormValidator.prototype.init = function() {
        //validator plugin
        $.validator.setDefaults({
            submitHandler: function() { alert("submitted!"); }
        });

        // validate the comment form when it is submitted
        this.$commentForm.validate();

        // validate signup form on keyup and submit
        this.$signupForm.validate({
            rules: {
            	name: "required",
                lastname: "required",
                username: {
                    required: true,
                    minlength: 2
                },
                password: {
                    required: true,
                    minlength: 5
                },
                confirm_password: {
                    required: true,
                    minlength: 5,
                    equalTo: "#password"
                },
                email: {
                    required: true,
                    email: true
                },
                topic: {
                    required: "#newsletter:checked",
                    minlength: 2
                },
                agree: "required",
                cbranch: "required",
                cAddress: "required",
                cOfficeNumber: {
                	required: true,
                	digits: true
                },
                ceim: {
                	required: true,
                	digits: true
                },
                cceo: "required",
                cphoneNumber: {
                	required: true,
                	minlength:9,
                	digits: true
                },
                cbirthday: {
                	date: true
                }
//                cAddress2 : "required"
            },
            messages: {
            	name: "請輸入公司名稱",
                lastname: "Please enter your lastname",
                username: {
                    required: "Please enter a username",
                    minlength: "Your username must consist of at least 2 characters"
                },
                password: {
                    required: "Please provide a password",
                    minlength: "Your password must be at least 5 characters long"
                },
                confirm_password: {
                    required: "Please provide a password",
                    minlength: "Your password must be at least 5 characters long",
                    equalTo: "Please enter the same password as above"
                },
                email: "Please enter a valid email address",
                agree: "Please accept our policy",
                cbranch: "請選擇簽約分店",
                cAddress: "請輸入地址",
                cOfficeNumber: {
                	required: "請輸入辦公室號碼",
                	digits: "格式錯誤"
                },
                ceim: {
                	required: "請輸入統編",
                	digits: "格式錯誤"
                },
                cceo: "請輸入負責人",
                cphoneNumber: {
                	required: "請輸入電話",
                	minlength: "長度錯誤",
                	digits: "格式錯誤"
                },
                cbirthday: {
                	date: "格式錯誤"
                }
//                cAddress2: "請輸入轉寄地址"
            }
        });
        
        this.$basicform.validate({
            rules: {
            	name: "required",
                lastname: "required",
                username: {
                    required: true,
                    minlength: 2
                },
                password: {
                    required: true,
                    minlength: 5
                },
                confirm_password: {
                    required: true,
                    minlength: 5,
                    equalTo: "#password"
                },
                email: {
                    required: true,
                    email: true
                },
                topic: {
                    required: "#newsletter:checked",
                    minlength: 2
                },
                agree: "required",
                cbranch: "required",
                cAddress: "required",
                cOfficeNumber: {
                	required: true,
                	digits: true
                },
                ceim: {
                	required: true,
                	digits: true
                },
                cceo: "required",
                cphoneNumber: {
                	required: true,
                	minlength:9,
                	digits: true
                },
                cbirthday: {
                	date: true
                }
//                cAddress2 : "required"
            },
            messages: {
            	name: "請輸入公司名稱",
                lastname: "Please enter your lastname",
                username: {
                    required: "Please enter a username",
                    minlength: "Your username must consist of at least 2 characters"
                },
                password: {
                    required: "Please provide a password",
                    minlength: "Your password must be at least 5 characters long"
                },
                confirm_password: {
                    required: "Please provide a password",
                    minlength: "Your password must be at least 5 characters long",
                    equalTo: "Please enter the same password as above"
                },
                email: "Please enter a valid email address",
                agree: "Please accept our policy",
                cbranch: "請選擇簽約分店",
                cAddress: "請輸入地址",
                cOfficeNumber: {
                	required: "請輸入辦公室號碼",
                	digits: "格式錯誤"
                },
                ceim: {
                	required: "請輸入統編",
                	digits: "格式錯誤"
                },
                cceo: "請輸入負責人",
                cphoneNumber: {
                	required: "請輸入電話",
                	minlength: "長度錯誤",
                	digits: "格式錯誤"
                },
                cbirthday: {
                	date: "格式錯誤"
                }
//                cAddress2: "請輸入轉寄地址"
            }
        });

        // propose username by combining first- and lastname
        $("#username").focus(function() {
            var firstname = $("#firstname").val();
            var lastname = $("#lastname").val();
            if(firstname && lastname && !this.value) {
                this.value = firstname + "." + lastname;
            }
        });

        //code to hide topic selection, disable for demo
        var newsletter = $("#newsletter");
        // newsletter topics are optional, hide at first
        var inital = newsletter.is(":checked");
        var topics = $("#newsletter_topics")[inital ? "removeClass" : "addClass"]("gray");
        var topicInputs = topics.find("input").attr("disabled", !inital);
        // show when newsletter is checked
        newsletter.click(function() {
            topics[this.checked ? "removeClass" : "addClass"]("gray");
            topicInputs.attr("disabled", !this.checked);
        });

    },
    //init
    $.FormValidator = new FormValidator, $.FormValidator.Constructor = FormValidator
}(window.jQuery),


//initializing 
function($) {
    "use strict";
    $.FormValidator.init()
}(window.jQuery);