package main

import (
	//"bytes"
	"database/sql"
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
	_ "github.com/go-sql-driver/mysql"
)

var db *sql.DB
var err error

// Computers Table
type Computers struct {
	Name        string
	Description string
	Category    string
	Barcode     int
}

// Furniture Table
type Furniture struct {
	Name        string
	Description string
	Category    string
	Barcode     int
}

// Printers Table
type Printers struct {
	Name        string
	Description string
	Category    string
	Barcode     int
}

// Supplies Table
type Supplies struct {
	Name        string
	Description string
	Category    string
	Barcode     int
}

// Warehouse Table
type Warehouse struct {
	Name        string
	Description string
	Category    string
	Barcode     int
}

var (
	computer  Computers
	computers []Computers

	furniture    Furniture
	allFurniture []Furniture

	printer  Printers
	printers []Printers

	supplyItem Supplies
	supplies   []Supplies

	warehouseItem Warehouse
	warehouse     []Warehouse

	result gin.H
)

func main() {
	db, err = sql.Open("mysql", "root:1111@tcp(127.0.0.1:3306)/testAPI")
	if err != nil {
		fmt.Print(err.Error())
	}

	defer db.Close()

	err = db.Ping()
	if err != nil {
		fmt.Print(err.Error())
	}

	router := gin.Default()

	// Gets all items
	router.GET("/all", func(c *gin.Context) {

		rows, err := db.Query("select * from Computers;")

		rows2, err2 := db.Query("select * from Furniture;")

		rows3, err3 := db.Query("select * from Printers;")

		rows4, err4 := db.Query("select * from Supplies;")

		rows5, err5 := db.Query("select * from Warehouse;")

		for rows.Next() {
			err = rows.Scan(&computer.Name, &computer.Description, &computer.Category, &computer.Barcode)
			computers = append(computers, computer)

			if err != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows.Close()

		for rows2.Next() {
			err2 = rows2.Scan(&furniture.Name, &furniture.Description, &furniture.Category, &furniture.Barcode)
			allFurniture = append(allFurniture, furniture)

			if err2 != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows2.Close()

		for rows3.Next() {
			err3 = rows3.Scan(&printer.Name, &printer.Description, &printer.Category, &printer.Barcode)
			printers = append(printers, printer)

			if err3 != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows3.Close()

		for rows4.Next() {
			err4 = rows4.Scan(&supplyItem.Name, &supplyItem.Description, &supplyItem.Category, &supplyItem.Barcode)
			supplies = append(supplies, supplyItem)

			if err4 != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows4.Close()

		for rows5.Next() {
			err5 = rows5.Scan(&warehouseItem.Name, &warehouseItem.Description, &warehouseItem.Category, &warehouseItem.Barcode)
			warehouse = append(warehouse, warehouseItem)

			if err5 != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows5.Close()

		c.JSON(http.StatusOK, gin.H{
			"total":   len(computers) + len(allFurniture) + len(printers) + len(supplies) + len(warehouse),
			"result":  computers,
			"count":   len(computers),
			"result2": allFurniture,
			"count2":  len(allFurniture),
			"result3": printers,
			"count3":  len(printers),
			"result4": supplies,
			"count4":  len(supplies),
			"result5": warehouse,
			"count5":  len(warehouse),
		})

		computers, allFurniture, printers, supplies, warehouse = nil, nil, nil, nil, nil

	})

	// Gets all computers
	router.GET("/computers", func(c *gin.Context) {

		rows, err := db.Query("select * from Computers;")

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

	// Gets all furninture
	router.GET("/furniture", func(c *gin.Context) {

		rows, err := db.Query("select * from Furniture;")

		for rows.Next() {
			err = rows.Scan(&furniture.Name, &furniture.Description, &furniture.Category, &furniture.Barcode)
			allFurniture = append(allFurniture, furniture)

			if err != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows.Close()

		c.JSON(http.StatusOK, gin.H{
			"result": allFurniture,
			"count":  len(allFurniture),
		})

		allFurniture = nil

	})

	// Gets all printers
	router.GET("/printers", func(c *gin.Context) {

		rows, err := db.Query("select * from Printers;")
		if err != nil {
			fmt.Print(err.Error())
		}

		for rows.Next() {

			err = rows.Scan(&printer.Name, &printer.Description, &printer.Category, &printer.Barcode)
			printers = append(printers, printer)

			if err != nil {
				fmt.Print(err.Error())
			}

		}

		defer rows.Close()

		c.JSON(http.StatusOK, gin.H{
			"result": printers,
			"count":  len(printers),
		})

		printers = nil

	})

	// Gets all supplies
	router.GET("/supplies", func(c *gin.Context) {

		rows, err := db.Query("select * from Supplies;")
		if err != nil {
			fmt.Print(err.Error())
		}

		for rows.Next() {
			err = rows.Scan(&supplyItem.Name, &supplyItem.Description, &supplyItem.Category, &supplyItem.Barcode)
			supplies = append(supplies, supplyItem)
			if err != nil {
				fmt.Print(err.Error())
			}

		}

		defer rows.Close()

		c.JSON(http.StatusOK, gin.H{
			"result": supplies,
			"count":  len(supplies),
		})

		supplies = nil

	})

	// Gets all warehouse items
	router.GET("/warehouse", func(c *gin.Context) {

		rows, err := db.Query("select * from Warehouse;")
		if err != nil {
			fmt.Print(err.Error())
		}

		for rows.Next() {
			err = rows.Scan(&warehouseItem.Name, &warehouseItem.Description, &warehouseItem.Category, &warehouseItem.Barcode)
			warehouse = append(warehouse, warehouseItem)
			if err != nil {
				fmt.Print(err.Error())
			}
		}

		defer rows.Close()

		c.JSON(http.StatusOK, gin.H{
			"result": warehouse,
			"count":  len(warehouse),
		})

		warehouse = nil

	})

	// Get all items with Name
	router.GET("/allNames/:Name", func(c *gin.Context) {

		result2 := test("Name", c)

		c.JSON(http.StatusOK, result2)
		computers, allFurniture, printers, supplies, warehouse = nil, nil, nil, nil, nil
	})

	// Get items with Barcode
	router.GET("/allBarcodes/:Barcode", func(c *gin.Context) {

		barcode := c.Param("Barcode")
		row := db.QueryRow("select * from Computers where Barcode = ?;", barcode)
		row2 := db.QueryRow("select * from Furniture where Barcode = ?;", barcode)
		row3 := db.QueryRow("select * from Printers where Barcode = ?;", barcode)
		row4 := db.QueryRow("select * from Supplies where Barcode = ?;", barcode)
		row5 := db.QueryRow("select * from Warehouse where Barcode = ?;", barcode)
		err = row.Scan(&computer.Name, &computer.Description, &computer.Category, &computer.Barcode)
		err2 := row2.Scan(&furniture.Name, &furniture.Description, &furniture.Category, &furniture.Barcode)
		err3 := row3.Scan(&printer.Name, &printer.Description, &printer.Category, &printer.Barcode)
		err4 := row4.Scan(&supplyItem.Name, &supplyItem.Description, &supplyItem.Category, &supplyItem.Barcode)
		err5 := row5.Scan(&warehouseItem.Name, &warehouseItem.Description, &warehouseItem.Category, &warehouseItem.Barcode)
		if err != nil && err2 != nil && err3 != nil && err4 != nil && err5 != nil {
			result = gin.H{
				"result": "No Match",
				"count":  0,
			}
		} else if err != nil && err2 == nil && err3 != nil && err4 != nil && err5 != nil {
			result = gin.H{
				"result": furniture,
			}

		} else if err == nil && err2 != nil && err3 != nil && err4 != nil && err5 != nil {
			result = gin.H{
				"result": computer,
			}
		} else if err != nil && err2 != nil && err3 == nil && err4 != nil && err5 != nil {
			result = gin.H{
				"result": printer,
			}
		} else if err != nil && err2 != nil && err3 != nil && err4 == nil && err5 != nil {
			result = gin.H{
				"result": supplyItem,
			}
		} else if err != nil && err2 != nil && err3 != nil && err4 != nil && err5 == nil {
			result = gin.H{
				"result": warehouseItem,
			}
		}

		c.JSON(http.StatusOK, result)
		computers, allFurniture, printers, supplies, warehouse = nil, nil, nil, nil, nil
	})

	router.Run(":3000")
}

// Get items by something
func test(str string, c *gin.Context) gin.H {

	name := c.Param(str)

	rows, err := db.Query("select * from Computers where Name = ?;", name)
	if err != nil {
		fmt.Println(err.Error())
	}

	for rows.Next() {
		err = rows.Scan(&computer.Name, &computer.Description, &computer.Category, &computer.Barcode)
		computers = append(computers, computer)
	}

	defer rows.Close()

	rows2, err2 := db.Query("select * from Furniture where Name = ?;", name)
	if err2 != nil {
		fmt.Println(err2.Error())
	}

	for rows2.Next() {
		err2 = rows2.Scan(&furniture.Name, &furniture.Description, &furniture.Category, &furniture.Barcode)
		allFurniture = append(allFurniture, furniture)
	}

	defer rows2.Close()

	rows3, err3 := db.Query("select * from Printers where Name = ?;", name)
	if err3 != nil {
		fmt.Println(err3.Error())
	}

	for rows3.Next() {
		err3 = rows3.Scan(&printer.Name, &printer.Description, &printer.Category, &printer.Barcode)
		printers = append(printers, printer)
	}

	defer rows3.Close()

	rows4, err4 := db.Query("select * from Supplies where Name = ?;", name)
	if err4 != nil {
		fmt.Printf(err4.Error())
	}

	for rows4.Next() {
		err4 = rows4.Scan(&supplyItem.Name, &supplyItem.Description, &supplyItem.Category, &supplyItem.Barcode)
		supplies = append(supplies, supplyItem)
	}

	defer rows4.Close()

	rows5, err5 := db.Query("select * from Warehouse where Name = ?;", name)
	if err5 != nil {
		fmt.Printf(err5.Error())
	}

	for rows5.Next() {
		err5 = rows5.Scan(&warehouseItem.Name, &warehouseItem.Description, &warehouseItem.Category, &warehouseItem.Barcode)
		warehouse = append(warehouse, warehouseItem)
	}

	defer rows5.Close()

	if computers == nil && allFurniture == nil && printers == nil && supplies == nil && warehouse == nil {
		result = gin.H{
			"result": "No Match",
			"count":  0,
		}

	} else if computers != nil && allFurniture == nil && printers == nil && supplies == nil && warehouse == nil {
		result = gin.H{
			"result": computers,
		}
	} else if computers == nil && allFurniture != nil && printers == nil && supplies == nil && warehouse == nil {
		result = gin.H{
			"result": allFurniture,
		}
	} else if computers == nil && allFurniture == nil && printers != nil && supplies == nil && warehouse == nil {
		result = gin.H{
			"result": printers,
		}
	} else if computers == nil && allFurniture == nil && printers == nil && supplies != nil && warehouse == nil {
		result = gin.H{
			"result": supplies,
		}
	} else if computers == nil && allFurniture == nil && printers == nil && supplies == nil && warehouse != nil {
		result = gin.H{
			"result": warehouse,
		}
	} else {
		result = gin.H{
			"result":  computers,
			"result2": allFurniture,
			"result3": printers,
			"result4": supplies,
			"result5": warehouse,
		}
	}

	return result

}
