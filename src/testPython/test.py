# used for unit testing 
# to run a test use while in the python directory:
#   py -m unittest

import unittest, sys

sys.path.append("..")

from main.python import testModule

class TestModule(unittest.TestCase):

    def test_sum(self):
        tests = [[1,2,3,6],
                 [-1,0,1,0],
                 [3.1,1,1,5.1]]
        for test in tests:
            self.assertEqual(testModule.add(test[:3]), test[3], 'Sum failed.')

    def test_foo(self):
        self.assertEqual(testModule.foo(), "Test String", "Foo failed.")

    def test_optimiseReq(self):
        lower = 3
        upper = 10
        for _ in range(100):
            self.assertTrue(lower <= testModule.optimiseReq([lower, upper]) <= upper)

if __name__ == '__main__':
    unittest.main()