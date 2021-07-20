import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class contains a creature on the arena 
 */
public class Creature 
{
    /**
     * the card that creature based on that
     */
    private Card card;

    /**
     * the level of the creature
     */
    private int level;

    /**
     * the center position of the creature 
     */
    private Point2D position;

    /**
     * the current image that creture shown based on status
     */
    private ImageView image;

    /**
     * the side that creature fights for
     * -1 -> belongs to bot , 1 -> belongs to player 
     */
    private int side; 

    /**
     * the current status of the creature
     * 0 -> created recently , 1 -> move to right , 2 -> move to left , 3 -> fighting to right , 4 -> fighting to left , 5 -> dying to right , 6 -> dying to left
     */
    private int status;

    private int oldStatus;

    private int statusTime = 0;

    /**
     * the creature this creature should follow
     */
    private Creature followTarget;

    /**
     * the creature that this creature should kill or be killed
     */
    private Creature killTarget;

    /**
     * the current hp of creature
     */
    private double hp;

    /**
     * the current life time of the creature
     */
    private int lifeTime;

    /**
     * the tick time to hit
     */
    private int hitStepValue;

    /**
     * the hit speed of the creature
     */
    private int hitSpeed;

    /**
     * the moving speed of the creature
     */
    private int speed;

    /**
     * is the creature under rage
     */
    private boolean underRage;

    /**
     * the damage of the creature
     */
    private int damage;

    private Point2D ragePosition;

    /**
     * the remaining time of rage effect
     */
    private int rageTimeRemained;//in milliseconds

    /**
     * demonstrate the creature status toward the bridges in the map from the player vision//0->king or princess//down bridge is the nearer bridge//1->before bridge//2->on the bridge//3->after the bridge//top bridge is the nearer bridge//4->before bridge//5->on the bridge//6->after the bridge
     * */
    private int bridgeStatus;

    /**
     * the number of times creature doesnt moved
     */
    private int moveAvoided = 0;

    /**
     * creates a new creature
     * @param card
     * @param position
     * @param side
     */
    public Creature(Card card, int level, Point2D position, int side) 
    {
        this.card = card;
        this.level = level;
        this.position = position;
        this.side = side;
        if(this.card instanceof King || this.card instanceof Princess)
        {
            if(this.side == 1)
            {
                this.status = 1;
            }
            else
            {
                this.status = 2;
            }
        }
        else
        {
            this.status = 1;
        }
        oldStatus = status;

        underRage = false;
        ragePosition = null;
        rageTimeRemained = 0;

        speed = card.getSpeed();

        hitSpeed = card.getHitSpeed();
        hitStepValue = 0;

        damage = card.getDamage(level);

        updateBridgeStatus();

        if(card instanceof Building)
        {
            this.hp = ((Building)card).getInitHP(level);
            this.lifeTime = ((Building)card).getInitLifeTime();
        }
        else if(card instanceof Troop)
        {
            this.hp = ((Troop)card).getInitHP(level);
            this.lifeTime = 1000000;
        }
    }

    /**
     * @return the side of creature
     */
    public int getSide() 
    {
        return side;
    }

    /**
     * @return the level of creature
     */
    public int getLevel() 
    {
        return level;
    }

    /**
     * @return is creature under rage
     */
    public boolean isUnderRage() 
    {
        return underRage;
    }

    /**
     * @return the card of the creature
     */
    public Card getCard() 
    {
        return card;
    }

    /**
     * @return remaining time of rage effect
     */
    public int getRageTimeRemained() 
    {
        return rageTimeRemained;
    }

    /**
     * @return the position of creature
     */
    public Point2D getPosition() 
    {
        return position;
    }

    /**
     * @return current image of the creature
     */
    public ImageView getImage() 
    {
        return image;
    }

    /**
     * sets the status of the creature
     * @param status
     */
    public void setStatus(int status)
    {
        this.status = status;
    }

    public int updateBridgeStatus() 
    {
        if(position.getY() >= 360)
        {
            if(position.getX() <= 600 && (position.getY() > 620 || position.getY() < 540))
                bridgeStatus = 1;
            else if(position.getX() >= 600 && position.getX() <= 680 && position.getY() <= 620 && position.getY() >= 540)
                bridgeStatus = 2;
            else
                bridgeStatus = 3;
        }
        else
        {
            if(position.getX() <= 600 && (position.getY() < 100 || position.getY() > 180))
                bridgeStatus = 4;
            else if(position.getX() >= 600 && position.getX() <= 680 && position.getY() <= 180 && position.getY() >= 100)
                bridgeStatus = 5;
            else
                bridgeStatus = 6;
        }
        if(card instanceof King || card instanceof Princess)
            bridgeStatus = 0;

        return bridgeStatus;
    }

    /**
     * sets the position of the creature
     * @param position
     */
    public void setPosition(Point2D position) 
    {
        this.position = position;
    }

    /**
     * @return the kill target of the creature
     */
    public Creature getKillTarget()
    {
        return killTarget;
    }

    /**
     * sets the kill target of the creature
     * @param creature
     */
    public void setKillTarget(Creature creature)
    {
        this.killTarget = creature;
    }

    /**
     * @return the follow target of the creature
     */
    public Creature getFollowTarget()
    {
        return followTarget;
    }

    /**
     * @return the status of the creature
     */
    public int getStatus() 
    {
        return status;
    }

    /**
     * sets under rage field of the creature
     * @param newUnderRage
     */
    public void setUnderRage(boolean newUnderRage)
    {
        this.underRage = newUnderRage;
    }

    /**
     * sets remaining time of rage effect
     * @param rageTimeRemained
     */
    public void setRageTimeRemained(int rageTimeRemained) 
    {
        this.rageTimeRemained = rageTimeRemained;
    }

    /**
     * sets follow target of the creature
     * @param creature
     */
    public void setFollowTarget(Creature creature)
    {
        this.followTarget = creature;
    }

    /**
     * @return the hp of the creature
     */
    public double getHP()
    {
        return hp;
    }

    /**
     * the main step that done every frame and uses card step
     */
    public void step()
    {
        card.step(this);

        if(card instanceof Cannon || card instanceof Inferno){
            if (lifeTime > 0) {
                lifeTime -= 1000 / GameManager.FPS;
                hp -= (((Building) card).getInitHP(level) / (((Building)card).getInitLifeTime() / 1000.0)) / GameManager.FPS;
                hp = Math.max(hp, 0);
            } else {
                hp = 0;
            }
            if(card instanceof Inferno){
                status = 1;
            }
        }

        if(underRage)
        {
            rageTimeRemained -= 1000 / GameManager.FPS;

            if((ragePosition != null) && (rageTimeRemained <= 0 || position.distance(ragePosition) > 5 * 40)) {
                underRage = false;
                ragePosition = null;
                rageTimeRemained = 0;
            }
        }

        if(oldStatus != status)
            statusTime += 1000 / GameManager.FPS;

        if(statusTime >= 500 && status != oldStatus) {
            statusTime = 0;
            oldStatus = status;
        }
        else
            status = oldStatus;
    }

    public void setRagePosition(Point2D ragePosition) {
        this.ragePosition = ragePosition;
    }

    /**
     * this creature get hit
     * @param damage
     * @return true if target eliminated else false
     */
    public boolean getHit(double damage, int tempStatus)
    {
        hp -= damage;
        if(hp <= 0)
        {
//            status = tempStatus;

            hp = 0;
            return true;
        }
        return false;
    }

    /**
     * hit another creature
     * @param creature
     * @return true if target creature eliminated else false
     */
    public boolean hit(Creature creature)
    {
        if(hitStepValue >= hitSpeed) 
        {
            card.playAttackSound();
            if(card instanceof Inferno){
                creature.getHit(((Inferno) card).calculateInfernoDamage(level, underRage), position.getX() < creature.position.getX() ? 6 : 5);
                ((Inferno) card).addTempTargetTime(creature);
            }
            else{
                if (card instanceof Troop && ((Troop) card).isAreaSplash()) {
                    if (card instanceof Valkyrie) {
                        for (Creature c : GameManager.getInstance().getBattle().getArena().getCreatures()) {
                            if (c.getPosition().distance(position) < 100 && c.getSide() != side && (c.getCard().getType().equals("ground") || c.getCard().getType().equals("building")))
                                c.getHit((damage * (underRage ? 1.4 : 1)), position.getX() < c.position.getX() ? 6 : 5);
                        }
                    } else {
                        for (Creature c : GameManager.getInstance().getBattle().getArena().getCreatures())
                            if (c.getPosition().distance(creature.position) < 40 && c.getSide() != side)
                                c.getHit((damage * (underRage ? 1.4 : 1)), position.getX() < c.position.getX() ? 6 : 5);
                    }
                } else
                    creature.getHit((damage * (underRage ? 1.4 : 1)), position.getX() < creature.position.getX() ? 6 : 5);
            }
            hitStepValue = 0;
        }
        else
            hitStepValue += 1000 / GameManager.FPS;

        return creature.isEliminated();
    }

    /**
     * @return is creature eliminated
     */
    public boolean isEliminated()
    {
        if(hp == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * @param creature
     * @return the distance between this creature and given creature
     */
    public int getDistance(Creature creature)
    {
        return (int)position.distance(creature.position);
    }

    /**
     * @return finds nearest creature that is in creature targets
     */
    public Creature findNearestValidCreature()
    {
        return card.findNearestValidCreature(this);
    }

    /**
     * @param creature
     * @return is given creature in range of this creature
     */
    public boolean isCreatureInRange(Creature creature)
    {
        if(creature != null)
        {
            double distance = creature.getPosition().distance(position);

            if (distance <= card.getRange() * 40 || (creature.getCard() instanceof King && distance <= (card.getRange() + 1) * 40) || (creature.getCard() instanceof Princess && distance <= (card.getRange() + 0.5) * 40) || ((creature.getCard() instanceof Inferno || creature.getCard() instanceof Cannon) && distance <= (card.getRange() + 0.5) * 40))
                return true;
        }
        return false;
    }

    /**
     * this method is the main method of moving creatures
     * @param creature
     */
    public void followCreature(Creature creature)
    {
        for(int i = 0;i < calculateRageEffect(speed);i++) 
        {
//            System.out.println("position :" + position.getX() + ", " + position.getY());
            //System.out.println(card.getId() + " is moving");
            if (card instanceof Troop)
                pixelMove();
        }
        if(card instanceof Wizard)
        {
            hitStepValue = 0;
        }
    }

    /**
     * @param speed
     * @return new speed after rage effect
     */
    private int calculateRageEffect(int speed) 
    {
        if(underRage && (rageTimeRemained % 200 == 0))
        {
            return 3 * speed * (20 / GameManager.FPS);
        }
        else
            return speed * (20 / GameManager.FPS);
    }

    /**
     * 
     */
    private void pixelMove()
    {// System.out.println("pixel move working346");
//        if(moveAvoided == 0){
            ArrayList<Point2D> probablePositions = new ArrayList<>();

            if (notInViewRange(position.add(side * (moveAvoided + 1), 0)))
                probablePositions.add(position.add(side * (moveAvoided + 1), 0));
            if (notInViewRange(position.add(side * (moveAvoided + 1), -1 * (moveAvoided + 1))))
                probablePositions.add(position.add(side * (moveAvoided + 1), -1 * (moveAvoided + 1)));
            if (notInViewRange(position.add(side * (moveAvoided + 1), (moveAvoided + 1))))
                probablePositions.add(position.add(side * (moveAvoided + 1), (moveAvoided + 1)));
            if (notInViewRange(position.add(0,  (moveAvoided + 1))))
                probablePositions.add(position.add(0, (moveAvoided + 1)));
            if (notInViewRange(position.add(0, -1 * (moveAvoided + 1))))
                probablePositions.add(position.add(0, -1 * (moveAvoided + 1)));
            if (notInViewRange(position.add(side * -1 * (moveAvoided + 1), -1 * (moveAvoided + 1))))
                probablePositions.add(position.add(side * -1 * (moveAvoided + 1), -1 * (moveAvoided + 1)));
            if (notInViewRange(position.add(side * -1 * (moveAvoided + 1), 0)))
                probablePositions.add(position.add(side * -1 * (moveAvoided + 1), 0));
            if (notInViewRange(position.add(side * -1 * (moveAvoided + 1),  (moveAvoided + 1))))
                probablePositions.add(position.add(side * -1 * (moveAvoided + 1), (moveAvoided + 1)));

            Point2D tempTargetPosition = findTempTargetPosition();
            probablePositions = inRangePoints(probablePositions);

            if (probablePositions.size() != 0 && tempTargetPosition != null) {//System.out.println("moving successfully369");
                Point2D newPosition = findNearestPosition(tempTargetPosition, probablePositions);
                setPositionAndStatus(newPosition);//System.out.println("moving successfully finished?!?!?!?!?!?371");
                moveAvoided = 0;
            }
//            else if(tempTargetPosition != null)
//            {
//            ArrayList<Point2D> allPositions = new ArrayList<>();
//
//            allPositions.add(position.add(side, 0));
//            allPositions.add(position.add(side, 1));
//            allPositions.add(position.add(side, -1));
//            allPositions.add(position.add(0, 1));
//            allPositions.add(position.add(0, -1));
//            allPositions.add(position.add(side * -1, -1));
//            allPositions.add(position.add(side * -1, 0));
//            allPositions.add(position.add(side * -1, 1));
//
//            allPositions = inRangePoints(allPositions);
//
//            Point2D newPosition = findNearestPosition(tempTargetPosition, allPositions);
//            setPositionAndStatus(newPosition);
////            System.out.println("moving struggle390");
//            moveCreaturesBackward(findInViewRangeCreatures(position));
//            }
//        }
        else
        {
            System.out.println("move avoided :" + moveAvoided);
            moveAvoided++;
        }
    }

    /**
     * sets the new position and update the status
     * @param newPosition to set
     * */
    private void setPositionAndStatus(Point2D newPosition) 
    {
            if (newPosition.getX() < position.getX())
                status = 2;
            else {
                status = 1;
            }
            position = newPosition;
    }

    /**
     * 
     * @return
     */
    private Point2D findTempTargetPosition() 
    {
        Creature target;
        if(killTarget == null)
        {
            if(followTarget == null)
                target = followTarget = findNearestValidCreature();
            else
                target = findNearestValidCreature();
        }
        else
            target = killTarget;

//        System.out.println("finding temp target position417");
        int ebs = target.updateBridgeStatus();
        bridgeStatus= updateBridgeStatus();

        if(ebs == 0 || ebs == bridgeStatus || (ebs == 1 && bridgeStatus == 4) || (ebs == 4 && bridgeStatus == 1) || (ebs == 3 && bridgeStatus == 6) || (ebs == 6 && bridgeStatus == 3) || card instanceof Dragon)
        {
            return target.position;
        }
        else if((bridgeStatus == 1 || bridgeStatus == 4) && side == 1)
        {
            if((position.getY() <= 620 && position.getY() >= 540) || (position.getY() <= 180 && position.getY() >= 100))
                return target.getPosition().distance(600, 140) < target.getPosition().distance(600, 580) ? new Point2D(600, position.getY()) : new Point2D(600, position.getY());

            Point2D bridge = target.getPosition().distance(600, 140) < target.getPosition().distance(600, 580) ? new Point2D(600, 140) : new Point2D(600, 580);

            if(position.getY() < bridge.getY())
                return new Point2D(bridge.getX(), bridge.getY() - 20);
            else
                return new Point2D(bridge.getX(), bridge.getY() + 20);
        }
        else if((bridgeStatus == 3 || bridgeStatus == 6) && side == -1)
        {
            if((position.getY() <= 620 && position.getY() >= 540) || (position.getY() <= 180 && position.getY() >= 100))
                return target.getPosition().distance(680, 140) < target.getPosition().distance(680, 580) ? new Point2D(680, position.getY()) : new Point2D(680, position.getY());

            Point2D bridge = target.getPosition().distance(680, 140) < target.getPosition().distance(680, 580) ? new Point2D(680, 140) : new Point2D(680, 580);

            if(position.getY() < bridge.getY())
                return new Point2D(bridge.getX(), bridge.getY() - 20);
            else
                return new Point2D(bridge.getX(), bridge.getY() + 20);
        }
        else if(bridgeStatus == 2 || bridgeStatus == 5)
        { 
            //System.out.println(card.getId() + "\tbridge status :" + bridgeStatus);
            if(ebs == 2 || (ebs == 5))
            {
                if ((target.position.getX() + position.getX()) / 2 < 640) 
                {
                    return new Point2D(600, position.getY());
                } 
                else if ((target.position.getX() + position.getX()) / 2 > 640) 
                {
                    return new Point2D(680, position.getY());
                } 
                else 
                {
                    Random rand = new Random();
                    return rand.nextInt() % 2 == 0 ? new Point2D(600, position.getY()) : new Point2D(680, position.getY());
                }
            }
            else 
            {
                if(position.getX() != 680 && side == 1 && target.getPosition().getX() > position.getX())
                    return new Point2D(680, position.getY());
                else if(position.getX() != 600 && side == -1 && target.getPosition().getX() < position.getX())
                    return new Point2D(600, position.getY());
                else
                    return target.position;
            }
        }
        else if((bridgeStatus == 1 || bridgeStatus == 4) && side == -1)
        {
            Point2D newTarget = new Point2D(600, ebs == 2 || ebs == 5 ? target.position.getY() : 140);

            if(target.getPosition().getX() > position.getX())
            {
                if (position.distance(newTarget) > position.distance(target.position))
                    newTarget = target.position;
                if (position.distance(newTarget) > position.distance(new Point2D(600, ebs == 2 || ebs == 5 ? target.position.getY() : 580)))
                    newTarget = new Point2D(600, ebs == 2 || ebs == 5 ? target.position.getY() : 580);

                return newTarget;
            }
        }
        else if((bridgeStatus == 3 || bridgeStatus == 6) && side == 1)
        {
            Point2D newTarget = new Point2D(680, ebs == 2 || ebs == 5 ? target.position.getY() : 140);

            if(target.getPosition().getX() < position.getX())
            {
                if (position.distance(newTarget) > position.distance(target.position))
                    newTarget = target.position;
                if (position.distance(newTarget) > position.distance(new Point2D(680, ebs == 2 || ebs == 5 ? target.position.getY() : 580)))
                    newTarget = new Point2D(680, ebs == 2 || ebs == 5 ? target.position.getY() : 580);

                return newTarget;
            }
        }
//        System.out.println(card.getId() + "\t?!?!?!?!?!?!");
        return target.position;
    }
//
//    private void moveCreaturesBackward(ArrayList<Creature> inRanges)
//    {
//        System.out.println("move backward used");
//        for(Creature c : inRanges)
//        if(!(c.getCard() instanceof Building))
//        {//System.out.println("moving other creatures backward458");
//            ArrayList<Point2D> probablePositions = new ArrayList<>();
//
//            if (notInViewRange(c.getPosition().add(side, 0)))
//                probablePositions.add(c.getPosition().add(side, 0));
//            if (notInViewRange(c.getPosition().add(side, -1)))
//                probablePositions.add(c.getPosition().add(side, -1));
//            if (notInViewRange(c.getPosition().add(side, 1)))
//                probablePositions.add(c.getPosition().add(side, 1));
//            if (notInViewRange(c.getPosition().add(0, 1)))
//                probablePositions.add(c.getPosition().add(0, 1));
//            if (notInViewRange(c.getPosition().add(0, -1)))
//                probablePositions.add(c.getPosition().add(0, -1));
//            if (notInViewRange(c.getPosition().add(side * -1, -1)))
//                probablePositions.add(c.getPosition().add(side * -1, -1));
//            if (notInViewRange(c.getPosition().add(side * -1, 0)))
//                probablePositions.add(c.getPosition().add(side * -1, 0));
//            if (notInViewRange(c.getPosition().add(side * -1, 1)))
//                probablePositions.add(c.getPosition().add(side * -1, 1));
//
//            Point2D tempTargetPosition = (killTarget == null ? followTarget : killTarget).getPosition();
//            probablePositions = inRangePoints(probablePositions);
//
//            if (probablePositions.size() != 0 && tempTargetPosition != null) {//System.out.println("moving creature backward successfully481");
//                Point2D newPosition = findNearestPosition(tempTargetPosition, probablePositions);
//                setPositionAndStatus(newPosition);
//            }
//        }
//    }

    /**
     * 
     * @param position
     * @return
     */
    private ArrayList<Creature> findInViewRangeCreatures(Point2D position) 
    {
        ArrayList<Creature> inRange = new ArrayList<>();
        Iterator<Creature> it = GameManager.getInstance().getBattle().getArena().getCreatures().iterator();
//        System.out.println("finding creatures in view overlap492");
        while (it.hasNext())
        {
            Creature temp = it.next();

            if(temp != this && position.distance(temp.position) < 10 && ((!(card instanceof Dragon) && !(temp.getCard() instanceof Dragon)) || ((card instanceof Dragon) && (temp.getCard() instanceof Dragon))))
                inRange.add(temp);
        }
        return inRange;
    }

    /**
     * 
     * @param source
     * @param positions
     * @return
     */
    private Point2D findNearestPosition(Point2D source, ArrayList<Point2D> positions)
    {
        Iterator<Point2D> it = positions.iterator();
        Point2D newPosition = positions.get(0);
//        System.out.println("finding the nearest position to a source507");
        while (it.hasNext())
        {
            Point2D tempPosition = it.next();

            if(!(source.getY() == tempPosition.getY() && source.getX() == tempPosition.getX()) && source.distance(newPosition) > source.distance(tempPosition))
                newPosition = tempPosition;
        }
        return newPosition;
    }

    /**
     * 
     * @param newPosition
     * @return
     */
    private boolean notInViewRange(Point2D newPosition)
    {
        Iterator<Creature> it = GameManager.getInstance().getBattle().getArena().getCreatures().iterator();
//        System.out.println("checking the creature position accuracy521");
        while (it.hasNext())
        {
            Creature c = it.next();

            if(c != this && !(c.card instanceof Spell) && newPosition.distance(c.position) < 10 && ((!(card instanceof Dragon) && !(c.getCard() instanceof Dragon)) || ((card instanceof Dragon) && (c.getCard() instanceof Dragon)))){
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @param points
     * @return
     */
    public ArrayList<Point2D> inRangePoints(ArrayList<Point2D> points)
    {
        ArrayList<Point2D> validates = new ArrayList<>();
        Iterator<Point2D> it = points.iterator();
//        System.out.println("finding in range positions536");
        while (it.hasNext())
        {
            Point2D temp = it.next();
            double x = temp.getX();
            double y = temp.getY();

            if(y < 710 && x < 1270 && x > 10 && y > 10)
            {
                if(x < 680 && x > 600){
                    if((y >= 100 && y <= 180) || (y >= 540 && y <= 620))
                        validates.add(temp);
                    else if(card instanceof Dragon)
                        validates.add(temp);
                }
                else
                    validates.add(temp);
            }
        }
        return validates;
    }
}