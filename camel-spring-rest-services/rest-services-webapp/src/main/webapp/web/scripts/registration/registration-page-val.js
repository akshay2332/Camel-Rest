function regUserNameValidator(event) {
    regUserNameErMsg.style.display = 'block';
    const userName = event.target.value;

    if (validTextToRegex(userName, alphaNumeric)) {
        regUserNameErMsg.style.display = 'none';
        regForm[2] = true;

    } else {
        regUserNameErMsg.innerText = 'Please enter a valid User Name.';
        regForm[2] = false;
    }
    enableRegisterButton();
}

function regPwdValidator(event) {
    regPwdErMsg.style.display = 'block';
    const regPassword = event.target.value;

    if (validTextToRegex(regPassword, password)) {
        regPwdErMsg.style.display = 'none';
        regConfPwd.disabled = false;
        regForm[4] = true;

    } else {
        regPwdErMsg.innerText = 'Please enter a valid Password.';
        regConfPwd.disabled = true;
        regForm[4] = false;
    }
    enableRegisterButton();
}

function regConfPwdValidator(event) {
    regConfPwdErMsg.style.display = 'block';
    const regPassword = event.target.value;

    if (validTextToRegex(regPassword, password)) {

        if (regPassword !== regPwd.value) {
            regConfPwdErMsg.innerText = 'Confirm password do not match.';
            regForm[5] = false;
        } else {
            regConfPwdErMsg.style.display = 'none';
            regForm[5] = true;
        }
    } else {
        regForm[5] = false;
        regConfPwdErMsg.innerText = 'Please enter a valid Password.';
    }
    enableRegisterButton();
}


function regFirstNameValidator(event) {
    regFirstNameErMsg.style.display = 'block';
    const firstName = event.target.value;

    if (validTextToRegex(firstName, onlyAlphabets)) {
        regFirstNameErMsg.style.display = 'none';
        regForm[1] = true;

    } else {
        regFirstNameErMsg.innerText = 'Please enter a valid First Name.';
        regForm[1] = false;
    }
    enableRegisterButton();
}


function regLastNameValidator(event) {
    regLastNameErMsg.style.display = 'block';
    const lastName = event.target.value;

    if (validTextToRegex(lastName, onlyAlphabets)) {
        regLastNameErMsg.style.display = 'none';
        regForm[0] = true;

    } else {
        regLastNameErMsg.innerText = 'Please enter a valid Last Name.';
        regForm[0] = false;
    }
    enableRegisterButton();
}


function regEmailIdValidator(event) {
    regEmailIdErMsg.style.display = 'block';
    const regEmailId = event.target.value;

    if (validTextToRegex(regEmailId, emailId)) {
        regEmailIdErMsg.style.display = 'none';
        regForm[3] = true;

    } else {
        regEmailIdErMsg.innerText = 'Please enter a valid Email Id.';
        regForm[3] = false;
    }
    enableRegisterButton();
}

function regPhoneNoValidator(event) {
    regPhoneNoErMsg.style.display = 'block';
    const mobileNo = event.target.value;

    if (validTextToRegex(mobileNo) && (mobileNo.length == 10)) {
        regPhoneNoErMsg.style.display = 'none';
        regForm[6] = true;

    } else {
        regPhoneNoErMsg.innerText = 'Please enter a valid Mobile No.';
        regForm[6] = false;

    }
    enableRegisterButton();
}

function validTextToRegex(text, regex) {

    if (text && "" !== text && text.match(regex)) {
        return true;
    } else {
        return false;
    }
}

function enableRegisterButton() {

    if (regForm.every((item) => item)) {
        confirmRegisterBtn.disabled = false;

    } else {
        confirmRegisterBtn.disabled = true;
    }
}

function confirmRegistrationValidator() {
    confirmRegisterBtn.disabled = true;
    registerBtn.disabled = false;
    enableDisableInput(true);
}

function enableDisableInput(enableDisableFlag) {
    regUserName.disabled = enableDisableFlag;
    regPwd.disabled = enableDisableFlag;
    regConfPwd.disabled = enableDisableFlag;
    regFirstName.disabled = enableDisableFlag;
    regLastName.disabled = enableDisableFlag;
    regEmailId.disabled = enableDisableFlag;
    regPhoneNo.disabled = enableDisableFlag;
}

function registrationValidator(event) {

    const data = {
        user: {
            name: {
                last: regLastName.value,
                first: regFirstName.value,
            },
            number: regPhoneNo.value,
            email: regEmailId.value,
            password: regPwd.value,
            id: regUserName.value
        }
    }

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');


    executePostRequest(registrationURL, data, headers)
        .then(response => {
            return response.json();
        })
        .then(data => {

            console.log(data);

            if (data.response.returnCode === 2) {
                regServiceErMsg.style.display = 'block';
                regServiceErMsg.innerText = data.response.message;
                console.log("Error code 2");
                initRegProcess();
                enableDisableInput(false);

            } else if (data.response.returnCode === 0) {
                regServiceErMsg.style.display = 'block';
                regServiceErMsg.innerText = data.response.message;
                console.log("Succcess");
                console.log("login should be called");
                window.location.href = `${webPageBaseURL}${loginPage}`;
            } else {
                regServiceErMsg.style.display = 'block';
                regServiceErMsg.innerText = data.response.message;
                console.log("Error");
                initRegProcess();
                enableDisableInput(false);
            }
        }).catch((error) => {
        regServiceErMsg.style.display = 'block';
        regServiceErMsg.innerText = "Please try after sometime.";
        initRegProcess();
        enableDisableInput(false);

    });
}