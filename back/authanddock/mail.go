package main

import (
	"context"
	// "fmt"
	"github.com/mailgun/mailgun-go"
	"time"
)

func main() {

	sendSimpleMessage("sandboxc5a02f1ae92c4375bc3863b1f7144b25.mailgun.org", "fdfc4ca0adba0721c12442c9d461b5a5-2b778fc3-77f9ef15")

}
func sendSimpleMessage(domain, apiKey string) (string, error) {
	mg := mailgun.NewMailgun(domain, apiKey)
	m := mg.NewMessage(
		"Mailgun Sandbox <postmaster@sandboxc5a02f1ae92c4375bc3863b1f7144b25.mailgun.org>",
		"Hello Ante Zovko",
		"Testing some Mailgun awesomeness!",
		"antezovko.az@gmail.com",
	)
	ctx, cancel := context.WithTimeout(context.Background(), time.Second*30)
	defer cancel()

	_, id, err := mg.Send(ctx, m)
	return id, err
}
