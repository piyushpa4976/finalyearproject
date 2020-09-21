$(document).ready(function() {



    // $("#same_addressID").on("click", function() {
    //     if (this.checked) {

    //         $("#permanent_address_line1ID").val($("#address_line1ID").val());
    //         $("#permanent_cityID").val($("#cityID").val());
    //         $("#permanent_regionID").val($("#regionID").val());
    //         $("#permanent_postal_codeID").val($("#postal_codeID").val());
    //     } else {
    //         $("#permanent_address_line1ID").val('');
    //         $("#permanent_cityID").val('');
    //         $("#permanent_regionID").val('');
    //         $("#permanent_postal_codeID").val('');
    //     }
    // })

    try {

        // $("#student_referenceNumberID").attr("disabled", true)
        // $("#student_nameID").attr("disabled", true)
        // $("#student_applicationID").attr("disabled", true)
        // $("#student_mobile_numberID").attr('disabled', true)
        // $("#student_emailID").attr('disabled', true)

        var ob = localStorage.getItem("lmuser_data");
        var userdetails = JSON.parse(ob);


        console.log(localStorage.getItem("lmuser_data"));
        console.log(userdetails.user_id);


    } catch (error) {
        console.log(error);

    }

})