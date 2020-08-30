const userName = document.getElementById("userName");
const pwd = document.getElementById("pwd");

const userNameErMsg = document.querySelector("#userNameErMsg");
const pwdErMsg = document.querySelector("#pwdErMsg");

const networkError = document.querySelector("#loginErMsg");

userName.addEventListener("input", userNameValidator);
pwd.addEventListener("input", pwdValidator);

const loginButtonListener = document.getElementById("loginBtn").addEventListener("click", loginValidator);
const registerButtonListener = document.getElementById("registerBtn").addEventListener("click", registerButton);


