package main

import (
	"errors"
	"fmt"
	"strings"
)

// User ad
type User struct {
	ID       string
	Username string
	Password string
	Email    string
}

// For this demo, we're storing the user list in memory
// We also have some users predefined.
// In a real application, this list will most likely be fetched
// from a database. Moreover, in production settings, you should
// store passwords securely by salting and hashing them instead
// of using them as we're doing in this demo
var userList = []User{}
var user User

// Check if the username and password combination is valid
func isUserValid(username, password string) bool {

	rows, err := db.Query("SELECT * FROM Users;")
	for rows.Next() {
		err = rows.Scan(&user.ID, &user.Username, &user.Password, &user.Email)
		userList = append(userList, user)
		if err != nil {
			fmt.Print(err.Error())
		}
	}

	defer rows.Close()

	for _, u := range userList {
		if u.Username == username && u.Password == password {
			return true
		}
	}
	return false
}

// Register a new user with the given username and password
// NOTE: For this demo, we
func registerNewUser(username, password, email string) (*User, error) {
	if strings.TrimSpace(password) == "" {
		return nil, errors.New("The password can't be empty")
	} else if !isUsernameAvailable(username) {
		return nil, errors.New("The username isn't available")
	}

	u := User{Username: username, Password: password, Email: email}
	userList = append(userList, u)

	stmt, err := db.Prepare("INSERT INTO Users (Username, Password, Email) VALUES (?, ?, ?);")
	if err != nil {
		fmt.Println(err.Error())
	}

	_, err = stmt.Exec(username, password, email)
	if err != nil {
		fmt.Println(err.Error())
	}

	defer stmt.Close()

	return &u, nil
}

// Check if the supplied username is available
func isUsernameAvailable(username string) bool {
	for _, u := range userList {
		if u.Username == username {
			return false
		}
	}
	return true
}
