const { ConstraintVerifier } = require("./Contraints");
const { Queue } = require("./Queue");

const revise = cst => {
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

const getArcs = (csts = []) => {
    let arcs = [];
    csts.forEach(cst => {
        arcs.push(cst);
        arcs.push(cst.getReverse());
    });
    return arcs;
}

const ac3 = constraints => {
    const queue = new Queue();
    const arcs = getArcs(constraints);
    queue.enqueueMany(arcs);

    while(!queue.isEmpty()){
        const arc = queue.dequeue();
        const X = arc.left, Y = arc.right;
        if(revise(arc)){
            if(X.domain.length == 0) return false;
            arcs.forEach(neighbor => {
                if(arc.left.id === neighbor.right.id){
                    queue.enqueue(neighbor);
                }
            });
        }
    }
    return true;
}

module.exports = {
    revise,
    getArcs,
    ac3
}