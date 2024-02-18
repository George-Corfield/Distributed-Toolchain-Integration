"""
This is a test module for the python.
"""

def string():
    """This function returns the string 'test String'."""

    return "test String"

def add(nums):
    """sums the values in an array"""

    total = 0
    for num in nums:
        total += float(num)
    return total
