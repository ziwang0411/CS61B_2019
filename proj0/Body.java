public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    /** Init the Body*/
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    /**take in a Body object and initialize an identical Body object*/
    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }
    /** Return the distance between two Bodys */
    public double calcDistance(Body b) {
        return Math.sqrt (Math.pow((this.xxPos - b.xxPos), 2) + Math.pow((this.yyPos - b.yyPos), 2));
    }
    public double calcForceExertedBy(Body b) {
        return G * this.mass * b.mass /Math.pow((this.calcDistance(b)), 2);
    }
    public double calcForceExertedByX (Body b) {
        return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
    }
    public double calcForceExertedByY (Body b) {
        return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
    }
    public double calcNetForceExertedByX (Body[] allBodys) {
        double sum = 0;
        for (int i = 0; i < allBodys.length; i+=1) {
            if (allBodys[i].equals(this)) {continue;
            }
            sum += this.calcForceExertedByX(allBodys[i]);
        }
        return sum;
    }
    public double calcNetForceExertedByY (Body[] allBodys) {
        double sum = 0;
        for (int i = 0; i < allBodys.length; i+=1) {
            if (allBodys[i].equals(this)) {continue;
            }
            sum += this.calcForceExertedByY(allBodys[i]);
        }
        return sum;
    }
    public void update (double dt, double fX, double fY) {
        this.xxVel += dt*fX/this.mass;
        this.yyVel += dt*fY/this.mass;
        this.xxPos +=dt*this.xxVel;
        this.yyPos +=dt*this.yyVel;
    }
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos,"images/"+ this.imgFileName);

    }
}