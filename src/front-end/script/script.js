// DOM elements
const userForm = document.getElementById('userForm');
const userList = document.getElementById('userList');
userForm.dataset.mode = 'create';
// Event listener for form submission
userForm.addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission

    const user = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        age: parseInt(document.getElementById('age').value),
        occupation: document.getElementById('occupation').value
    };

    if (userForm.dataset.mode === 'create') {
        // Create user
        createUser(user);
    } else if (userForm.dataset.mode === 'update') {
        // Update user
        const userId = userForm.dataset.userId;
        updateUser(userId, user);
    }

    userForm.reset(); // Clear form fields
});

// Create User
function createUser(user) {
    fetch('http://localhost:8080/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then(response => response.text())
        .then(data => {
            console.log('Response:', data);
            // Refresh user list
            getUsers();
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occurred during the request
        });
}

// Update User
function updateUser(userId, user) {
    fetch(`http://localhost:8080/update/${userId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then(response => response.text())
        .then(data => {
            console.log('Response:', data);
            // Refresh user list
            getUsers();
            // Reset form to create mode
            userForm.dataset.mode = 'create';
            delete userForm.dataset.userId;
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occurred during the request
        });
}
// Delete User
function deleteUser(userId) {
    fetch(`http://localhost:8080/delete/${userId}`, {
        method: 'DELETE'
    })
        .then(response => response.text())
        .then(data => {
            console.log('Response:', data);
            // Refresh user list
            getUsers();
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occurred during the request
        });
}

// Get Users
function getUsers() {
    fetch('http://localhost:8080/users')
        .then(response => response.json())
        .then(data => {
            console.log('Users:', data);
            displayUsers(data);
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occurred during the request
        });
}

// Display Users
function displayUsers(users) {
    userList.innerHTML = ''; // Clear user list

    users.forEach(user => {
        const listItem = document.createElement('li');
        listItem.textContent = `${user.firstName} ${user.lastName} (Age: ${user.age}, Occupation: ${user.occupation})`;

        // Edit button
        const editButton = document.createElement('button');
        editButton.textContent = 'Edit';
        editButton.addEventListener('click', function() {
            editUser(user);
        });
        listItem.appendChild(editButton);

        // Delete button
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', function() {
            deleteUser(user.id);
        });
        listItem.appendChild(deleteButton);

        userList.appendChild(listItem);
    });
}
function editUser(user) {
    userForm.dataset.mode = 'update';
    userForm.dataset.userId = user.id;
    console.log(userForm.dataset.mode);
    document.getElementById('firstName').value = user.firstName;
    document.getElementById('lastName').value = user.lastName;
    document.getElementById('age').value = user.age;
    document.getElementById('occupation').value = user.occupation;
}


    // Initial setup
    getUsers();
