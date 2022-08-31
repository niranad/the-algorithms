/* Problem statement: Write a function to return all subsets of an input
 * array set of distinct elements.
 */

function subsets(arr) {
  let map = {};
  map[arr.join('')] = arr;

  function getSubsets(map, set) {
    let len = set.length;

    if (len == 1) return;

    for (let i = 0; i < len; i++) {
      let subset = set.slice(0, i).concat(set.slice(i + 1));
      if (map[subset.join('')] === undefined) {
        map[subset.join('')] = subset;
        getSubsets(map, subset);
      }
    }
  }

  getSubsets(map, arr);
  return Object.values(map);
}
