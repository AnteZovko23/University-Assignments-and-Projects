package main

import (
	//"bytes"
	"database/sql"
	"fmt"
	"net/http"

	// "strconv"

	// "github.com/biezhi/gorm-paginator/pagination"
	"github.com/gin-gonic/gin"
	_ "github.com/go-sql-driver/mysql"
	// "github.com/jinzhu/gorm"
)

var db *sql.DB
var err error

func main() {
	db, err := sql.Open("mysql", "root:1111@tcp(127.0.0.1:3306)/gotest")
	if err != nil {
		fmt.Print(err.Error())
	}

	defer db.Close()

	err = db.Ping()
	if err != nil {
		fmt.Print(err.Error())
	}

	type Computers struct {
		Name        string
		Description string
		Category    string
		Barcode     int
	}

	var (
		computer  Computers
		computers []Computers
	)

	// pagination.Paging(&pagination.Param{
	// 	DB:      db,
	// 	Page:    1,
	// 	Limit:   3,
	// 	OrderBy: []string{"Barcode"},
	// 	ShowSQL: true,
	// }, &computers)

	router := gin.Default()

	// Gets all computers
	router.GET("/computers/:test", func(c *gin.Context) {

		rows, err := db.Query("select * from computers;")

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

	// TODO
	// authorized := router.Group("/", gin.BasicAuth(gin.Accounts{
	// 	"user1": "test",
	// 	"user2": "test2",
	// }))

	// authorized.GET("/test", func(c *gin.Context) {
	// 	c.JSON(http.StatusOK, gin.H{
	// 		"test": "TEST123456",
	// 	})
	// })

	// router.GET("/computers/:Name", func(c *gin.Context) {

	// 	queryVar := c.Param("Name")

	// 	rows, err := db.Query("select * from computers where Name = ?;", queryVar)
	// 	if err != nil {
	// 		fmt.Println(err.Error())
	// 	}

	// 	for rows.Next() {
	// 		err = rows.Scan(&computer.Name, &computer.Description, &computer.Category, &computer.Barcode)
	// 		computers = append(computers, computer)
	// 	}

	// 	c.JSON(http.StatusOK, computers)
	// 	computers = nil

	// })
	router.Run(":8000")
}
