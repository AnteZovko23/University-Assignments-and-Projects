from time import time


"""
Author: Ante Zovko
Description: Shows the Executions times of 4 example functions
Version: 12/15/2020

"""

def example1(S):
    n = len(S)
    total = 0
    for j in range(n):
        total += S[j]
    return total


def example2(S):
    n = len(S)
    total = 0
    for j in range(0, n, 2):
        total += S[j]
    return total


def example3(S):
    n = len(S)
    total = 0
    for j in range(n):
        for k in range(1 + j):
            total += S[k]
    return total


def example4(S):
    n = len(S)
    prefix = 0
    total = 0
    for j in range(n):
        prefix += S[j]
        total += prefix
    return total

# Calls given functions
def functionRunner(functions, numberOfFunctionCalls = 1):

    # Starting size of list
    size = 50
    S = range(size)

    # For each given function
    for function in functions:

        # Number of calls
        for i in range(1, numberOfFunctionCalls + 1):

            startTime = time()
            function(S)
            endTime = time()

            print("Execution time: {}. run = {:.10f}".format(i, endTime - startTime))

            # Increase list size
            size *= 10
            S = range(size)

        # Reset list size for next function
        size = 50
        S = range(size)
        print()



"""
    The following code will run a single function.
    Repeat similar codes for all four functions.
    Make sure to use the same input for each function execution.
    Execute multiple runs with different sizes of input.
    
    start_time = time()
    S = range(10)
    example1(S)
    end_time = time()
    T_time = end_time - start_time
    print("Execution time = {}".format(T_time))
    """
if __name__ == '__main__':

    functions = [example1, example2, example3, example4]
    functionRunner(functions, 3)

       
    
    
