const regUserName = document.getElementById("userName");
const regPwd = document.getElementById("password");
const regConfPwd = document.getElementById("confPassword");
const regFirstName = document.getElementById("firstName");
const regLastName = document.getElementById("lastName");
const regEmailId = document.getElementById("emailId");
const regPhoneNo = document.getElementById("mobileNo");


const regUserNameErMsg = document.querySelector("#userNameErMsg");
const regPwdErMsg = document.querySelector("#passwordErMsg");
const regConfPwdErMsg = document.querySelector("#confPasswordErMsg");
const regFirstNameErMsg = document.querySelector("#firstNameErMsg");
const regLastNameErMsg = document.querySelector("#lastNameErMsg");
const regEmailIdErMsg = document.querySelector("#emailIdErMsg");
const regPhoneNoErMsg = document.querySelector("#mobileNoErMsg");
const regServiceErMsg = document.querySelector("#regServiceMsg");
const registerBtn = document.getElementById("registerBtn");
const confirmRegisterBtn = document.getElementById("confirm");


const regForm = Array(7);

regUserName.addEventListener("input", regUserNameValidator);
regPwd.addEventListener("input", regPwdValidator);
regConfPwd.addEventListener("input", regConfPwdValidator);
regFirstName.addEventListener("input", regFirstNameValidator);
regLastName.addEventListener("input", regLastNameValidator);
regEmailId.addEventListener("input", regEmailIdValidator);
regPhoneNo.addEventListener("input", regPhoneNoValidator);

const registerButtonListener = registerBtn.addEventListener("click", registrationValidator);
const confirmRegisterBtnListener = confirmRegisterBtn.addEventListener("click", confirmRegistrationValidator);

function initRegProcess() {
    registerBtn.disabled = true;
    regForm.fill(false, 0, 7);
    regLastName.value = "";
    regFirstName.value = "";
    regPhoneNo.value = "";
    regEmailId.value = "";
    regPwd.value = "";
    regConfPwd.value = "";
    regUserName.value = "";
}

initRegProcess();