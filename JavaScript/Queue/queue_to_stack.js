/* 
Given a queue Q containing n elements, transfer these items on to a stack S
(initially empty) so that front element of Q appears at the top of the stack 
and the order of all other items is preserved. Using enqueue and dequeue 
operations for the queue, and push and pop operations for the stack, outline 
an efficient O(n) algorithm to accomplish the above task, using only a constant 
amount of additional storage.Given a queue Q containing n elements, transfer these 
items on to a stack S (initially empty) so that front element of Q appears at the 
top of the stack and the order of all other items is preserved. Using enqueue and 
dequeue operations for the queue, and push and pop operations for the stack, 
outline an efficient O(n) algorithm to accomplish the above task, using only a 
constant amount of additional storage.
*/


function preserveQueueInStack(queue, stack) {
  if (!queue || queue.length < 1 || !stack) return queue;

  function dequeueAll() {
    if (queue.length < 1) return;
    const item = queue.shift(); // dequeue operation
    dequeueAll();
    transferToStack(item);
  }

  function transferToStack(item) {
    stack.push(item);
  } 

  dequeueAll()

  return stack;
}
