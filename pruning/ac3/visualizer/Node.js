export class Node {

    static offsetY = 10;

    constructor({ variable, position, couple }) {
        this.variable = variable;
        this.position = position;
        this.couple = couple;
        this.color = "black";
        this.domainStep = [...this.variable.domain];

        if (couple) {
            couple.couple = this;
        }

        this.radius = this.variable.domain.length * 10;
    }

    drawCircle() {
        context.beginPath();
        context.strokeStyle = this.color;
        context.lineWidth = 1;
        context.arc(
            this.position.getX,
            this.position.getY,
            this.radius,
            0,
            Math.PI * 2
        );

        context.stroke();
        context.closePath();
    }

    drawLabel() {
        context.textAlign = "center";
        context.textBaseline = "middle";

        context.font = "16px Arial"

        context.fillStyle = "white";

        context.fillText(
            this.variable.name,
            this.position.getX,
            this.position.getY
        );
    }

    drawDomain() {
        context.textAlign = "center";

        context.font = "12px Arial"
        const text =
            "{" + this.domainStep.join(", ") + "}";

        context.fillText(
            text,
            this.position.getX,
            this.position.getY + this.radius + 15
        );
    }

    draw() {
        this.drawCircle();
        this.drawLabel();
        this.drawDomain();
    }

    update() {
        this.draw();
    }

}