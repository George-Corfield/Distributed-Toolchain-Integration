# used for unit testing 
# to run a test use while in the python directory:
#   py -m unittest
#
# preferably this gets moved to the test folder, but
# when moved to the test folder it cant discover testModule and the following error is returned
#
#ImportError: Failed to import test module: test
#Traceback (most recent call last):
#  File "C:\Users\dylan\AppData\Local\Programs\Python\Python310\lib\unittest\loader.py", line 436, in _find_e 436, in _find_test_path
#    module = self._get_module_from_name(name)
#  File "C:\Users\dylan\AppData\Local\Programs\Python\Python310\lib\unittest\loader.py", line 377, in _get_me 377, in _get_module_from_name
#    __import__(name)
#  File "S:\Uni-Coursework\2023-DistributedToolchainIntegration\src\test\java\UoBToolchainGroup\DistributedToup\DistributedToolchainIntegration\test.py", line 8, in <module>
#    import testModule
#ModuleNotFoundError: No module named 'testModule'
#
# I have tried the following
# adding an __init__.py file to both the test folder and the python folder.
# attempting to modify the PYTHONPATH variable
# attempting to use code to set the path
#
# Any other ideas would be appreciated.

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