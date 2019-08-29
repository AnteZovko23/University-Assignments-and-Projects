package main

import (
	"errors"
	"fmt"
	"strings"

	vars "./lib"
)

// User ad
type User struct {
	ID       string
	Username string
	Password string
	Email    string
	Gender   string
	Birthday string
}

var userList = []User{}
var emailList = []User{}
var user User

// Check if the username and password combination is valid
func isUserValid(username, password string) bool {

	rows, err := vars.Db.Query("SELECT * FROM Users;")
	for rows.Next() {
		err = rows.Scan(&user.ID, &user.Username, &user.Password, &user.Email, &user.Gender, &user.Birthday)
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

func isResetValid(email string) bool {

	rows, err := vars.Db.Query("SELECT Email FROM Users;")
	for rows.Next() {
		err = rows.Scan(&user.Email)
		emailList = append(emailList, user)
		if err != nil {
			fmt.Print(err.Error())
		}
	}

	defer rows.Close()

	for _, u := range emailList {
		if u.Email == email {
			return true
		}
	}
	return false
}

func registerNewUser(username, password, email, gender, birthday string) (*User, error) {
	if strings.TrimSpace(password) == "" {
		return nil, errors.New("The password can't be empty")
	} else if !isUsernameAvailable(username) {
		return nil, errors.New("The username isn't available")
	}

	u := User{Username: username, Password: password, Email: email, Gender: gender, Birthday: birthday}
	userList = append(userList, u)

	stmt, err := vars.Db.Prepare("INSERT INTO Users (Username, Password, Email, Gender, Birthday) VALUES (?, ?, ?, ?, ?);")
	if err != nil {
		fmt.Println(err.Error())
	}

	_, err = stmt.Exec(username, password, email, gender, birthday)
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
