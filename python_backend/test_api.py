"""
used for unit testing
to run a test use while in the python directory:
   py -m unittest
"""

import unittest
from modules import module

class TestModule(unittest.TestCase):
    """
    This is the class for testing the python_api.py.
    Currently it requires the python_api script to be running which is less than ideal.
    """

    def test_sum(self):
        """
        Tests the add function in the python_api file.
        """

        tests = [[1,2,3,6.0],
                 [-1,0,1,0.0],
                 [3.1,1,1,5.1]]
        for test in tests:
            self.assertEqual(float(module.add(test[:3])),test[3], "Sum Failed")

    def test_foo(self):
        """
        Tests the test_string function in the python_api file.
        """

        self.assertEqual(module.string(),"test String", "Foo failed.")

    def test_fake(self):
        """
        This is a fake unittest to allow for actions to be developed.
        """

        self.assertEqual(1,1)

if __name__ == '__main__':
    unittest.main()
