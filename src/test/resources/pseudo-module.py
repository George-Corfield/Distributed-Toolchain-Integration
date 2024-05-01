"""
Test Module for python and module upload

Module multiplies the values in an array
"""
import sys


def mult(nums):
    """multiplies values in array"""
    tot = 1
    for n in nums:
        tot *= float(n)
    return tot 


if __name__ == "__main__":
   mult(sys.argv[1:])
   