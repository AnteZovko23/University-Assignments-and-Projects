package main

import (
	// "fmt"
	"math/rand"
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"

	"context"
	"fmt"
	"time"

	vars "./lib"
	"github.com/mailgun/mailgun-go"
)

var keys map[string]string
var key string

func showLoginPage(c *gin.Context) {
	// Call the Render function with the name of the template to Render
	Render(c, gin.H{
		"title": "Login",
	}, "loginImproved.html")
}

func showTest(c *gin.Context) {
	// Call the Render function with the name of the template to Render
	Render(c, gin.H{
		"title": "Home",
	}, "loginImproved.html")
}
func showHomePage(c *gin.Context) {
	// Call the Render function with the name of the template to Render
	Render(c, gin.H{
		"title": "Home",
	}, "index.html")
}

func searchResult(c *gin.Context) {
	// Call the Render function with the name of the template to Render
	Render(c, gin.H{
		"title": "Home",
	}, "searchResults.html")
}

func showResetPage(c *gin.Context) {
	// Call the Render function with the name of the template to Render
	Render(c, gin.H{
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

		c.Redirect(http.StatusTemporaryRedirect, "/users/homepage")

		keys = make(map[string]string)
		key = ""

	} else {

		c.HTML(http.StatusBadRequest, "loginImproved.html", gin.H{
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

		sendSimpleMessage("api", "key", email, key)

		Render(c, gin.H{
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

		Render(c, gin.H{
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

	c.Redirect(http.StatusTemporaryRedirect, "/users/login")

}

func showRegistrationPage(c *gin.Context) {
	// Call the Render function with the name of the template to Render
	Render(c, gin.H{
		"title": "Register"}, "registerImproved.html")
}

func register(c *gin.Context) {

	username := c.PostForm("username")
	password := c.PostForm("password")
	email := c.PostForm("email")

	if _, err := registerNewUser(username, password, email); err == nil {

		token := generateSessionToken()
		c.SetCookie("token", token, 3600, "", "", false, true)
		c.Set("is_logged_in", true)

		c.Redirect(http.StatusTemporaryRedirect, "/users/homepage")

	} else {

		c.HTML(http.StatusBadRequest, "registerImproved.html", gin.H{
			"ErrorTitle":   "Registration Failed",
			"ErrorMessage": err.Error()})

	}
}
func sendSimpleMessage(domain, apiKey, email, key string) (string, error) {
	mg := mailgun.NewMailgun(domain, apiKey)
	m := mg.NewMessage(
		"Mailgun Sandbox <postmaster@api>",
		"Hello, ",
		"This is your key "+key,
		email,
	)
	// zaz934327@gmail.com
	ctx, cancel := context.WithTimeout(context.Background(), time.Second*30)
	defer cancel()

	_, id, err := mg.Send(ctx, m)
	return id, err
}
