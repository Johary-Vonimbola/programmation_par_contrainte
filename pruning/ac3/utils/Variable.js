class Variable{
    static counter = 0;
    constructor(domain=[]){
        this.id = Variable.counter++;
        this.domain = domain;
    }
}

module.exports = {
    Variable
}