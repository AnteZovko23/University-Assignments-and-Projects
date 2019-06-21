package main

import (
	vars "./lib"
	"database/sql"
	"fmt"
	"github.com/gin-gonic/gin"
	"net/http"

	_ "github.com/go-sql-driver/mysql"
)

func main() {
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
	router.GET("/allBarcode/:Barcode", func(c *gin.Context) {

		result2 := vars.GetAllItemsBy("Barcode", c)

		c.JSON(http.StatusOK, result2)
		vars.ComputerArr, vars.FurnitureArr, vars.PrintersArr, vars.SuppliesArr, vars.WarehouseArr = nil, nil, nil, nil, nil
	})

	router.Run(":3000")
}
