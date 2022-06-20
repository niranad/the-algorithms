import unittest
from ..generate_strings import generate_strings


class StringPermutationsWithRepetition(unittest.TestCase):
    def test_sample_case(self):
        s = 'ABC'
        self.assertLogs()
        self.assertEqual(len(generate_strings(s, 3)), 27, 'should generate strings of length 3^3')
        self.assertEqual(len(generate_strings(s, 2)), 8, 'should generate strings of length 2^3')
        self.assertEqual(len(generate_strings(s, 1)), 1, 'should generate strings of length 1^3')


if __name__ == '__main__':
    unittest.main()
