package lib

import (
	//"bytes"
	"database/sql"
	"fmt"
	// "net/http"

	"github.com/gin-gonic/gin"
	// Nisam siguran sta je ovo ali bojim se izbrisat
	_ "github.com/go-sql-driver/mysql"
)

// Db base
var Db *sql.DB

// Err error
var Err error

// Computer Table
type Computer struct {
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
	// ComputerItem single item from Computer Table
	ComputerItem Computer
	// ComputerArr array from Computer Table
	ComputerArr []Computer

	// FurnitureItem single item from Furniture Table
	FurnitureItem Furniture
	// FurnitureArr array from Furniture Table
	FurnitureArr []Furniture

	// PrinterItem single item from Printer Table
	PrinterItem Printers
	// PrintersArr array from Printer Table
	PrintersArr []Printers

	// SupplyItem single item from Computer Table
	SupplyItem Supplies
	//SuppliesArr array from Printer Table
	SuppliesArr []Supplies

	// WarehouseItem single item from Computer Table
	WarehouseItem Warehouse
	// WarehouseArr single item from Computer Table
	WarehouseArr []Warehouse

	result gin.H
)

// GetAllItemsBy Gets all items by specific parameter
func GetAllItemsBy(str string, c *gin.Context) gin.H {

	queryVar := c.Param(str)

	rows, Err := Db.Query("select * from Computers where "+str+" = ?;", queryVar)
	if Err != nil {
		fmt.Println(Err.Error())
	}

	for rows.Next() {
		Err = rows.Scan(&ComputerItem.Name, &ComputerItem.Description, &ComputerItem.Category, &ComputerItem.Barcode)
		ComputerArr = append(ComputerArr, ComputerItem)
	}

	defer rows.Close()

	rows2, Err2 := Db.Query("select * from Furniture where "+str+" = ?;", queryVar)
	if Err2 != nil {
		fmt.Println(Err2.Error())
	}

	for rows2.Next() {
		Err2 = rows2.Scan(&FurnitureItem.Name, &FurnitureItem.Description, &FurnitureItem.Category, &FurnitureItem.Barcode)
		FurnitureArr = append(FurnitureArr, FurnitureItem)
	}

	defer rows2.Close()

	rows3, Err3 := Db.Query("select * from Printers where "+str+" = ?;", queryVar)
	if Err3 != nil {
		fmt.Println(Err3.Error())
	}

	for rows3.Next() {
		Err3 = rows3.Scan(&PrinterItem.Name, &PrinterItem.Description, &PrinterItem.Category, &PrinterItem.Barcode)
		PrintersArr = append(PrintersArr, PrinterItem)
	}

	defer rows3.Close()

	rows4, Err4 := Db.Query("select * from Supplies where "+str+" = ?;", queryVar)
	if Err4 != nil {
		fmt.Printf(Err4.Error())
	}

	for rows4.Next() {
		Err4 = rows4.Scan(&SupplyItem.Name, &SupplyItem.Description, &SupplyItem.Category, &SupplyItem.Barcode)
		SuppliesArr = append(SuppliesArr, SupplyItem)
	}

	defer rows4.Close()

	rows5, Err5 := Db.Query("select * from Warehouse where "+str+" = ?;", queryVar)
	if Err5 != nil {
		fmt.Printf(Err5.Error())
	}

	for rows5.Next() {
		Err5 = rows5.Scan(&WarehouseItem.Name, &WarehouseItem.Description, &WarehouseItem.Category, &WarehouseItem.Barcode)
		WarehouseArr = append(WarehouseArr, WarehouseItem)
	}

	defer rows5.Close()

	if ComputerArr == nil && FurnitureArr == nil && PrintersArr == nil && SuppliesArr == nil && WarehouseArr == nil {
		result = gin.H{
			"result": "No Match",
			"count":  0,
		}

	} else if ComputerArr != nil && FurnitureArr == nil && PrintersArr == nil && SuppliesArr == nil && WarehouseArr == nil {
		result = gin.H{
			"result": ComputerArr,
		}
	} else if ComputerArr == nil && FurnitureArr != nil && PrintersArr == nil && SuppliesArr == nil && WarehouseArr == nil {
		result = gin.H{
			"result": FurnitureArr,
		}
	} else if ComputerArr == nil && FurnitureArr == nil && PrintersArr != nil && SuppliesArr == nil && WarehouseArr == nil {
		result = gin.H{
			"result": PrintersArr,
		}
	} else if ComputerArr == nil && FurnitureArr == nil && PrintersArr == nil && SuppliesArr != nil && WarehouseArr == nil {
		result = gin.H{
			"result": SuppliesArr,
		}
	} else if ComputerArr == nil && FurnitureArr == nil && PrintersArr == nil && SuppliesArr == nil && WarehouseArr != nil {
		result = gin.H{
			"result": WarehouseArr,
		}
	} else {
		result = gin.H{
			"result":  ComputerArr,
			"result2": FurnitureArr,
			"result3": PrintersArr,
			"result4": SuppliesArr,
			"result5": WarehouseArr,
		}
	}

	return result

}

// GetTable Gets table name based on parameter
func GetTable(str string, o struct {
	Name        string
	Description string
	Category    string
	Barcode     int
}) []interface{} {

	var thingList []interface{}

	rows, Err := Db.Query("select * from " + str + ";")

	for rows.Next() {
		Err = rows.Scan(&o.Name, &o.Description, &o.Category, &o.Barcode)
		thingList = append(thingList, o)
		if Err != nil {
			fmt.Print(Err.Error())
		}
	}

	defer rows.Close()

	return thingList
}

// AllItems Gets all items from all tables
func AllItems(c *gin.Context) gin.H {

	rows, err := Db.Query("select * from Computers;")

	rows2, err2 := Db.Query("select * from Furniture;")

	rows3, err3 := Db.Query("select * from Printers;")

	rows4, err4 := Db.Query("select * from Supplies;")

	rows5, err5 := Db.Query("select * from Warehouse;")

	for rows.Next() {
		err = rows.Scan(&ComputerItem.Name, &ComputerItem.Description, &ComputerItem.Category, &ComputerItem.Barcode)
		ComputerArr = append(ComputerArr, ComputerItem)

		if err != nil {
			fmt.Print(err.Error())
		}
	}

	defer rows.Close()

	for rows2.Next() {
		err2 = rows2.Scan(&FurnitureItem.Name, &FurnitureItem.Description, &FurnitureItem.Category, &FurnitureItem.Barcode)
		FurnitureArr = append(FurnitureArr, FurnitureItem)

		if err2 != nil {
			fmt.Print(err.Error())
		}
	}

	defer rows2.Close()

	for rows3.Next() {
		err3 = rows3.Scan(&PrinterItem.Name, &PrinterItem.Description, &PrinterItem.Category, &PrinterItem.Barcode)
		PrintersArr = append(PrintersArr, PrinterItem)

		if err3 != nil {
			fmt.Print(err.Error())
		}
	}

	defer rows3.Close()

	for rows4.Next() {
		err4 = rows4.Scan(&SupplyItem.Name, &SupplyItem.Description, &SupplyItem.Category, &SupplyItem.Barcode)
		SuppliesArr = append(SuppliesArr, SupplyItem)

		if err4 != nil {
			fmt.Print(err.Error())
		}
	}

	defer rows4.Close()

	for rows5.Next() {
		err5 = rows5.Scan(&WarehouseItem.Name, &WarehouseItem.Description, &WarehouseItem.Category, &WarehouseItem.Barcode)
		WarehouseArr = append(WarehouseArr, WarehouseItem)

		if err5 != nil {
			fmt.Print(err.Error())
		}
	}

	defer rows5.Close()

	result = gin.H{
		"result":  ComputerArr,
		"result2": FurnitureArr,
		"result3": PrintersArr,
		"result4": SuppliesArr,
		"result5": WarehouseArr,
	}

	return result

}
