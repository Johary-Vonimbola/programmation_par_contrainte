validOps = [">", ">=", "=", "<", "<=", "!="];
class Constraint{

    constructor(left, op, right, leftFunc, rightFunc){
        let i = validOps.findIndex(v => v === op);
        if(i < 0) throw new Error("Invalid operation");
        this.left = left;
        this.right = right;
        this.op = op;
        this.leftFunc = leftFunc;
        this.rightFunc = rightFunc;
    }

    getReverseOp(){
        if(this.op === ">") return "<";
        else if(this.op === ">=") return "<=";
        else if(this.op === "<") return ">";
        else if(this.op === "<=") return ">=";
        else return this.op;
    }

    getReverse(){
        return new Constraint(this.right, this.getReverseOp(), this.left, this.rightFunc, this.leftFunc);
    }

}

class ConstraintVerifier{

    constructor(leftVal, op, rightVal){
        let i = validOps.findIndex(v => v === op);
        if(i < 0) throw new Error("Invalid operation");
        this.left = leftVal;
        this.right = rightVal;
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

module.exports = {
    Constraint,
    ConstraintVerifier
}