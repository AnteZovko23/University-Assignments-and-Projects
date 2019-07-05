package main

import (
	// "fmt"
	"github.com/gin-gonic/gin"
	"math/rand"
	"net/http"
	"strconv"

	vars "./lib"
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

	username := c.PostForm("username")
	password := c.PostForm("password")

	if isUserValid(username, password) {

		token := generateSessionToken()
		c.SetCookie("token", token, 3600, "", "", false, true)
		c.Set("is_logged_in", true)

		render(c, gin.H{
			"title": "Successful Login"}, "login-successful.html")

		keys = make(map[string]string)
		key = ""

	} else {

		c.HTML(http.StatusBadRequest, "login.html", gin.H{
			"ErrorTitle":   "Login Failed",
			"ErrorMessage": "Invalid credentials provided"})
	}
}

func performReset(c *gin.Context) {

	email := c.PostForm("email")

	if isResetValid(email) {

		key = strconv.FormatInt(rand.Int63(), 16)

		keys = make(map[string]string)

		keys[key] = email

		sendSimpleMessage("<insert-domain>", "<insert-key>", email, key)

		render(c, gin.H{
			"title": "Success"}, "resetWithKey.html")

	} else {

		c.HTML(http.StatusBadRequest, "resetpass.html", gin.H{
			"ErrorTitle":   "Reset Failed",
			"ErrorMessage": "Invalid credentials provided"})
	}
}

func resetWithKey(c *gin.Context) {

	key := c.PostForm("key")
	password := c.PostForm("password")

	if isResetValid(keys[key]) {

		stmt, err := vars.Db.Prepare("update Users set Password = ? where Email = ?;")
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

		c.HTML(http.StatusBadRequest, "resetWithKey.html", gin.H{
			"ErrorTitle":   "Reset Failed",
			"ErrorMessage": "Invalid credentials provided"})

	}
}

func generateSessionToken() string {

	return strconv.FormatInt(rand.Int63(), 16)
}

func logout(c *gin.Context) {
	// Clear the cookie
	c.SetCookie("token", "", -1, "", "", false, true)

	render(c, gin.H{
		"title": "Successful logout"}, "logout-successful.html")

}

func showRegistrationPage(c *gin.Context) {
	// Call the render function with the name of the template to render
	render(c, gin.H{
		"title": "Register"}, "register.html")
}

func register(c *gin.Context) {

	username := c.PostForm("username")
	password := c.PostForm("password")
	email := c.PostForm("email")

	if _, err := registerNewUser(username, password, email); err == nil {

		token := generateSessionToken()
		c.SetCookie("token", token, 3600, "", "", false, true)
		c.Set("is_logged_in", true)

		render(c, gin.H{
			"title": "Successful registration & Login"}, "login-successful.html")

	} else {

		c.HTML(http.StatusBadRequest, "register.html", gin.H{
			"ErrorTitle":   "Registration Failed",
			"ErrorMessage": err.Error()})

	}
}
func sendSimpleMessage(domain, apiKey, email, key string) (string, error) {
	mg := mailgun.NewMailgun(domain, apiKey)
	m := mg.NewMessage(
		"Mailgun Sandbox <postmaster@sandbox82c8c3c7479f4ee3bea44749c99a19c8.mailgun.org>",
		"Hello, ",
		"This is your key "+key,
		email,
	)
	ctx, cancel := context.WithTimeout(context.Background(), time.Second*30)
	defer cancel()

	_, id, err := mg.Send(ctx, m)
	return id, err
}
