package main

import (
	vars "./lib"
	// "bytes"
	"database/sql"
	"fmt"
	"github.com/gin-gonic/gin"
	_ "github.com/go-sql-driver/mysql"
	"net/http"
)

// TODO Delete only specific table, update, post
// Handle err
// Remove gets DONE
// Messages JSON
// Paginacija

func main() {
	tables := []string{"Computers", "Furniture", "Printers", "Supplies", "Warehouse"}

	// m := make(map[string]struct {
	// 	Name        string
	// 	Description string
	// 	Category    string
	// 	Barcode     int
	// })

	m := map[string]struct {
		Name        string
		Description string
		Category    string
		Barcode     int
	}{"Computers": vars.ComputerItem, "Furniture": vars.FurnitureItem}

	vars.Db, vars.Err = sql.Open("mysql", "root:1111@tcp(127.0.0.1:3306)/testAPI")
	if vars.Err != nil {
		fmt.Print(vars.Err.Error())
	}

	defer vars.Db.Close()

	vars.Err = vars.Db.Ping()
	if vars.Err != nil {
		fmt.Print(vars.Err.Error())
	}

	router := gin.Default()

	/********************************** GET OPERATIONS ****************************************/

	/************** ALL ITEMS *****************/

	// Gets all items
	router.GET("/all", func(c *gin.Context) {

		table := c.DefaultQuery("table", "")
		field := c.DefaultQuery("field", "")
		item := c.DefaultQuery("item", "")
		obj := m[table]

		c.JSON(http.StatusOK, gin.H{

			"All Items": vars.AllItems(field, item, table, c, obj),
		})

		vars.ComputerArr, vars.FurnitureArr, vars.PrintersArr, vars.SuppliesArr, vars.WarehouseArr = nil, nil, nil, nil, nil
		vars.Result = nil
		vars.Thingslist = nil
		table, field, item = "", "", ""

	})

	/********************************* POST OPERATIONS *****************************************/

	/************** POST TO SPECIFIC TABLE *****************/

	// Create new Item
	router.POST("/all", func(c *gin.Context) {

		table := c.DefaultQuery("table", "")

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf(" %s successfully created", vars.NewItem(table, c)),
		})

	})

	/********************************* UPDATE OPERATIONS *****************************************/

	/************** GLOBAL UPDATE BASED ON FIELD *****************/

	router.PUT("/all", func(c *gin.Context) {

		field := c.DefaultQuery("field", "")
		item := c.DefaultQuery("item", "")

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf("Successfully updated all items with "+field+" %s", vars.UpdateItemsByField(field, item, tables, c)),
		})

	})

	/********************************* DELETE OPERATIONS *****************************************/

	/************** DELETE A SPECIFIC ITEM *****************/

	// Delete By Name
	router.DELETE("/all", func(c *gin.Context) {

		field := c.DefaultQuery("field", "")
		item := c.DefaultQuery("item", "")

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf("Successfully deleted items with Name: %s", vars.DeleteItemsByField(field, item, tables, c)),
		})

	})

	router.Run(":3000")
}
