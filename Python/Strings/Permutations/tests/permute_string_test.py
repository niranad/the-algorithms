import unittest
from ..permute_string import permute_string


class StringPermutationTest(unittest.TestCase):
    def test_sample_case(self):
        s = 'ABC'
        d = {}
        permutations = permute_string(s)

        self.assertEqual(len(permutations), 3 * 2 * 1)
        for i in range(len(permutations)):
            d[permutations[i]] = permutations[i]

        self.assertDictEqual(d,
                             {'ABC': 'ABC', 'BAC': 'BAC', 'CBA': 'CBA', 'BCA': 'BCA', 'CAB': 'CAB', 'ACB': 'ACB'})


if __name__ == '__main__':
    unittest.main()
