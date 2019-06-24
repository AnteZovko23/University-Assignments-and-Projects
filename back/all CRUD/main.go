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

func main() {
	tables := []string{"Computers", "Furniture", "Printers", "Supplies", "Warehouse"}

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

		c.JSON(http.StatusOK, gin.H{

			"All Items": vars.AllItems(c),
			"Total":     len(vars.AllItems(c)),
		})

		vars.ComputerArr, vars.FurnitureArr, vars.PrintersArr, vars.SuppliesArr, vars.WarehouseArr = nil, nil, nil, nil, nil

	})

	/************** ALL ITEMS BASED ON A TABLE *****************/

	// Gets all computers
	router.GET("/computers", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"result": vars.GetTable("Computers", vars.ComputerItem),
			"count":  len(vars.GetTable("Computers", vars.ComputerItem)),
		})

	})

	// Gets all furninture
	router.GET("/furniture", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"result": vars.GetTable("Furniture", vars.FurnitureItem),
			"count":  len(vars.GetTable("Furniture", vars.FurnitureItem)),
		})

	})

	// Gets all printers
	router.GET("/printers", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"result": vars.GetTable("Printers", vars.PrinterItem),
			"count":  len(vars.GetTable("Printers", vars.PrinterItem)),
		})

	})

	// Gets all supplies
	router.GET("/supplies", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"result": vars.GetTable("Supplies", vars.SupplyItem),
			"count":  len(vars.GetTable("Supplies", vars.SupplyItem)),
		})

	})

	// Gets all warehouse items
	router.GET("/warehouse", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"result": vars.GetTable("Warehouse", vars.WarehouseItem),
			"count":  len(vars.GetTable("Warehouse", vars.WarehouseItem)),
		})

	})

	/************** ALL ITEMS BASED ON A FIELD *****************/

	// Get all items with Name
	router.GET("/allNames/:Name", func(c *gin.Context) {

		result2 := vars.GetAllItemsBy("Name", c)

		c.JSON(http.StatusOK, result2)
		vars.ComputerArr, vars.FurnitureArr, vars.PrintersArr, vars.SuppliesArr, vars.WarehouseArr = nil, nil, nil, nil, nil
	})

	// Get all items with Description
	router.GET("/allDescr/:Description", func(c *gin.Context) {

		result2 := vars.GetAllItemsBy("Description", c)

		c.JSON(http.StatusOK, result2)
		vars.ComputerArr, vars.FurnitureArr, vars.PrintersArr, vars.SuppliesArr, vars.WarehouseArr = nil, nil, nil, nil, nil
	})

	// Get all items with Category
	router.GET("/allCat/:Category", func(c *gin.Context) {

		result2 := vars.GetAllItemsBy("Category", c)

		c.JSON(http.StatusOK, result2)
		vars.ComputerArr, vars.FurnitureArr, vars.PrintersArr, vars.SuppliesArr, vars.WarehouseArr = nil, nil, nil, nil, nil
	})

	// Get all items with Barcode
	router.GET("/allBarcodes/:Barcode", func(c *gin.Context) {

		result2 := vars.GetAllItemsBy("Barcode", c)

		c.JSON(http.StatusOK, result2)
		vars.ComputerArr, vars.FurnitureArr, vars.PrintersArr, vars.SuppliesArr, vars.WarehouseArr = nil, nil, nil, nil, nil
	})

	/********************************* POST OPERATIONS *****************************************/

	/************** POST TO SPECIFIC TABLE *****************/

	// Create new Computer Item
	router.POST("/computers", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf(" %s successfully created", vars.NewItem("Computers", c)),
		})

	})

	// Create new Furniture Item
	router.POST("/furniture", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf(" %s successfully created", vars.NewItem("Furniture", c)),
		})

	})

	// Create new Printer Item
	router.POST("/printers", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf(" %s successfully created", vars.NewItem("Printers", c)),
		})

	})

	// Create new Supply Item
	router.POST("/supplies", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf(" %s successfully created", vars.NewItem("Supplies", c)),
		})

	})

	// Create new Warehouse Item
	router.POST("/warehouse", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf(" %s successfully created", vars.NewItem("Warehouse", c)),
		})

	})

	/********************************* UPDATE OPERATIONS *****************************************/

	/************** GLOBAL UPDATE BASED ON FIELD *****************/

	router.PUT("/allNames", func(c *gin.Context) {

		str := "Name"

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf("Successfully updated all items with "+str+" %s", vars.UpdateItemsByField(str, tables, c)),
		})

	})
	router.PUT("/allDescr", func(c *gin.Context) {

		str := "Description"

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf("Successfully updated all items with "+str+" %s", vars.UpdateItemsByField(str, tables, c)),
		})

	})
	router.PUT("/allCat", func(c *gin.Context) {

		str := "Category"

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf("Successfully updated all items with "+str+" %s", vars.UpdateItemsByField(str, tables, c)),
		})

	})

	router.PUT("/allBarcodes", func(c *gin.Context) {

		str := "Barcode"

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf("Successfully updated all items with "+str+" %s", vars.UpdateItemsByField(str, tables, c)),
		})

	})

	/********************************* DELETE OPERATIONS *****************************************/

	/************** DELETE A SPECIFIC ITEM *****************/

	// Delete By Name
	router.DELETE("/allNames", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf("Successfully deleted items with Name: %s", vars.DeleteItemsByField("Name", tables, c)),
		})

	})

	// Delete By Description
	router.DELETE("/allDescr", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf("Successfully deleted items with Description: %s", vars.DeleteItemsByField("Description", tables, c)),
		})

	})

	// Delete By Category
	router.DELETE("/allCat", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf("Successfully deleted items with Category: %s", vars.DeleteItemsByField("Category", tables, c)),
		})

	})

	// Delete By Barcode
	router.DELETE("/allBarcodes", func(c *gin.Context) {

		c.JSON(http.StatusOK, gin.H{
			"message": fmt.Sprintf("Successfully deleted items with Barcode: %s", vars.DeleteItemsByField("Barcode", tables, c)),
		})

	})

	router.Run(":3000")
}
