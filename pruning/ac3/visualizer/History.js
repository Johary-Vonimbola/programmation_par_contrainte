import { Queue } from "../utils/Queue.js";

export class History {

    constructor(arc, message, domainsSnapshot, queue = [], newInQueue = []) {
        this.arc = arc;
        this.message = message;
        this.domains = domainsSnapshot;
        this.queue = queue;
        this.newInQueue = newInQueue;
    }

}