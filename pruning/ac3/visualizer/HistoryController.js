export class HistoryController {

    constructor(history) {
        this.history = history;
        this.index = 0;
    }

    current() {
        return this.history[this.index];
    }

    next() {
        if (this.index < this.history.length - 1) {
            this.index++;
        }
        return this.current();
    }

    prev() {
        if (this.index > 0) {
            this.index--;
        }
        return this.current();
    }

}