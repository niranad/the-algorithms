
def permute_string(s):
    """Finds all permutations of an input string. Each character is treated as unique
    whether or not the input string contains duplicate characters."""
    chars_list = list(s)
    output = []

    # find all permutations by recursion
    def permute(_list, idx, n, permutations):
        if idx > n - 2:
            permutations += [''.join(_list)]
        else:
            for i in range(idx, len(_list)):
                new_char_list = swap(_list, idx, i)
                permute(new_char_list, idx + 1, n, permutations)

    # swap two characters
    def swap(_list, i, j):
        # copy _list
        li = _list.copy()
        # swap
        temp = li[i]
        li[i] = li[j]
        li[j] = temp

        return li

    permute(chars_list, 0, len(s), output)
    return output
