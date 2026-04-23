function login() {
   
    const username = document.getElementById("loginUsername").value.trim();
    const password = document.getElementById("loginPassword").value.trim();

    if (username === "" || password === "") {
        alert("All fields are required!");
        return false;
    }

    console.log(`Login clicked. Username: ${username}, Password: ${password}`);
    
}

function register() {
    const name = document.getElementById("registerName").value.trim();
    const email = document.getElementById("registerEmail").value.trim();
    const username = document.getElementById("registerUsername").value.trim();
    const password = document.getElementById("registerPassword").value.trim();

    // Empty field check
    if (name === "" || email === "" || username === "" || password === "") {
        alert("All fields are mandatory!");
        return false;
    }

    // Email validation
    const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
    if (!email.match(emailPattern)) {
        alert("Enter a valid email address!");
        return false;
    }

    // Username validation (no special characters)
    const usernamePattern = /^[a-zA-Z0-9]+$/;
    if (!username.match(usernamePattern)) {
        alert("Username should not contain special characters!");
        return false;
    }

    // Password validation
    const passwordPattern = /^(?=.*[A-Z])(?=.*[0-9]).{8,}$/;
    if (!password.match(passwordPattern)) {
        alert("Password must be at least 8 characters long, include one uppercase letter and one number.");
        return false;
    }

    console.log(`Register clicked. Name: ${name}, Email: ${email}, Username: ${username}, Password: ${password}`);
}
module.exports = { login, register };