import { ConstraintVerifier } from "./Constraints.js";
import { Queue } from "./Queue.js";
import { snapshot } from "../visualizer/Snapshot.js";

export const revise = cst => {
    let revised = false;
    const X = cst.left, Y = cst.right;
    const leftFunc = cst.leftFunc, rightFunc = cst.rightFunc;
    const op = cst.op;
    for(let i=0; i<X.domain.length; i++){
        let remove = true;
        for(let j=0; j<Y.domain.length; j++){
            let l = leftFunc(X.domain[i]);
            let r = rightFunc(Y.domain[j]);
            let cstVerifier = new ConstraintVerifier(l, op, r);
            if(cstVerifier.isRespected()) remove = false;
        }
        if(remove) {
            X.domain.splice(i, 1);
            revised = true;
            --i;
        }
    }
    return revised;
}

export const getArcs = (csts = []) => {
    let arcs = [];
    csts.forEach(cst => {
        arcs.push(cst);
        arcs.push(cst.getReverse());
    });
    return arcs;
}

export const ac3 = constraints => {
    const history = [];
    const queue = new Queue();
    const arcs = getArcs(constraints);

    queue.enqueueMany(arcs);
    let prevQueue = [...queue.getItems()]; 
    history.push(snapshot(arcs, null, "Initialisation AC-3", queue.getItems(), prevQueue));
    prevQueue = [...queue.getItems()];
    while (!queue.isEmpty()) {
        const arc = queue.dequeue();
        const X = arc.left;
        const before = [...X.domain];
        if (revise(arc)) {
            if (X.domain.length === 0) {
                history.push(snapshot(arcs, arc, `Échec: domaine vide pour ${X.name}`, queue.getItems(), prevQueue));
                return { result: false, history };
            }
            const removed = before.filter(v => !X.domain.includes(v));
            
            arcs.forEach(neighbor => {
                if (arc.left.id === neighbor.right.id) {
                    queue.enqueue(neighbor);
                }
            });
            history.push(snapshot(arcs, 
                arc,
                `Révision ${arc.left.name} ${arc.op} ${arc.right.name} | supprimé: [${removed}]`,
                queue.getItems(),
                prevQueue
            ));
            prevQueue = [...queue.getItems()];
        } else {
            history.push(snapshot(arcs, 
                arc,
                `Aucun changement sur ${arc.left.name}`,
                queue.getItems(),
                prevQueue
            ));
        }
        prevQueue = [...queue.getItems()];
    }

    history.push(snapshot(arcs, null, "AC-3 terminé", queue, prevQueue));

    return { result: true, history };
};