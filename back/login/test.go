package main

import (
	"database/sql"
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
	_ "github.com/go-sql-driver/mysql"
)

var db *sql.DB
var err error

// Computers ad
type Computers struct {
	Name        string
	Description string
	Category    string
	Barcode     int
}

var (
	computer  Computers
	computers []Computers

	username  User
	usernames []User

	userpass   User
	userpasses []User
)

func main() {

	// gin.SetMode(gin.ReleaseMode)

	db, err = sql.Open("mysql", "root:1111@tcp(127.0.0.1:3306)/gotest")
	if err != nil {
		fmt.Print(err.Error())
	}

	defer db.Close()

	err = db.Ping()
	if err != nil {
		fmt.Print(err.Error())
	}

	router := gin.Default()

	router.Use(setUserStatus())

	router.LoadHTMLGlob("templates/*")

	users := router.Group("/users")

	// users.POST("/register", func(c *gin.Context) {
	// 	// username := c.PostForm("Username")
	// 	// password := c.PostForm("Password")
	// 	// email := c.PostForm("Email")

	// 	// stmt, err := db.Prepare("INSERT INTO Users (Username, Password, Email) VALUES (?, ?, ?);")
	// 	// if err != nil {
	// 	// 	fmt.Println(err.Error())
	// 	// }

	// 	// _, err = stmt.Exec(username, password, email)
	// 	// if err != nil {
	// 	// 	fmt.Println(err.Error())
	// 	// }

	// 	// defer stmt.Close()

	// })

	users.GET("/register", ensureNotLoggedIn(), showRegistrationPage)

	// Handle POST requests at /u/register
	// Ensure that the user is not logged in by using the middleware

	users.POST("/register", ensureNotLoggedIn(), register)

	users.POST("/login", ensureNotLoggedIn(), performLogin)

	users.GET("/login", ensureNotLoggedIn(), showLoginPage)

	users.GET("/logout", ensureLoggedIn(), logout)

	// Gets all computers
	router.GET("/computers", ensureLoggedIn(), func(c *gin.Context) {

		rows, err := db.Query("SELECT * FROM computers;")
		for rows.Next() {
			err = rows.Scan(&computer.Name, &computer.Description, &computer.Category, &computer.Barcode)
			computers = append(computers, computer)
			if err != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows.Close()

		c.JSON(http.StatusOK, gin.H{
			"result": computers,
			"count":  len(computers),
		})

		computers = nil

	})

	router.Run(":8000")
}

// Render one of HTML, JSON or CSV based on the 'Accept' header of the request
// If the header doesn't specify this, HTML is rendered, provided that
// the template name is present
func render(c *gin.Context, data gin.H, templateName string) {
	loggedInInterface, _ := c.Get("is_logged_in")
	data["is_logged_in"] = loggedInInterface.(bool)

	switch c.Request.Header.Get("Accept") {
	case "application/json":
		// Respond with JSON
		c.JSON(http.StatusOK, data["payload"])
	case "application/xml":
		// Respond with XML
		c.XML(http.StatusOK, data["payload"])
	default:
		// Respond with HTML
		c.HTML(http.StatusOK, templateName, data)
	}
}
