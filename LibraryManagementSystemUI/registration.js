$(document).ready(function() {
    console.log("ready");

    $('#recoverpasswordbtn').on('click', function() {

        $('#recoverpasswordform').validate({
            rules: {
                user_email: {
                    required: true,
                    email: true,

                },
                user_fullname: {
                    required: true,

                }
            },
            messages: {
                user_email: {
                    required: "enter email address",
                    email: "please enter <em>valid</em> email"
                },
                user_fullname: {
                    required: "enter name"
                }
            },
            errorElement: 'span',
            errorPlacement: function(error, element) {
                error.addClass('invalid-feedback');
                element.closest('.form-group').append(error);
                if (element.is(":radio")) {
                    error.appendTo(element.parents('.container'));
                } else { // This is the default behavior 
                    error.insertAfter(element);
                }

            },
            // success: function(label, element) {

            //     element.closest('.form-group');
            //     if (element.id == "rstudent_reference_number") {
            //         label.text("User details Available!").removeClass("is-invalid").addClass("valid");
            //     }
            // },
            highlight: function(element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function(element, errorClass, validClass) {
                $(element).removeClass('is-invalid');

            }

        })
        if ($('#recoverpasswordform').valid()) {
            var recoverpassword = $('#recoverpasswordform').serializeJSON();
            console.log(recoverpassword);

            $.ajax({
                type: "POST",
                url: "http://localhost:8080/user/forgetpassword",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(recoverpassword),
                dataType: "text",
                success: function(data) {
                    console.log(data);
                    alert(data);

                },
                error: function(errMsg) {
                    alert(errMsg);

                }
            });

        }

    })

    $('#regiterform').validate({
        rules: {
            user_fullname: {

                required: true
            },
            user_password: {
                required: true,

                nowhitespace: true,


            },
            user_password_verify: {
                required: true,

                equalTo: "#Ruser_passwordID"

            },
            user_email: {
                required: true,
                email: true,
                remote: {
                    url: "http://localhost:8080/user/usernamevalidate",
                    type: 'GET',
                    data: {
                        user_email: function() {

                            console.log($('#Ruser_emailID').val());
                            return $('#Ruser_emailID').val();
                        },
                        user_fullname: function() {
                            return $('#Ruser_fullnameID').val();
                        }
                    }
                }

            },
            user_phone: {
                required: true,


            },
            user_type: {
                required: true,


            },
            user_class: {
                required: true,


            },
            user_semester: {
                required: true,


            },
            user_pincode: {
                required: true,
                number: true


            }



        },
        // success: function(label) {
        //     console.log($(label));
        //     var $label = $(label);
        //     $(label).nextAll().remove();
        //     if (!$label.hasClass('valid')) {
        //         $label.addClass('valid');
        //     }
        //     $label.removeClass('error');
        // },
        messages: {
            user_fullname: {
                required: "Enter full name"
            },
            user_password: {
                required: "Enter password",
                nowhitespace: " no whitespace please",


            },
            user_password_verify: {
                required: "please verify password",
                equalTo: "password not match"

            },
            user_email: {
                required: "Enter email address",
                remote: "Email already Exists!"


            },
            user_phone: {
                required: "Enter phone number",
            },
            user_type: {
                required: "select user type",


            },
            user_class: {
                required: "select class",


            },
            user_semester: {
                required: "select semester",


            },
            user_pincode: {
                required: "enter address pincode",
                number: "must be in number"


            }
        },
        errorElement: 'span',
        errorPlacement: function(error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
            if (element.is(":radio")) {
                error.appendTo(element.parents('.container'));
            } else { // This is the default behavior 
                error.insertAfter(element);
            }

        },
        // success: function(label, element) {

        //     element.closest('.form-group');
        //     if (element.id == "rstudent_reference_number") {
        //         label.text("User details Available!").removeClass("is-invalid").addClass("valid");
        //     }
        // },
        highlight: function(element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).removeClass('is-invalid');

        }
    });


    $('#registerbtn').on('click', function() {




        if ($('#regiterform').valid()) {
            var registerdata = $('#regiterform').serializeJSON();



            // function replacer(key, value) {
            //     console.log("1" + typeof value);
            //     console.log("2" + key);
            //     // // Filtering out properties
            //     // if (typeof key === 'string') {
            //     //     return undefined;
            //     // }
            //     // return value;
            // }

            // // var foo = {foundation: 'Mozilla', model: 'box', week: 45, transport: 'car', month: 7};
            // console.log(JSON.stringify(registerdata, replacer));

            // const arr = JSON.parse(JSON.stringify(registerdata));
            // arr.forEach(obj => renameKey(obj, 'student_email', 'Email Id'));
            // const updatedJson = JSON.stringify(arr);

            // console.log(updatedJson);
            // var obj = JSON.stringify(arr);

            // obj.

            console.log(JSON.stringify(registerdata))
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/user/register",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(registerdata),
                dataType: "json",
                success: function(data) {
                    console.log(data);
                    alert(data.message);
                },
                error: function(errMsg) {
                    alert(errMsg);
                }
            });
        }
    })
    $('#verifydetailspan').hide();
    $('#rstudent_password').prop('disabled', true)
    $('#repeatpassword').prop('disabled', true)
    $('#rstudent_email').prop('disabled', true)
    $('#rstudent_phone').prop('disabled', true)



    $("#btnverify").on('click', function() {

        if ($('#rstudent_reference_number').val() != "" || $('#rstudent_name').val() != "") {

            $.ajax({
                url: "http://localhost:8080/dbkn/usernamevalidate",
                type: 'GET',
                data: {
                    student_reference_number: function() {

                        console.log($('#rstudent_reference_number').val());
                        return $('#rstudent_reference_number').val();
                    },
                    student_name: function() {
                        return $('#rstudent_name').val();
                    }
                },
                success: function(response) {


                    if (response == "true") {
                        console.log("yes");
                        $('#rstudent_password').prop('disabled', false)
                        $('#repeatpassword').prop('disabled', false)
                        $('#rstudent_email').prop('disabled', false)
                        $('#rstudent_phone').prop('disabled', false)

                    } else {
                        $('#verifydetailspan').text("enter valid details");
                        $('#verifydetailspan').show();
                        setTimeout(function() {


                            $('#verifydetailspan').hide();

                        }, 2000);
                    }
                },
                error: function(er) {
                    console.log(er);
                    $('#verifydetailspan').text(er.statusText);
                    $('#verifydetailspan').show();
                    setTimeout(function() {


                        $('#verifydetailspan').hide();

                    }, 2000);
                }

            })

        } else {
            $('#verifydetailspan').text('enter name and referecne number');
            $('#verifydetailspan').show();
            setTimeout(function() {


                $('#verifydetailspan').hide();

            }, 2000);

        }


    })


    $('#loginbtn').on('click', function() {

        $('#loginform').validate({
            rules: {
                user_name: {
                    required: true,


                },
                user_password: {
                    required: true,
                    nowhitespace: true

                }
            },
            messages: {
                user_name: {
                    required: "enter username",
                    //email: "please enter <em>valid</em> email"
                },
                user_password: {
                    required: "enter password"
                }
            },
            errorElement: 'span',
            errorPlacement: function(error, element) {
                error.addClass('invalid-feedback');
                element.closest('.form-group').append(error);
                if (element.is(":radio")) {
                    error.appendTo(element.parents('.container'));
                } else { // This is the default behavior 
                    error.insertAfter(element);
                }
            },
            highlight: function(element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function(element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }

        })

        if ($('#loginform').valid()) {


            // } else {
            var logindata = $('#loginform').serializeJSON();
            console.log(JSON.stringify(logindata))
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/user/login",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(logindata),
                dataType: "json",
                success: function(data) {
                    console.log("success");
                    var ud = JSON.stringify(data.data)
                    sessionStorage.setItem("lmuser_data", ud);
                    sessionStorage.setItem("lmuser_id", ud.user_id)
                        //  console.log(JSON.stringify(data.data));

                    if (data.status_code == 200) {

                        $("#loginon_success").css("display", "block");

                        setTimeout(function() {

                            location.href = "dashboard.html";
                            $("#loginon_success").css("display", "none");

                        }, 2000);
                    } else {

                        $("#loginon_error").css("display", "block");
                        $('#loginon_errorwarn').text(data.message);
                        setTimeout(function() {
                            $("#loginon_error").css("display", "none");
                        }, 5000);
                    }

                },
                error: function(errMsg) {
                    console.log("error");
                    $("#loginon_error").css("display", "block");
                    $('#loginon_errorwarn').text("Error in Server Connection");
                    console.log(errMsg);


                    setTimeout(function() {
                        $("#loginon_error").css("display", "none");
                    }, 5000);

                }
            });

        }

    })



})













// document.getElementById('formsection').addEventListener('submit', submitform);
// document.getElementById('reset_form').addEventListener('reset', clear_fields);

// function submitform(e) {
//     e.preventDefault();
//     register();



// }

// function fn() {
//     window.open("registration.html", '_self');
// }

// function getinputvalue(id) {
//     return document.getElementById(id).value;
// };

// function clear_fields(e) {
//     e.preventDefault();
//     document.getElementById('formsection').reset();
// }

// function register() {
//     var email = getinputvalue('email');
//     var password = getinputvalue('pswd');

//     var student = {
//         emailref: email,
//         passwordref: password
//     }


//     var valid = validate_form(student);
//     if (valid) {


//     } else {
//         console.log('invalid', valid)
//         document.getElementById("warn").innerHTML = 'fill the form';
//         document.getElementById('on_error').style.display = "Block"

//         setTimeout(() => {
//             document.getElementById('on_error').style.display = "none"

//         }, 3000)
//     }
// }

// function validate_form(user) {
//     var valid = true;
//     for (var key in user) {
//         if (user[key] == "" || user[key] == undefined) {
//             valid = false;
//         }

//     }
//     return valid;

// };