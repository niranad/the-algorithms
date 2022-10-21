/*
Give an algorithm to reverse a queue. 
*/

// assuming the queue is a javascript array
function reverseQueue(queue) {
  if (!queue || queue.length < 1) return queue;

  function dequeueAll() {
    if (queue.length === 0) return;
    const item = queue.shift();
    dequeueAll(queue);
    enqueueInReverse(queue, item);
  }

  function enqueueInReverse(queue, data) {
    queue.push(data);
  }

  dequeueAll();

  return queue;
}
