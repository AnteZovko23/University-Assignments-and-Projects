file <- read.csv("mydata.csv", header = TRUE, check.names = FALSE)

# Get col names
columnNames <- colnames(file)


# Get x and y
x_vals <- file[[1]]
y_vals <- file[[2]]


# pch makes solid circles, cex is the size
plot(x_vals, y_vals,
     xlab = columnNames[1],
     ylab = columnNames[2],
     pch = 16, 
     cex=1.2, 
     col="RED",
     main = "SPRING POSITION PLOTTED AGAINST SPRING ENERGY")


# Grid
# NULL Aligns with numbers on axes, lty line type
grid(nx = NULL, ny = NULL, col = "lightgray", lty = 2,
     lwd = par("lwd"), equilogs = TRUE)


# Legend
# no clue what most of this does
legend("topleft", 
       legend = c("Best Fit Line"), 
       col = c(rgb(0,0,0)),
       pch = 20, 
       bty = "n", 
       pt.cex = 1, 
       cex = 1.2, 
       text.col = "black", 
       horiz = F, 
       inset = c(0, 0))



# Get best fit line
best_fit_line <- lm(y_vals~x_vals)


# Draw line
# lwd = thickness
abline(best_fit_line, lwd = 2)

# obtain R squared
R2 <- round(summary(best_fit_line)$r.square, digits = 5)

# Add label
R2str <- paste("R-Squared:", R2)


#Get coefficients
## possible issue if y-intercept is negative because
# + is hard-coded
coef <- summary(best_fit_line)$coefficients

# Add label
Equation <- paste("y = ", round(coef[2], digits = 3), "x + ", round(coef[1], digits = 3), sep="")


## Concat both
fullInfo <- paste(R2str, "   ", Equation)
title(sub = fullInfo)

