# time complexity: O(k^n)

def generate_strings(s, k):
    """Generates all strings of length n from a given string of length n, drawing
    the characters in the range 0 to k, whereby 0 <= k <= n. Returns a list
    of all the strings."""

    strings_list = []
    chars_list = [None] * len(s)

    # recursively generate the strings
    def generator(char_pos, range_limit):
        if char_pos < 1:
            strings_list.append(''.join(chars_list))
        else:
            for i in range(range_limit):
                chars_list[char_pos - 1] = s[i]
                generator(char_pos - 1, range_limit)

    generator(len(s), k)

    return strings_list
