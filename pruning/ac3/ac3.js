class Constraint{
    validOps = [">", ">=", "=", "<", "<=", "!="];

    constructor(left, op, right){
        let i = this.validOps.findIndex(v => v === op);
        if(i < 0) throw new Error("Invalid operation");
        this.left = left;
        this.right = right;
        this.op = op;
    }

    opEquals(op){
        return this.op === op;
    }

    isRespected(){
        if(this.opEquals(">")) return this.left > this.right;
        if(this.opEquals(">=")) return this.left >= this.right;
        if(this.opEquals("<")) return this.left < this.right;
        if(this.opEquals("<=")) return this.left <= this.right;
        if(this.opEquals("=")) return this.left == this.right;
        if(this.opEquals("!=")) return this.left != this.right;
    }

}

class Variable{
    constructor(val, domain=[]){
        this.val = val;
        this.domain = domain;
    }
}

const revise = (X, Y, leftFunc, op, rightFunc) => {
    let revised = false;
    for(let i=0; i<X.domain.length; i++){
        let remove = true;
        for(let j=0; j<Y.domain.length; j++){
            let l = leftFunc(X.domain[i]);
            let r = rightFunc(Y.domain[j]);
            let cst = new Constraint(l, op, r);
            if(cst.isRespected()) remove = false;
        }
        if(remove) {
            X.domain.splice(i, 1);
            --i;
        }
    }
    return revised;
}

let X = new Variable(0, [1,2,3,4]);
let Y = new Variable(0, [10]);

revise(X, Y, x => x, ">", y => y)
revise(Y, X, y => y, "<", x => x)

console.log(X.domain);
console.log(Y.domain);