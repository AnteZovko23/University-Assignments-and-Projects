package lib

import (
	"bytes"
	"database/sql"
	"fmt"
	"net/http"

	"strings"

	"github.com/gin-gonic/gin"
	// Nisam siguran sta je ovo ali bojim se izbrisat
	_ "github.com/go-sql-driver/mysql"
)

// Thingslist sas
var Thingslist []interface{}

// Db base
var Db *sql.DB

// Err error
var Err error
var err2 error
var err3 error
var err4 error
var err5 error

var rows *sql.Rows
var rows2 *sql.Rows
var rows3 *sql.Rows
var rows4 *sql.Rows
var rows5 *sql.Rows

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

	// Result sag
	Result gin.H
)

// AllItems Gets all items from all tables
func AllItems(offset string, limit string, str string, item string, table string, c *gin.Context, o struct {
	Name        string
	Description string
	Category    string
	Barcode     int
}) gin.H {

	// All items
	if len(strings.TrimSpace(table)) == 0 && len(strings.TrimSpace(str)) == 0 && len(strings.TrimSpace(item)) == 0 {

		if len(strings.TrimSpace(limit)) == 0 {
			rows, Err = Db.Query("select * from Computers;")

			rows2, err2 = Db.Query("select * from Furniture;")

			rows3, err3 = Db.Query("select * from Printers;")

			rows4, err4 = Db.Query("select * from Supplies;")

			rows5, err5 = Db.Query("select * from Warehouse;")
		} else {
			rows, Err = Db.Query("select * from Computers limit " + limit + " offset " + offset + ";")
			rows2, Err = Db.Query("select * from Furniture limit " + limit + " offset " + offset + ";")
			rows3, Err = Db.Query("select * from Printers limit " + limit + " offset " + offset + ";")
			rows4, Err = Db.Query("select * from Supplies limit " + limit + " offset " + offset + ";")
			rows5, Err = Db.Query("select * from Warehouse limit " + limit + " offset " + offset + ";")
		}

		if rows == nil || rows2 == nil || rows3 == nil || rows4 == nil || rows5 == nil {
			c.Redirect(http.StatusTemporaryRedirect, "users/homepage")
		} else {
			for rows.Next() {
				Err = rows.Scan(&ComputerItem.Name, &ComputerItem.Description, &ComputerItem.Category, &ComputerItem.Barcode)
				ComputerArr = append(ComputerArr, ComputerItem)

				if Err != nil {
					fmt.Print(Err.Error())
				}
			}

			defer rows.Close()

			for rows2.Next() {
				err2 = rows2.Scan(&FurnitureItem.Name, &FurnitureItem.Description, &FurnitureItem.Category, &FurnitureItem.Barcode)
				FurnitureArr = append(FurnitureArr, FurnitureItem)

				if err2 != nil {
					fmt.Print(err2.Error())
				}
			}

			defer rows2.Close()

			for rows3.Next() {
				err3 = rows3.Scan(&PrinterItem.Name, &PrinterItem.Description, &PrinterItem.Category, &PrinterItem.Barcode)
				PrintersArr = append(PrintersArr, PrinterItem)

				if err3 != nil {
					fmt.Print(err3.Error())
				}
			}

			defer rows3.Close()

			for rows4.Next() {
				err4 = rows4.Scan(&SupplyItem.Name, &SupplyItem.Description, &SupplyItem.Category, &SupplyItem.Barcode)
				SuppliesArr = append(SuppliesArr, SupplyItem)

				if err4 != nil {
					fmt.Print(err4.Error())
				}
			}

			defer rows4.Close()

			for rows5.Next() {
				err5 = rows5.Scan(&WarehouseItem.Name, &WarehouseItem.Description, &WarehouseItem.Category, &WarehouseItem.Barcode)
				WarehouseArr = append(WarehouseArr, WarehouseItem)

				if err5 != nil {
					fmt.Print(err5.Error())
				}
			}

			defer rows5.Close()

			Thingslist = append(Thingslist, ComputerArr)
			Thingslist = append(Thingslist, FurnitureArr)
			Thingslist = append(Thingslist, PrintersArr)
			Thingslist = append(Thingslist, SuppliesArr)
			Thingslist = append(Thingslist, WarehouseArr)
			c.JSON(http.StatusOK, gin.H{

				"All": Thingslist,
			})
		}

	} else if len(strings.TrimSpace(str)) == 0 && len(strings.TrimSpace(item)) == 0 {
		// A specific table
		if len(strings.TrimSpace(limit)) == 0 {
			rows, Err := Db.Query("select * from " + table + ";")
			if rows == nil {
				c.Redirect(http.StatusTemporaryRedirect, "users/homepage")
			} else {
				for rows.Next() {
					Err = rows.Scan(&o.Name, &o.Description, &o.Category, &o.Barcode)
					Thingslist = append(Thingslist, o)
					if Err != nil {
						fmt.Print(Err.Error())
					}
				}
			}
		} else {
			rows, Err := Db.Query("select * from " + table + " limit " + limit + " offset " + offset + ";")

			for rows.Next() {
				Err = rows.Scan(&o.Name, &o.Description, &o.Category, &o.Barcode)
				Thingslist = append(Thingslist, o)
				if Err != nil {
					fmt.Print(Err.Error())
				}
			}
		}

		// Result = gin.H{
		// 	"Result": Thingslist,
		// }

		c.JSON(http.StatusOK, gin.H{

			"All": Thingslist,
		})
		// From all Items based on Field
	} else if len(strings.TrimSpace(table)) == 0 {
		if len(strings.TrimSpace(limit)) == 0 {
			rows, Err = Db.Query("select * from Computers where "+str+" = ?;", item)
			rows2, err2 = Db.Query("select * from Furniture where "+str+" = ?;", item)
			rows3, err3 = Db.Query("select * from Printers where "+str+" = ?;", item)
			rows4, err4 = Db.Query("select * from Supplies where "+str+" = ?;", item)
			rows5, err5 = Db.Query("select * from Warehouse where "+str+" = ?;", item)
		} else {
			rows, Err = Db.Query("select * from Computers where "+str+" = ? limit "+limit+" offset "+offset+";", item)
			rows2, err2 = Db.Query("select * from Furniture where "+str+" = ? limit "+limit+" offset "+offset+";", item)
			rows3, err3 = Db.Query("select * from Printers where "+str+" = ? limit "+limit+" offset "+offset+";", item)
			rows4, err4 = Db.Query("select * from Supplies where "+str+" = ? limit "+limit+" offset "+offset+";", item)
			rows5, err5 = Db.Query("select * from Warehouse where "+str+" = ? limit "+limit+" offset "+offset+";", item)
		}

		if Err != nil {
			fmt.Println(Err.Error())
		}

		for rows.Next() {
			Err = rows.Scan(&ComputerItem.Name, &ComputerItem.Description, &ComputerItem.Category, &ComputerItem.Barcode)
			ComputerArr = append(ComputerArr, ComputerItem)
		}

		defer rows.Close()

		if err2 != nil {
			fmt.Println(err2.Error())
		}

		for rows2.Next() {
			err2 = rows2.Scan(&FurnitureItem.Name, &FurnitureItem.Description, &FurnitureItem.Category, &FurnitureItem.Barcode)
			FurnitureArr = append(FurnitureArr, FurnitureItem)
		}

		defer rows2.Close()

		if err3 != nil {
			fmt.Println(err3.Error())
		}

		for rows3.Next() {
			err3 = rows3.Scan(&PrinterItem.Name, &PrinterItem.Description, &PrinterItem.Category, &PrinterItem.Barcode)
			PrintersArr = append(PrintersArr, PrinterItem)
		}

		defer rows3.Close()

		if err4 != nil {
			fmt.Printf(err4.Error())
		}

		for rows4.Next() {
			err4 = rows4.Scan(&SupplyItem.Name, &SupplyItem.Description, &SupplyItem.Category, &SupplyItem.Barcode)
			SuppliesArr = append(SuppliesArr, SupplyItem)
		}

		defer rows4.Close()

		if err5 != nil {
			fmt.Printf(err5.Error())
		}

		for rows5.Next() {
			err5 = rows5.Scan(&WarehouseItem.Name, &WarehouseItem.Description, &WarehouseItem.Category, &WarehouseItem.Barcode)
			WarehouseArr = append(WarehouseArr, WarehouseItem)
		}

		defer rows5.Close()

		if ComputerArr == nil && FurnitureArr == nil && PrintersArr == nil && SuppliesArr == nil && WarehouseArr == nil {
			Result = gin.H{
				"Result": "No Match",
				"count":  0,
			}

		} else if ComputerArr != nil && FurnitureArr == nil && PrintersArr == nil && SuppliesArr == nil && WarehouseArr == nil {
			c.JSON(http.StatusOK, gin.H{
				"All": ComputerArr,
			})

		} else if ComputerArr == nil && FurnitureArr != nil && PrintersArr == nil && SuppliesArr == nil && WarehouseArr == nil {
			c.JSON(http.StatusOK, gin.H{

				"All": FurnitureArr,
			})
		} else if ComputerArr == nil && FurnitureArr == nil && PrintersArr != nil && SuppliesArr == nil && WarehouseArr == nil {
			c.JSON(http.StatusOK, gin.H{

				"All": PrintersArr,
			})
		} else if ComputerArr == nil && FurnitureArr == nil && PrintersArr == nil && SuppliesArr != nil && WarehouseArr == nil {
			c.JSON(http.StatusOK, gin.H{

				"All": SuppliesArr,
			})
		} else if ComputerArr == nil && FurnitureArr == nil && PrintersArr == nil && SuppliesArr == nil && WarehouseArr != nil {
			c.JSON(http.StatusOK, gin.H{

				"All": WarehouseArr,
			})
		} else {
			Thingslist = append(Thingslist, ComputerArr)
			Thingslist = append(Thingslist, FurnitureArr)
			Thingslist = append(Thingslist, PrintersArr)
			Thingslist = append(Thingslist, SuppliesArr)
			Thingslist = append(Thingslist, WarehouseArr)
			c.JSON(http.StatusOK, gin.H{

				"All": Thingslist,
			})
		}

		// From specific table based on field
	} else if len(strings.TrimSpace(table)) != 0 && len(strings.TrimSpace(str)) != 0 && len(strings.TrimSpace(item)) != 0 {
		if len(strings.TrimSpace(limit)) == 0 {
			rows, Err = Db.Query("select * from "+table+" where "+str+" = ?;", item)
		} else {
			rows, Err = Db.Query("select * from "+table+" where "+str+" = ? limit "+limit+" offset "+offset+";", item)
		}

		if Err != nil {
			fmt.Println(Err.Error())
		}

		for rows.Next() {
			Err = rows.Scan(&ComputerItem.Name, &ComputerItem.Description, &ComputerItem.Category, &ComputerItem.Barcode)
			ComputerArr = append(ComputerArr, ComputerItem)
		}

		defer rows.Close()
		c.JSON(http.StatusOK, gin.H{

			"All": ComputerArr,
		})
	}

	return Result

}

// NewItem Posts a new item to a table
func NewItem(str string, c *gin.Context) string {
	var buffer bytes.Buffer
	name := c.PostForm("Name")
	description := c.PostForm("Description")
	category := c.PostForm("Category")
	barcode := c.PostForm("Barcode")
	stmt, err := Db.Prepare("INSERT INTO " + str + " (Name, Description, Category, Barcode) VALUES (?, ?, ?, ?);")
	if err != nil {
		fmt.Println(err.Error())
	}

	_, err = stmt.Exec(name, description, category, barcode)
	if err != nil {
		fmt.Println(err.Error())
	}

	buffer.WriteString(name)
	buffer.WriteString(" ")
	buffer.WriteString(description)
	buffer.WriteString(" ")
	buffer.WriteString(category)
	buffer.WriteString(" ")
	buffer.WriteString(barcode)
	defer stmt.Close()
	item := buffer.String()
	return item

}

// DeleteItemsByField Deletes items based on given field
func DeleteItemsByField(table string, str string, item string, tables []string, c *gin.Context) string {

	// var query string
	if len(strings.TrimSpace(table)) == 0 {
		for i := 0; i < len(tables); i++ {
			// query = c.Query(str)
			stmt, err := Db.Prepare("delete from " + tables[i] + " where " + str + " = ?;")
			if err != nil {
				fmt.Println(err.Error())
			}

			_, err = stmt.Exec(item)
			if err != nil {
				fmt.Println(err.Error())
			}
		}
	} else {
		for i := 0; i < len(tables); i++ {
			// query = c.Query(str)
			stmt, err := Db.Prepare("delete from " + table + " where " + str + " = ?;")
			if err != nil {
				fmt.Println(err.Error())
			}

			_, err = stmt.Exec(item)
			if err != nil {
				fmt.Println(err.Error())
			}
		}
	}

	return item

}

// UpdateItemsByField Updates item based on field parameter
func UpdateItemsByField(table string, str string, item string, tables []string, c *gin.Context) string {

	name := c.PostForm("Name")
	description := c.PostForm("Description")
	category := c.PostForm("Category")
	barcode := c.PostForm("Barcode")

	if len(strings.TrimSpace(table)) == 0 {
		for i := 0; i < len(tables); i++ {
			stmt, err := Db.Prepare("update " + tables[i] + " set Name = ?, Description = ?, Category = ?, Barcode = ? where " + str + " = ?;")
			if err != nil {
				fmt.Println(err.Error())
			}

			_, err = stmt.Exec(name, description, category, barcode, item)
			if err != nil {
				fmt.Println(err.Error())
			}
		}
	} else {
		for i := 0; i < len(tables); i++ {
			stmt, err := Db.Prepare("update " + table + " set Name = ?, Description = ?, Category = ?, Barcode = ? where " + str + " = ?;")
			if err != nil {
				fmt.Println(err.Error())
			}

			_, err = stmt.Exec(name, description, category, barcode, item)
			if err != nil {
				fmt.Println(err.Error())
			}
		}
	}

	return item

}

// Render whatever
func Render(c *gin.Context, data gin.H, templateName string) {
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
