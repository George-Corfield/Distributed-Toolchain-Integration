"""
used for unit testing 
to run a test use while in the python directory:
   py -m unittest
"""

import unittest
from python_backend.python_api import app 
import requests

class TestModule(unittest.TestCase):

    def test_sum(self):
        """
        Tests the add function in the python_api file.
        """

        tests = [[1,2,3,6.0],
                 [-1,0,1,0.0],
                 [3.1,1,1,5.1]]
        for test in tests:
            data = {"array":test[:3]}
            self.assertEqual(float(requests.post("http://localhost:5000/add", 
                                            json=data).text),test[3], "Sum Failed")

    def test_foo(self):
        """
        Tests the test_string function in the python_api file.
        """

        self.assertEqual(requests.get("http://localhost:5000/foo").text,
                                             "test String", "Foo failed.")
    
    def test_fake(self):
        """
        This is a fake unittest to allow for actions to be developed.
        """

        self.assertEqual(1,1)

if __name__ == '__main__':
    unittest.main()
