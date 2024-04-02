
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
        age: parseInt(document.getElementById('age').value),
        occupation: document.getElementById('occupation').value
    };
    createUser(user);
});

// Create User
function createUser(user) {
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
            console.log('Response:', data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
        userForm.reset(); // Clear form fields
        window.location.href = "users.html";

}
