# Author: Ante Zovko
# Version: September 15th, 2020
# Description: A not Gate

def Not(A):
    tt = {0:1, 1:0}
    return tt[A]


print(f"Not(1) = {Not(1)}")

