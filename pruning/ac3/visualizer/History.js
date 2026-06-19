import { Queue } from "../utils/Queue.js";

export class History {

    constructor(arc, message, domainsSnapshot, queue = []) {
        this.arc = arc;
        this.message = message;
        this.domains = domainsSnapshot;
        this.queue = queue;
    }

}