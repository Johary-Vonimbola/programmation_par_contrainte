export class Variable{
    static counter = 0;
    constructor(domain=[], name="?"){
        this.id = Variable.counter++;
        this.domain = domain;
        this.name = name;
    }
}
