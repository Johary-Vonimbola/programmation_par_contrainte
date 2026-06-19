export class Vector{
    x;
    y;

    get getX(){
        return this.x;
    }
    set setX(x){
        this.x = x;
    }
    get getY(){
        return this.y;
    }
    set setY(y){
        this.y = y;
    }

    constructor(x , y){
        this.setX = x;
        this.setY = y;
    }

    add(vect){
        return new Vector(this.getX + vect.getX , this.getY + vect.getY);
    }
    substract(vect){
        return new Vector(this.getX - vect.getX , this.getY - vect.getY);
    }
    scale(scalar){
        return new Vector(this.getX * scalar , this.getY * scalar);
    }
    magnitude(){ 
        return Math.sqrt(this.getX**2 + this.getY**2);
    }   
    homothetie(value){
        let mag = this.magnitude();
        if(mag != 0){
            return value / mag;
        }
        return 1;
    }
    unit(){
        let k = this.homothetie(1);
        return this.scale(k);
    }
}