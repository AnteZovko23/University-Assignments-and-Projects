package main

import (
	vars "./lib"
	// "bytes"
	"database/sql"
	"fmt"
	"github.com/gin-gonic/gin"
	_ "github.com/go-sql-driver/mysql"
	"net/http"
	"strconv"
)

// Paginacija

func main() {
	tables := []string{"Computers", "Furniture", "Printers", "Supplies", "Warehouse"}

	m := map[string]struct {
		Name        string
		Description string
		Category    string
		Barcode     int
	}{"Computers": vars.ComputerItem, "Furniture": vars.FurnitureItem, "Printers": vars.PrinterItem, "Supplies": vars.SupplyItem, "Warehouse": vars.WarehouseItem}

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
		limit2 := c.DefaultQuery("limit", "")
		limit, err := strconv.Atoi(limit2)
		page2 := c.DefaultQuery("page", "1")
		page, err := strconv.Atoi(page2)
		if err != nil {
			fmt.Println(err.Error())
		}
		if page > 0 {
			page--
		} else if page < 0 {
			page = 0
		}
		offset2 := limit * page
		offset := strconv.Itoa(offset2)
		c.JSON(http.StatusOK, gin.H{

			"All Items": vars.AllItems(offset, limit2, field, item, table, c, obj),
		})

		vars.ComputerArr, vars.FurnitureArr, vars.PrintersArr, vars.SuppliesArr, vars.WarehouseArr = nil, nil, nil, nil, nil
		vars.Result = nil
		vars.Thingslist = nil
		table, field, item, limit2, offset = "", "", "", "", ""

	})

	/********************************* POST OPERATIONS *****************************************/

	/************** POST TO SPECIFIC TABLE *****************/

	// Create new Item
	router.POST("/all", func(c *gin.Context) {

		table := c.DefaultQuery("table", "")

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf(" %s successfully created", vars.NewItem(table, c)),
		})

		vars.ComputerArr, vars.FurnitureArr, vars.PrintersArr, vars.SuppliesArr, vars.WarehouseArr = nil, nil, nil, nil, nil
		vars.Result = nil
		table = ""

	})

	/********************************* UPDATE OPERATIONS *****************************************/

	/************** GLOBAL UPDATE BASED ON FIELD *****************/

	router.PUT("/all", func(c *gin.Context) {

		table := c.DefaultQuery("table", "")
		field := c.DefaultQuery("field", "")
		item := c.DefaultQuery("item", "")

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf("Successfully updated all items with "+field+" %s", vars.UpdateItemsByField(table, field, item, tables, c)),
		})

		vars.ComputerArr, vars.FurnitureArr, vars.PrintersArr, vars.SuppliesArr, vars.WarehouseArr = nil, nil, nil, nil, nil
		table, field, item = "", "", ""

	})

	/********************************* DELETE OPERATIONS *****************************************/

	/************** DELETE A SPECIFIC ITEM *****************/

	// Delete By Name
	router.DELETE("/all", func(c *gin.Context) {

		table := c.DefaultQuery("table", "")
		field := c.DefaultQuery("field", "")
		item := c.DefaultQuery("item", "")

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf("Successfully deleted items with Name: %s", vars.DeleteItemsByField(table, field, item, tables, c)),
		})

		vars.ComputerArr, vars.FurnitureArr, vars.PrintersArr, vars.SuppliesArr, vars.WarehouseArr = nil, nil, nil, nil, nil
		table, field, item = "", "", ""

	})

	router.Run(":3000")
}
