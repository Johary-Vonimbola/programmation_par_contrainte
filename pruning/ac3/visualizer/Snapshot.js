import { History } from "./History.js";

export const snapshot = (arcs, currentArc, message, queue) => {

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

    return new History(
            currentArc,
            message,
            domains,
            queue
        );
};