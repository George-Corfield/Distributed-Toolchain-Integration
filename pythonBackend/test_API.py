# used for unit testing 
# to run a test use while in the python directory:
#   py -m unittest

import unittest
from pythonAPI import app 
import requests
import json

class TestModule(unittest.TestCase):

    def test_sum(self):
        tests = [[1,2,3,6.0],
                 [-1,0,1,0.0],
                 [3.1,1,1,5.1]]
        for test in tests:
            data = {"array":test[:3]}
            self.assertEqual(float(requests.post("http://localhost:5000/add", json=data).text), test[3], "Sum Failed")

    def test_foo(self):
        self.assertEqual(requests.get("http://localhost:5000/foo").text, "test String", "Foo failed.")
    
    def test_fake(self):
        self.assertEqual(1,1)

if __name__ == '__main__':
    unittest.main()
    