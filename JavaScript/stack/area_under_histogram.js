function areaUnderHistogram(bars) {
  'use strict';

  if (bars === undefined || bars === null || bars.length === 0) {
    return 0;
  }

  let stack = [];
  let maxArea = 0,
    i = 0,
    len = bars.length;

  // loop through the bars
  while (i < len) {
    // If bars is empty or bar on top of stack is lesser than current bar,
    // push its index onto the stack
    if (stack.length === 0 || bars[stack[stack.length - 1]] <= bars[i]) {
      stack.push(i++);
    } else {
      let top = stack.pop();
      maxArea = Math.max(
        maxArea,
        bars[top] * (stack.length === 0 ? i : i - stack[stack.length - 1] - 1),
      );
    }
  }

  while (stack.length !== 0) {
    let top = stack.pop();
    maxArea = Math.max(
      maxArea,
      bars[top] * (stack.length === 0 ? i : i - stack[stack.length - 1] - 1),
    );
  }

  return maxArea;
}
