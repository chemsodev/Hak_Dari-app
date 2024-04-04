
// input.js

// DOM elements
const userForm = document.getElementById('userForm');
const path_to_backend = "https://project-poo.onrender.com";

// Event listener for form submission
userForm.addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission
    const user = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        username: document.getElementById("username").value,
        password: document.getElementById('password').value,
        role: document.getElementById('role').value
    };
    createUser(user);
});

// Create User
function createUser(user) {
    if (document.getElementById('password').value!==document.getElementById('password2').value)
    {
        alert("confirmed password must match password " );
        document.getElementById('password').value="";
        document.getElementById('password2').value="";
    }else {
        fetch(path_to_backend + '/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Permissions-Policy': 'interest-cohort=()'
            },
            body: JSON.stringify(user)
        })
            .then(response => response.text())
            .then(data => {
                if (data === "User saved.") {
                } else {
                    alert(data);
                }
                console.log('Response:', data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
        userForm.reset(); // Clear form fields
    }
}
