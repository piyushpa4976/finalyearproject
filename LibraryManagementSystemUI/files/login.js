function fn() {
    document.write("redirecting")
    setTimeout(() => {
        window.open("registration.html", "_self")

    }, 1000)

}

function edit_details() {
    document.getElementById('body').style.display = "block"
    document.getElementById("logindiv").style.display = "none";
    document.getElementById("logt").style.display = "none"
    document.getElementById('edit_profile').style.display = "block"

}

function show_details() {
    document.getElementById('body').style.display = "block"
    document.getElementById("logindiv").style.display = "none";
    document.getElementById("logt").style.display = "block"
    document.getElementById('edit_profile').style.display = "none"
}



firebase.auth().onAuthStateChanged(function(user) {
    if (user) {
        // User is signed in.

        document.getElementById('body').style.display = "block"
        document.getElementById("logindiv").style.display = "none";
        document.getElementById("logt").style.display = "block"
        document.getElementById('edit_profile').style.display = "none"


        var user = firebase.auth().currentUser;



        if (user != null) {
            var email_id = user.email;
            var userid = user.uid;
            firebase.database().ref('web').child(userid).once('value').then(function(snapshot) {
                var fname = snapshot.val().firstname;
                var lname = snapshot.val().lastname;
                var mothersname = snapshot.val().mothersname;
                var fathersname = snapshot.val().fathersname;
                var phone = snapshot.val().phone;
                var roll = snapshot.val().roll;
                document.getElementById("name").innerHTML = fname + " " + lname;
                document.getElementById("mothers_name").innerHTML = mothersname;
                document.getElementById("fathers_name").innerHTML = fathersname;
                document.getElementById("phone").innerHTML = phone;
                document.getElementById("email").innerHTML = email_id;
                document.getElementById("rollno").innerHTML = roll;

                // ...
            });




        }

    } else {
        // No user is signed in.

        document.getElementById('body').style.display = "block"
        document.getElementById("logindiv").style.display = "block";
        document.getElementById("logt").style.display = "none"
        document.getElementById('edit_profile').style.display = "none"



    }
});

function login() {

    var userEmail = document.getElementById("email_field").value;
    var userPass = document.getElementById("password_field").value;


    firebase.auth().signInWithEmailAndPassword(userEmail, userPass).catch(function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;

        window.alert("Error : " + errorMessage);

        // ...
    });

}

function logout() {
    firebase.auth().signOut();
}






document.getElementById('formsection').addEventListener('submit', submitform);
document.getElementById('reset_form').addEventListener('reset', clear_fields);


function submitform(e) {
    e.preventDefault();
    register();



}

function fn() {
    window.open("registration.html", '_self');
}

function getinputvalue(id) {
    return document.getElementById(id).value;
};

function clear_fields(e) {
    e.preventDefault();
    document.getElementById('formsection').reset();
}

function register() {
    var firstname = getinputvalue('efirst_name');
    var lastname = getinputvalue('elast_name');
    var fathersname = getinputvalue('efathers_name');
    var mothersname = getinputvalue('emothers_name');
    var phone = getinputvalue('ephone');
    var roll = getinputvalue('eroll');


    var student = {
        firstnameref: firstname,
        lastnameref: lastname,
        mothersnameref: mothersname,
        fathersnameref: fathersname,
        rollref: roll,
        phoneref: phone,

    }


    var valid = validate_form(student);
    if (valid) {
        //firebase store
        savedetails(firstname, lastname, fathersname, mothersname, phone, roll);

    } else {
        console.log('invalid', valid)
        document.getElementById("warn").innerHTML = 'fill the form';
        document.getElementById('on_error').style.display = "Block"
        setTimeout(() => {
            document.getElementById('on_error').style.display = "none"

        }, 3000)
    }
}



document.getElementById('file').onchange = function() {
    
};

function savedetails(firstname, lastname, fathersname, mothersname, phone, roll) {
    var user = firebase.auth().currentUser;
    var userid = user.uid;
    var dbref = firebase.database().ref('web').child(userid);


    dbref.set({
        firstname: firstname,
        lastname: lastname,
        fathersname: fathersname,
        mothersname: mothersname,
        phone: phone,
        roll: roll


    }, function(error) {
        if (error) {
            // The write failed...
        } else {
            document.getElementById("warn").innerHTML = 'success';
            document.getElementById('on_error').style.display = "Block"
            setTimeout(() => {
                document.getElementById('on_error').style.display = "none"

            }, 3000)
        }
    });

};

function validate_form(user) {
    var valid = true;
    for (var key in user) {
        if (user[key] == "" || user[key] == undefined) {
            valid = false;
        }

    }
    return valid;

};