
#include <stdio.h>
#define divisionConst 60

/**
 * 
 * Author: Ante Zovko
 * Version: October 9th, 2020
 * 
 * Converts minutes to hours and minutes
 * 
 */
int main() {

    int userTime;
    int hours;
    int minutes;


    while(1){

        printf("Enter the number of minutes to convert: ");
        scanf("%d", &userTime);

        if(userTime <= 0)
            break;
        else
        {
            hours = userTime / 60;
            minutes = userTime % 60;

        }
        
        printf("\n%d minutes = %d hours, %d minutes\n", userTime, hours, minutes);

    }
    printf("Done.\n");
    
    return 0;
}
