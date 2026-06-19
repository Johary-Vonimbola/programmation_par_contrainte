import { History } from "./History.js";

export const snapshot = (arcs, currentArc, message, queue, prevQueue = []) => {

    const domains = new Map();

    arcs.forEach(a => {

        domains.set(a.left.name, {
            name: a.left.name,
            domain: [...a.left.domain]
        });

        domains.set(a.right.name, {
            name: a.right.name,
            domain: [...a.right.domain]
        });

    });
    const queueArray = queue.getItems ? queue.getItems() : queue;
    const newInQueue = queueArray.filter(q =>
        !prevQueue.some(p =>
            p.left.id === q.left.id &&
            p.right.id === q.right.id &&
            p.op === q.op
        )
    );
    return new History(
        currentArc,
        message,
        domains,
        queueArray,   
        newInQueue
    );
};