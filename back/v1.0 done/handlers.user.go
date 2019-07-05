package main

import (
	// "fmt"
	"github.com/gin-gonic/gin"
	"math/rand"
	"net/http"
	"strconv"

	"context"
	"fmt"
	"github.com/mailgun/mailgun-go"
	"time"
)

var keys map[string]string
var key string

func showLoginPage(c *gin.Context) {
	// Call the render function with the name of the template to render
	render(c, gin.H{
		"title": "Login",
	}, "login.html")
}

func showResetPage(c *gin.Context) {
	// Call the render function with the name of the template to render
	render(c, gin.H{
		"title": "Reset",
	}, "resetpass.html")
}

func performLogin(c *gin.Context) {
	// Obtain the POSTed username and password values
	username := c.PostForm("username")
	password := c.PostForm("password")

	// Check if the username/password combination is valid
	if isUserValid(username, password) {
		// If the username/password is valid set the token in a cookie
		token := generateSessionToken()
		c.SetCookie("token", token, 3600, "", "", false, true)
		c.Set("is_logged_in", true)

		render(c, gin.H{
			"title": "Successful Login"}, "login-successful.html")

		keys = make(map[string]string)
		key = ""

	} else {
		// If the username/password combination is invalid,
		// show the error message on the login page
		c.HTML(http.StatusBadRequest, "login.html", gin.H{
			"ErrorTitle":   "Login Failed",
			"ErrorMessage": "Invalid credentials provided"})
	}
}

func performReset(c *gin.Context) {
	// Obtain the POSTed username and password values
	email := c.PostForm("email")

	// Check if the username/password combination is valid
	if isResetValid(email) {

		key = strconv.FormatInt(rand.Int63(), 16)

		keys = make(map[string]string)

		keys[key] = email

		sendSimpleMessage("<domain>", "<api-key", email, key)

		render(c, gin.H{
			"title": "Success"}, "resetWithKey.html")

	} else {
		// If the username/password combination is invalid,
		// show the error message on the login page
		c.HTML(http.StatusBadRequest, "resetpass.html", gin.H{
			"ErrorTitle":   "Reset Failed",
			"ErrorMessage": "Invalid credentials provided"})
	}
}

func resetWithKey(c *gin.Context) {
	// Obtain the POSTed username and password values

	key := c.PostForm("key")
	password := c.PostForm("password")

	// Check if the username/password combination is valid
	if isResetValid(keys[key]) {

		// // If the username/password is valid set the token in a cookie
		stmt, err := db.Prepare("update Users set Password = ? where Email = ?;")
		if err != nil {
			fmt.Println(err.Error())
		}

		_, err = stmt.Exec(password, keys[key])
		if err != nil {
			fmt.Println(err.Error())
		}

		render(c, gin.H{
			"title": "Successful Reset"}, "reset-successful.html")
		keys = make(map[string]string)
		key = ""

	} else {
		// If the username/password combination is invalid,
		// show the error message on the login page
		c.HTML(http.StatusBadRequest, "resetWithKey.html", gin.H{
			"ErrorTitle":   "Reset Failed",
			"ErrorMessage": "Invalid credentials provided"})

	}
}

func generateSessionToken() string {
	// We're using a random 16 character string as the session token
	// This is NOT a secure way of generating session tokens
	// DO NOT USE THIS IN PRODUCTION
	return strconv.FormatInt(rand.Int63(), 16)
}

func logout(c *gin.Context) {
	// Clear the cookie
	c.SetCookie("token", "", -1, "", "", false, true)

	render(c, gin.H{
		"title": "Successful logout"}, "logout-successful.html")

	// Redirect to the home page
	// c.Redirect(http.StatusTemporaryRedirect, "/")
}

func showRegistrationPage(c *gin.Context) {
	// Call the render function with the name of the template to render
	render(c, gin.H{
		"title": "Register"}, "register.html")
}

func register(c *gin.Context) {
	// Obtain the POSTed username and password values
	username := c.PostForm("username")
	password := c.PostForm("password")
	email := c.PostForm("email")

	if _, err := registerNewUser(username, password, email); err == nil {
		// If the user is created, set the token in a cookie and log the user in
		token := generateSessionToken()
		c.SetCookie("token", token, 3600, "", "", false, true)
		c.Set("is_logged_in", true)

		render(c, gin.H{
			"title": "Successful registration & Login"}, "login-successful.html")

	} else {
		// If the username/password combination is invalid,
		// show the error message on the login page
		c.HTML(http.StatusBadRequest, "register.html", gin.H{
			"ErrorTitle":   "Registration Failed",
			"ErrorMessage": err.Error()})

	}
}
func sendSimpleMessage(domain, apiKey, email, key string) (string, error) {
	mg := mailgun.NewMailgun(domain, apiKey)
	m := mg.NewMessage(
		"Mailgun Sandbox <domain>",
		"Hello, ",
		"This is your key "+key,
		email,
	)
	ctx, cancel := context.WithTimeout(context.Background(), time.Second*30)
	defer cancel()

	_, id, err := mg.Send(ctx, m)
	return id, err
}
