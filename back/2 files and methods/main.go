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

		rows, err := vars.Db.Query("select * from Computers;")

		rows2, err2 := vars.Db.Query("select * from Furniture;")

		rows3, err3 := vars.Db.Query("select * from Printers;")

		rows4, err4 := vars.Db.Query("select * from Supplies;")

		rows5, err5 := vars.Db.Query("select * from Warehouse;")

		for rows.Next() {
			err = rows.Scan(&vars.ComputerItem.Name, &vars.ComputerItem.Description, &vars.ComputerItem.Category, &vars.ComputerItem.Barcode)
			vars.ComputerArr = append(vars.ComputerArr, vars.ComputerItem)

			if err != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows.Close()

		for rows2.Next() {
			err2 = rows2.Scan(&vars.FurnitureItem.Name, &vars.FurnitureItem.Description, &vars.FurnitureItem.Category, &vars.FurnitureItem.Barcode)
			vars.FurnitureArr = append(vars.FurnitureArr, vars.FurnitureItem)

			if err2 != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows2.Close()

		for rows3.Next() {
			err3 = rows3.Scan(&vars.PrinterItem.Name, &vars.PrinterItem.Description, &vars.PrinterItem.Category, &vars.PrinterItem.Barcode)
			vars.PrintersArr = append(vars.PrintersArr, vars.PrinterItem)

			if err3 != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows3.Close()

		for rows4.Next() {
			err4 = rows4.Scan(&vars.SupplyItem.Name, &vars.SupplyItem.Description, &vars.SupplyItem.Category, &vars.SupplyItem.Barcode)
			vars.SuppliesArr = append(vars.SuppliesArr, vars.SupplyItem)

			if err4 != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows4.Close()

		for rows5.Next() {
			err5 = rows5.Scan(&vars.WarehouseItem.Name, &vars.WarehouseItem.Description, &vars.WarehouseItem.Category, &vars.WarehouseItem.Barcode)
			vars.WarehouseArr = append(vars.WarehouseArr, vars.WarehouseItem)

			if err5 != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows5.Close()

		c.JSON(http.StatusOK, gin.H{
			"total":   len(vars.ComputerArr) + len(vars.FurnitureArr) + len(vars.PrintersArr) + len(vars.SuppliesArr) + len(vars.WarehouseArr),
			"result":  vars.ComputerArr,
			"count":   len(vars.ComputerArr),
			"result2": vars.FurnitureArr,
			"count2":  len(vars.FurnitureArr),
			"result3": vars.PrintersArr,
			"count3":  len(vars.PrintersArr),
			"result4": vars.SuppliesArr,
			"count4":  len(vars.SuppliesArr),
			"result5": vars.WarehouseArr,
			"count5":  len(vars.WarehouseArr),
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
	router.GET("/allBarcode/:Barcode", func(c *gin.Context) {

		result2 := vars.GetAllItemsBy("Barcode", c)

		c.JSON(http.StatusOK, result2)
		vars.ComputerArr, vars.FurnitureArr, vars.PrintersArr, vars.SuppliesArr, vars.WarehouseArr = nil, nil, nil, nil, nil
	})

	router.Run(":3000")
}
