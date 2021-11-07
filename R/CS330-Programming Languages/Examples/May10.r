# Comments are after hashtags
a <- 4
a + 5
b <- 1:10
c <- b[2]
c <- b[3]
x <- c(-6, -2, 1, 7)
d <- x[1]
y <- c(-1, 2, 1, 6)
x+y
# setwd("/home/antezovko23/Desktop/Software Engineering Career/University Assignments and Projects/R/CS330-Programming Languages/Examples")
# file <- read.csv("a.csv")
# View(file)
# file$E1

mybfl <- lm(y~x)
mybfl$coefficients
plot(x, y)
abline(mybfl)
