
#include <stdio.h>

/*
 * Author: Ante Zovko
 * Version: October 9th, 2020
 * Prints given name and demonstrates left and right justify
 * 
 * 
 */
int main() {

    char initialString[20]; 

    printf("Enter your first name: ");
    scanf("%[^\n]%*c", initialString);

    printf("\n\"%s\"", initialString);
    printf("\n\"%-25s\"", initialString);
    printf("\n\"%25s\"\n",initialString);

    return 0;
}