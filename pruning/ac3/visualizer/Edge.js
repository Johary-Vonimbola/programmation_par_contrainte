export class Edge {
    constructor(from, to) {
        this.from = from;
        this.to = to;
        this.progress = 0;
    }

    draw(ctx, active = false, direction = 1) {
        const x1 = this.from.position.getX;
        const y1 = this.from.position.getY;
        const x2 = this.to.position.getX;
        const y2 = this.to.position.getY;

        const dx = x2 - x1;
        const dy = y2 - y1;

        const length = Math.sqrt(dx * dx + dy * dy);

        const px = -dy / length;
        const py = dx / length;

        const offset = 10 * direction;

        const ox = px * offset;
        const oy = py * offset;

        const startX = x1 + ox;
        const startY = y1 + oy;
        const endX = x2 + ox;
        const endY = y2 + oy;

        ctx.beginPath();
        ctx.strokeStyle = active ? "orange" : "#4aa3ff";
        ctx.lineWidth = active ? 3 : 1;

        ctx.moveTo(startX, startY);
        ctx.lineTo(endX, endY);
        ctx.stroke();

        const angle = Math.atan2(endY - startY, endX - startX);
        const size = 10;
        const spread = Math.PI / 5;

        ctx.beginPath();
        ctx.moveTo(endX, endY);
        ctx.lineTo(
            endX - size * Math.cos(angle - spread),
            endY - size * Math.sin(angle - spread)
        );
        ctx.lineTo(
            endX - size * Math.cos(angle + spread),
            endY - size * Math.sin(angle + spread)
        );
        ctx.closePath();

        ctx.fillStyle = active ? "orange" : "#4aa3ff";
        ctx.fill();
    }

}