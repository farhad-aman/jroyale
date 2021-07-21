import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class contains a creature on the arena 
 * @version 1.0
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
     * the current image that creature shown based on status
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

    /**
     * the last status to manage images alteration
     * */
    private int oldStatus;

    /**
     *the time that the last old status stand for avoiding images conflict
     * */
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

    /**
     *the origin of the rage place
     * */
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
     * the number of times creature does not moved
     */
    private int moveAvoided = 0;

    /**
     *  the infernos temp target
     */
    private int tempTargetTime;

    /**
     * creates a new creature
     * @param card the card of the creature
     * @param position the temporary position of the creature
     * @param side  = 1 if it is the player army otherwise  = -1
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

        oldStatus = status;
    }

    /**
     * @return the kill target for the creature
     * */
    public Creature getTempTarget() 
    {
        return killTarget;
    }

    /**
     * calculates new damage of the inferno
     * @param level to find the basic damage
     * @param underRage that the inferno has the rage effect
     * @return the hit damage
     */
    public double calculateInfernoDamage(int level, boolean underRage)
    {
        Inferno in = ((Inferno) card);

        if(killTarget == null)
            return 0;
        return (((in.getMaxDamage(level) - in.getMinDamage(level)) / 10.0) *  ((Math.min(tempTargetTime, 10000.0)) / 1000.0) + in.getMinDamage(level)) * (underRage ? 1.4 : 1);
    }

    /**
     * calculates time that inferno attacks to increase damage
     * @param tempTarget for the inferno to kill
     */
    public void addTempTargetTime(Creature tempTarget)
    {
        if(tempTarget == this.killTarget)
            this.tempTargetTime += 1000 / GameManager.FPS;
        else {
            this.killTarget = tempTarget;
            tempTargetTime = 0;
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
     * @param status the new status
     */
    public void setStatus(int status)
    {
        oldStatus = this.status;
        this.status = status;
    }

    /**
     * @return the number that represents the position of the creature on the map
     * */
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
     * @param position the new position
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
     * @param creature to kill
     */
    public void setKillTarget(Creature creature)
    {
        if(card instanceof Building && !(card instanceof Inferno)){
            if (creature == null) {
                oldStatus = status;
                status = side == 1 ? 1 : 2;
            }
            else {
                oldStatus = status;
                status = side == 1 ? 3 : 4;//************************************************************************************************************************
            }
        }
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
     * @param newUnderRage to set the new effect
     */
    public void setUnderRage(boolean newUnderRage)
    {
        this.underRage = newUnderRage;
    }

    /**
     * sets remaining time of rage effect
     * @param rageTimeRemained to calculate the time of the rage effect
     */
    public void setRageTimeRemained(int rageTimeRemained) 
    {
        this.rageTimeRemained = rageTimeRemained;
    }

    /**
     * sets follow target of the creature
     * @param creature the new target to follow
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
     * the main step that is done every frame and uses card step and updates everything
     */
    public void step()
    {
        if(card instanceof Rage && ((Rage)card).getInBattleTime() <= 0)
            ((Rage)card).setInBattleTime(level);

        card.step(this);

        if(card instanceof Cannon || card instanceof Inferno)
        {
            if (lifeTime > 0) 
            {
                lifeTime -= 1000 / GameManager.FPS;
                hp -= (((Building) card).getInitHP(level) / (((Building)card).getInitLifeTime() / 1000.0)) / GameManager.FPS;
                hp = Math.max(hp, 0);
            } 
            else 
            {
                hp = 0;
            }
            if(card instanceof Inferno)
            {
                status = 1;
            }
        }

        if(underRage)
        {
            rageTimeRemained -= 1000 / GameManager.FPS;

            if((ragePosition != null) && (rageTimeRemained <= 0 || position.distance(ragePosition) > 5 * 40)) 
            {
                underRage = false;
                ragePosition = null;
                rageTimeRemained = 0;
            }
        }

        if(card instanceof Princess || card instanceof King)
        {
            if(killTarget == null)
                status = side == 1 ? 1 : 2;
            else
                status = side == 1 ? 3 : 4;
        }//://1->moving to right//2->moving to left//3->fighting to right//4->fighting to left//5->dying to right//6->dying to left//buildings://7->cannon ball//8->cannon turning right//9->cannon turning left//
        else if(card instanceof Cannon)
        {
            if(killTarget == null)
                status = oldStatus == 4 ? 2 : 1;
        }

        if(oldStatus != status)
            statusTime += 1000 / GameManager.FPS;

        if(statusTime >= 500 && status != oldStatus) 
        {
            statusTime = 0;
            oldStatus = status;
        }
        else
            status = oldStatus;
    }

    /**
     * sets the position of rage spell
     * @param ragePosition
     */
    public void setRagePosition(Point2D ragePosition) 
    {
        this.ragePosition = ragePosition;
    }

    /**
     * this creature get hit
     * @param damage to affect the target
     * @return true if target eliminated else false
     */
    public boolean getHit(double damage, int tempStatus)
    {
        hp -= damage;
        if(hp <= 0)
        {
            hp = 0;
            return true;
        }
        return false;
    }

    /**
     * hit another creature
     * @param creature to kill
     * @return true if target creature eliminated else false
     */
    public boolean hit(Creature creature)
    {
        if(hitStepValue >= hitSpeed) 
        {
            card.playAttackSound();
            if(card instanceof Inferno)
            {
                creature.getHit(calculateInfernoDamage(level, underRage), position.getX() < creature.position.getX() ? 6 : 5);
                addTempTargetTime(creature);
            }
            else
            {
                if (card instanceof Troop && ((Troop) card).isAreaSplash()) 
                {
                    if (card instanceof Valkyrie) 
                    {
                        for (Creature c : GameManager.getInstance().getBattle().getArena().getCreatures()) 
                        {
                            if (c.getPosition().distance(position) < 100 && c.getSide() != side && (c.getCard().getType().equals("ground") || c.getCard().getType().equals("building")))
                                c.getHit((damage * (underRage ? 1.4 : 1)), position.getX() < c.position.getX() ? 6 : 5);
                        }
                    } 
                    else 
                    {
                        for (Creature c : GameManager.getInstance().getBattle().getArena().getCreatures())
                            if (c.getPosition().distance(creature.position) < 40 && c.getSide() != side)
                                c.getHit((damage * (underRage ? 1.4 : 1)), position.getX() < c.position.getX() ? 6 : 5);
                    }
                } 
                else
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
     * @param creature  is the destination
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
     * @param creature to consider the destination to
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
     * @param creature the creature to follow
     */
    public void followCreature(Creature creature)
    {
        for(int i = 0;i < calculateRageEffect(speed);i++) 
        {
            if (card instanceof Troop)
                pixelMove();
        }
        if(card instanceof Wizard)
        {
            hitStepValue = 0;
        }
    }

    /**
     * @param speed of the creature
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
     * moves this creature for one pixel
     */
    private void pixelMove()
    {
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

        if (probablePositions.size() != 0 && tempTargetPosition != null) 
        {
            Point2D newPosition = findNearestPosition(tempTargetPosition, probablePositions);
            setPositionAndStatus(newPosition);
            moveAvoided = 0;
        }
        else
        {
            moveAvoided++;
            if(moveAvoided > 5)
                moveAvoided += 10;
        }
    }

    /**
     * sets the new position and update the status
     * @param newPosition to set
     * */
    private void setPositionAndStatus(Point2D newPosition) 
    {
        if (newPosition.getX() < position.getX())
        {
            status = 2;
        }
        else 
        {
            status = 1;
        }
        position = newPosition;
    }

    /**
     * 
     * @return the temporary target position
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

        int ebs = target.updateBridgeStatus();
        bridgeStatus= updateBridgeStatus();

        if(ebs == 0 || ebs == bridgeStatus || (ebs == 1 && bridgeStatus == 4) || (ebs == 4 && bridgeStatus == 1) || (ebs == 3 && bridgeStatus == 6) || (ebs == 6 && bridgeStatus == 3) || card instanceof Dragon)
        {
            return target.position;
        }
        else if(((bridgeStatus == 1 || bridgeStatus == 4) && side == 1) && target.position.getX() > 680)
        {
            if((position.getY() <= 620 && position.getY() >= 540) || (position.getY() <= 180 && position.getY() >= 100))
                return target.getPosition().distance(600, 140) < target.getPosition().distance(600, 580) ? new Point2D(600, position.getY()) : new Point2D(600, position.getY());

            Point2D bridge = target.getPosition().distance(600, 140) < target.getPosition().distance(600, 580) ? new Point2D(600, 140) : new Point2D(600, 580);

            if(position.getY() < bridge.getY())
                return new Point2D(bridge.getX(), bridge.getY() - 20);
            else
                return new Point2D(bridge.getX(), bridge.getY() + 20);
        }
        if(((bridgeStatus == 3 || bridgeStatus == 6) && side == -1) && target.position.getX() < 600)
        {
            if((position.getY() <= 620 && position.getY() >= 540) || (position.getY() <= 180 && position.getY() >= 100)) 
            {
                return target.getPosition().distance(680, 140) < target.getPosition().distance(680, 580) ? new Point2D(680, position.getY()) : new Point2D(680, position.getY());
            }

            Point2D bridge = target.getPosition().distance(680, 140) < target.getPosition().distance(680, 580) ? new Point2D(680, 140) : new Point2D(680, 580);

            if(position.getY() < bridge.getY())
                return new Point2D(bridge.getX(), bridge.getY() - 20);
            else
                return new Point2D(bridge.getX(), bridge.getY() + 20);
        }
        if(bridgeStatus == 2 || bridgeStatus == 5)
        {
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
                    return new Point2D(681, position.getY());
                else if(position.getX() != 600 && side == -1 && target.getPosition().getX() < position.getX())
                    return new Point2D(599, position.getY());
                else
                    return target.position;
            }
        }
        if((bridgeStatus == 1 || bridgeStatus == 4) && side == -1)
        {
            if(target.getPosition().getX() > position.getX() && target.position.getX() > 680)
            {
                System.out.println("incorrect751");
                Point2D newTarget = new Point2D(600, ebs == 2 || ebs == 5 ? target.position.getY() : 140);

                if (position.distance(newTarget) > position.distance(target.position))
                    newTarget = target.position;
                if (position.distance(newTarget) > position.distance(new Point2D(600, ebs == 2 || ebs == 5 ? target.position.getY() : 580)))
                    newTarget = new Point2D(600, ebs == 2 || ebs == 5 ? target.position.getY() : 580);

                return newTarget;
            }
        }
        if((bridgeStatus == 3 || bridgeStatus == 6) && side == 1)
        {
            if(target.getPosition().getX() < position.getX() && target.position.getX() < 600)
            {
                Point2D newTarget = new Point2D(680, ebs == 2 || ebs == 5 ? target.position.getY() : 140);

                if (position.distance(newTarget) > position.distance(target.position))
                    newTarget = target.position;
                if (position.distance(newTarget) > position.distance(new Point2D(680, ebs == 2 || ebs == 5 ? target.position.getY() : 580)))
                    newTarget = new Point2D(680, ebs == 2 || ebs == 5 ? target.position.getY() : 580);

                return newTarget;
            }
        }
        return target.position;
    }

    /**
     * finds the nearest point2D from an arrayList to a source
     * @param source the current position of the creature
     * @param positions the collection of the points to go
     * @return the nearest position
     */
    private Point2D findNearestPosition(Point2D source, ArrayList<Point2D> positions)
    {
        Iterator<Point2D> it = positions.iterator();
        Point2D newPosition = positions.get(0);

        while (it.hasNext())
        {
            Point2D tempPosition = it.next();

            if(!(source.getY() == tempPosition.getY() && source.getX() == tempPosition.getX()) && source.distance(newPosition) > source.distance(tempPosition))
                newPosition = tempPosition;
        }
        return newPosition;
    }

    /**
     * returns the status considering other creatures
     * @param newPosition the probable position to go
     * @return true if newPosition is not to close to other creatures
     */
    private boolean notInViewRange(Point2D newPosition)
    {
        Iterator<Creature> it = GameManager.getInstance().getBattle().getArena().getCreatures().iterator();
        while (it.hasNext())
        {
            Creature c = it.next();

            if(!(card instanceof Giant) && !(c.getCard() instanceof Giant) && c != this && !(c.card instanceof Spell) && newPosition.distance(c.position) < 10 && ((!(card instanceof Dragon) && !(c.getCard() instanceof Dragon)) || ((card instanceof Dragon) && (c.getCard() instanceof Dragon))))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * finds the points in the battle map range
     * @param points the whole selected points
     * @return  the collection with the appropriate points
     */
    public ArrayList<Point2D> inRangePoints(ArrayList<Point2D> points)
    {
        ArrayList<Point2D> validates = new ArrayList<>();
        Iterator<Point2D> it = points.iterator();
        while (it.hasNext())
        {
            Point2D temp = it.next();
            double x = temp.getX();
            double y = temp.getY();

            if(y < 710 && x < 1270 && x > 10 && y > 10)
            {
                if(x < 680 && x > 600)
                {
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