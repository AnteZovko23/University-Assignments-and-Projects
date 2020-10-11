
#include <stdio.h>

/**
 * Author: Ante Zovko
 * Version: October 9th, 2020
 * 
 * Takes characters from user and displays 8 chars and ascii code pairs per line
 * 
 */
int main() {

    char userInput;
    int counter = 0;
    printf("Enter a char or # to quit.\n");
    while (1)
    {
        counter++;
        scanf("%c", &userInput);
        

        if(userInput == '#')
            break;
        else
            printf("Character: %c, ASCII: %d ", userInput, userInput);

        if(counter % 8 == 0){
            printf("\n");
        }
        
        
    }

    return 0;
}