// users.js
const path_to_backend = "http://localhost:8080";

// Get Users
function getUser() {
    fetch(path_to_backend + '/users', {
        headers: {
            'Permissions-Policy': 'interest-cohort=()'
        }})
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
    const userList = document.getElementById('userList');
    userList.innerHTML = ''; // Clear user list

    // Create table element
    const table = document.createElement('table');
    table.classList.add('user-table');

    // Create table header row
    const headerRow = document.createElement('tr');
    const headers = ['First Name', 'Last Name', 'username', 'password', 'role','actions'];

    headers.forEach(headerText => {
        const headerCell = document.createElement('th');
        headerCell.textContent = headerText;
        headerRow.appendChild(headerCell);
    });

    table.appendChild(headerRow);

    // Create table rows for each user
    users.forEach(user => {
        const row = document.createElement('tr');

        // Create table cells for user data
        const firstNameCell = document.createElement('td');
        firstNameCell.textContent = user.firstName;
        row.appendChild(firstNameCell);

        const lastNameCell = document.createElement('td');
        lastNameCell.textContent = user.lastName;
        row.appendChild(lastNameCell);

        const usernameCell = document.createElement('td');
        usernameCell.textContent = user.username;
        row.appendChild(usernameCell);

        const passwordCell = document.createElement('td');
        passwordCell.textContent = user.password;
        row.appendChild(passwordCell);

        const roleCell = document.createElement('td');
        roleCell.textContent = user.role;
        row.appendChild(roleCell);

        // Create table cell for actions (edit and delete buttons)
        const actionsCell = document.createElement('td');

        // Edit button
        const editButton = document.createElement('button');
        editButton.textContent = 'Edit';
        editButton.classList.add('edit');
        editButton.addEventListener('click', function () {
            editUser(user);
        });
        actionsCell.appendChild(editButton);

        // Delete button
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', function () {
            deleteUser(user.id);
        });
        actionsCell.appendChild(deleteButton);

        row.appendChild(actionsCell);

        table.appendChild(row);
    });

    userList.appendChild(table);

}

// Edit User
function editUser(user) {
    const userFormContainer = document.getElementById('updatingContainer');
    userFormContainer.innerHTML = ''; // Clear user form container
    const heading = document.createElement('h3');
    heading.textContent = 'Update User';

    const userForm = document.createElement('form');

    // Create input fields for first name, last name, age, and occupation
    const firstNameInput = document.createElement('input');
    firstNameInput.type = 'text';
    firstNameInput.value = user.firstName;
    userForm.appendChild(firstNameInput);

    const lastNameInput = document.createElement('input');
    lastNameInput.type = 'text';
    lastNameInput.value = user.lastName;
    userForm.appendChild(lastNameInput);

    const usernameInput = document.createElement('input');
    usernameInput.type = 'text';
    usernameInput.value = user.username;
    userForm.appendChild(usernameInput);

    const passwordInput = document.createElement('input');
    passwordInput.type = 'password';
    passwordInput.value = user.password;
    userForm.appendChild(passwordInput);
    const roleInput = document.createElement('input');
    roleInput.type = 'text';
    roleInput.value = user.role;
    userForm.appendChild(roleInput);
    // Create submit button
    const submitButton = document.createElement('button');
    submitButton.type = 'submit';
    submitButton.textContent = 'Save';
    userForm.appendChild(submitButton);

    // Add event listener to handle form submission
    userForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent form submission

        // Get updated values from form fields
        const updatedUser = {
            id: user.id,
            firstName: firstNameInput.value,
            lastName: lastNameInput.value,
            username: usernameInput.value,
            password: passwordInput.value,
            role: roleInput.value
        };

        // Call the updateUser function to update the user in the user list
        updateUser(updatedUser);

        // Clear the user form container
        userFormContainer.innerHTML = '';
    });

    // Append the heading and user form to the user form container
    userFormContainer.appendChild(heading);
    userFormContainer.appendChild(userForm);
}

// Delete User
function deleteUser(userId) {
    fetch(path_to_backend + `/delete/${userId}`, {
        method: 'DELETE',
        headers: {
            'Permissions-Policy': 'interest-cohort=()'
        }
    })
        .then(response => response.text())
        .then(data => {
            console.log('User deleted:', data);
            getUser(); // Refresh the user list after deletion
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occurred during the request
        });
}

// Update User
function updateUser(updatedUser) {
    fetch(path_to_backend + `/update/${updatedUser.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json','Permissions-Policy': 'interest-cohort=()'

        },
        body: JSON.stringify(updatedUser)
    })
        .then(response => response.text())
        .then(data => {
            console.log('User updated:', data);
            getUser(); // Refresh the user list after update
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occurred during the request
        });
}

// Initial setup
getUser();