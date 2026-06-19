import { ac3 } from "./utils/Algo.js";
import { Variable } from "./utils/Variable.js";
import { Constraint } from "./utils/Constraints.js";

let X = new Variable([1,2,3,4]);
let Y = new Variable([1,2,3,4]);
let Z = new Variable([1,2,3,4]);

const constraints = [
    new Constraint(X, ">", Y, x => x, y => y),
    new Constraint(Y, ">", Z, y => y, z => z)
];

ac3(constraints);

console.log(X.domain);
console.log(Y.domain);
console.log(Z.domain);