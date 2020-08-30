const baseURL = `http://localhost:8080/myapplication/services/session`;
const webPageBaseURL = `http://localhost:8080/myapplication/web`;
const loginPage = '/login-page.html';
const registrationPage = '/pages/registration/registration-page.html';
const registrationURL =`/user`;

/*
* Validation Regex
* */
const onlySmallAlphabets = /^[a-z]+$/;
const onlyCapitalAlphabets = /^[A-Z]+$/;
const onlyAlphabets = /^[A-Za-z]+$/;
const onlyNumbers = /^[0-9]+$/;
const alphaNumeric = /^[a-zA-z0-9]+$/;
const emailId = /^[a-zA-Z0-9._]{1,}@[a-zA-Z]{3,}[.][a-zA-Z]{3,}|[a-zA-Z0-9._]{1,}@[a-zA-Z]{3,}[.][a-zA-Z]{2,2}[.][a-zA-Z]{2,3}$/;
const password = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,24}$/;
