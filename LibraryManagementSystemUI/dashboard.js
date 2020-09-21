$(document).ready(function() {

    $('#admindashboard').css("display", "block")
    var user = JSON.parse(sessionStorage.getItem('lmuser_data'));

    function hideall() {
        $('#admindashboard').css("display", "none")
        $('#viewuserprofile').css("display", 'none')
        $('#changepassworddiv').css("display", "none")
        $('#addbookdiv').css("display", "none")
        $('#showlibrarydiv').css("display", "none")
        $('#showusersdiv').css("display", "none")
    }

    $('#viewprofile').on('click', function() {

        console.log("viewprofil");
        hideall();
        $('#viewuserprofile').css("display", 'block')
            //update user

        $('#user_fullnameID').attr('disabled', true)
        $('#user_phoneID').attr('disabled', true)
        $('#user_emailID').attr('disabled', true)
            //  $('#user_semesterID').attr('disabled', true)
        $('#user_pincodeID').attr('disabled', true)



        console.log(user);
        try {
            $('#user_fullnameID').attr('value', user.user_fullname);
            $('#user_phoneID').attr('value', user.user_phone);

            $('#user_stateID').attr('value', user.user_state);
            $('#user_addressID').attr('value', user.user_address);
            $('#enter_cityID').attr('value', user.user_city);

            $('#user_emailID').attr('value', user.user_email);
            //  $('#user_fullnameID').attr('value', user.user_fullname);
            $('#user_semesterID').attr('value', user.user_semester);
            $('#user_pincodeID').attr('value', user.user_pincode);
            $("#user_dobID").datepicker({ dateFormat: "yy-mm-dd" })
            $("#user_dobID").datepicker("setDate", new Date("'" + user.user_dob + "'"))
            $("#user_semesterID option[value='" + user.user_semester + "']").prop('selected', true);

            $("input[name=user_gender][value='" + user.user_gender + "']").prop('checked', true);

        } catch (e) { console.log(e); }


        $('#profileupdate').on('click', function() {

            var userdetailsdata = $('#userdetailsform').serializeJSON();

            console.log(JSON.stringify(userdetailsdata));
            // sessionStorage.setItem("lmuser_data", ud);
            // sessionStorage.setItem("lmuser_id", ud.user_id)
            console.log("profile update");
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/user/updateuser/" + user.user_id,
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(userdetailsdata),
                dataType: "json",
                success: function(data) {
                    console.log("success");
                    //var ud = JSON.stringify(data.data)

                    //  console.log(JSON.stringify(data.data));

                    if (data.status_code == 200) {
                        console.log(data.data);

                        sessionStorage.setItem("lmuser_data", JSON.stringify(data.data));
                        location.href = "dashboard.html";

                    } else {

                        console.log(data.message);
                    }

                },
                error: function(errMsg) {
                    console.log("error");
                    console.log(errMsg);


                }
            });
        })





    })
    $("#verify_passwordID").keyup(function() {

        if ($('#user_passwordID').val() != $('#verify_passwordID').val()) {
            $('#verifypasswordspan').text("password not match")
        } else {
            $('#verifypasswordspan').text("")

        }
    });
    $("#user_passwordID").keyup(function() {

        if ($('#user_passwordID').val() == $('#current_user_passwordID').val()) {
            $('#verifypasswordspan').text("new password can not be old password")
        } else {
            $('#verifypasswordspan').text("")

        }
    });

    $('#changepassword').on('click', function() {
        console.log("chngpsd");
        hideall();
        $('#changepassworddiv').css("display", "block")

        $('#updatepassword').on('click', function() {

            if ($('#user_passwordID').val() != $('#verify_passwordID').val()) {
                $('#verifypasswordspan').text("password not match")
            } else {
                var da = $('#resetpasswordform').serializeJSON();
                console.log(da);
                console.log(user.user_id);
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/user/updatepassword/" + user.user_id + "/" + $('#current_user_passwordID').val(),
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(da),
                    dataType: "json",
                    success: function(data) {
                        console.log("success");
                        //var ud = JSON.stringify(data.data)

                        //  console.log(JSON.stringify(data.data));

                        if (data.status_code == 200) {
                            alert(data.data)
                            console.log(data.data);
                            location.href = "dashboard.html";

                            // sessionStorage.setItem("lmuser_data", JSON.stringify(data.data));

                        } else {

                            console.log(data.message);
                        }

                    },
                    error: function(errMsg) {
                        console.log("error");
                        console.log(errMsg);


                    }
                });
            }


        })

    })

    $('#addbook').on('click', function() {
        hideall();
        $('#addbookdiv').css("display", "block")
        $('#addbookform').validate({
            rules: {
                book_isbn: {

                    required: true
                },
                book_author: {
                    required: true,

                },
                book_title: {
                    required: true,


                },
                book_numberOfCopies: {
                    required: true,


                },
                book_yearOfPublication: {
                    required: true,


                },
                book_language: {
                    required: true,


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


        $('#addbookbtn').on('click', function() {




            if ($('#addbookform').valid()) {
                var addbookdata = $('#addbookform').serializeJSON();



                console.log(JSON.stringify(addbookdata))
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/book",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(addbookdata),
                    dataType: "json",
                    success: function(data) {
                        console.log(data);

                    },
                    error: function(errMsg) {
                        alert(errMsg);
                    }
                });
            }


        })

    })


    $('#viewlibrary').on('click', function() {
        hideall();
        $('#showlibrarydiv').css("display", "block")





        $('#librarydt').DataTable({

            "ajax": {
                "url": "http://localhost:8080/book",
                "dataSrc": ""
            },
            "columns": [
                { "data": "book_title", "title": "Book Title" },
                { "data": "book_author", "title": "Book Author" },
                { "data": "book_isbn", "title": "Book ISBN" },
                { "data": "book_yearOfPublication", "title": "Year Of Publication" },
                { "data": "book_numberOfCopies", "title": "No. Of Copies" },
                { "data": "book_language", "title": "Book Language" },
                { "data": "book_totalpages", "title": "Total Pages" },
                { "data": "book_price", "title": "Book Price" },

                {
                    mRender: function(data, type, full) {
                        //console.log(full);

                        //   return '<a class="btn btn-info btn-sm" href=#/' + full[0] + '>' + 'Edit' + '</a>';
                        //return ' <button  class="btn btn-danger py-0 td_button_del" type="button"> <i class="fa fa-trash" aria-hidden="true"></i></button>';
                        return '<div class="row"> <button name="td__button_st_edit" class="btn btn-warning col-sm-5 py-0 td__button_st_edit" type="button">  <i class="fa fa-edit" aria-hidden="true" ></i></button>  <button  class="btn btn-danger ml-2 col-sm-5 py-0 td_button_del" type="button"> <i class="fa fa-trash" aria-hidden="true"></i></button> </div>';
                    }
                }

            ],
            "paging": true,
            // "responsive": true,
            "searching": true,
            "ordering": true,
            "scrollX": true,
            "retrieve": true,
            "lengthMenu": [
                [2, 5, 10, 500, 1000],
                [2, 5, 10, 500, "Max"]
            ]
        });
















    })

    $('#userlist').on('click', function() {

        hideall();
        $('#showusersdiv').css("display", "block");
        $('#usersdt').DataTable({

            "ajax": {
                "url": "http://localhost:8080/user/userlist",
                "dataSrc": ""
            },
            "columns": [
                { "data": "user_fullname", "title": "Full Name" },
                { "data": "user_gender", "title": "Gender" },
                { "data": "user_dob", "title": "DOB" },
                { "data": "user_class", "title": "Class" },
                { "data": "user_semester", "title": "Semester" },
                { "data": "user_city", "title": "Address City" },
                { "data": "user_phone", "title": "Phone" },
                { "data": "user_email", "title": "Email" },


                {
                    mRender: function(data, type, full) {
                        //console.log(full);

                        //   return '<a class="btn btn-info btn-sm" href=#/' + full[0] + '>' + 'Edit' + '</a>';
                        //return ' <button  class="btn btn-danger py-0 td_button_del" type="button"> <i class="fa fa-trash" aria-hidden="true"></i></button>';
                        return '<div class="row"> <button name="td__button_st_edit" class="btn btn-warning col-sm-5 admin py-0 td__button_st_edit" type="button">  <i class="fa fa-edit" aria-hidden="true" ></i></button>  <button  class="btn btn-danger admin ml-2 col-sm-5 py-0 td_button_del" type="button"> <i class="fa fa-trash" aria-hidden="true"></i></button> </div>';
                    }
                }

            ],
            "paging": true,
            // "responsive": true,
            "searching": true,
            "ordering": true,
            "scrollX": true,
            "retrieve": true,
            "lengthMenu": [
                [2, 5, 10, 500, 1000],
                [2, 5, 10, 500, "Max"]
            ]
        });

    })
    console.log("ready");
    $('.admin').hide();
    console.log(user.user_type);
    if (user.user_type == 'Admin') {
        $('.admin').show();
    }








})