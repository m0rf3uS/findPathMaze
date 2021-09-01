import java.util.ArrayList;

public abstract class AbstractFindPathInputReader {

    private Position currentPosition;
    private ArrayList<Direction> stepsTaken = new ArrayList<Direction>();

    protected void findPath(Maze maze) {
        this.currentPosition = maze.getStartingPosition();
        boolean madeStep;
        Direction previousDirection = Direction.n;

        while (this.currentPosition.getRow() != maze.getTargetPosition().getRow() || this.currentPosition.getColumn() != maze.getTargetPosition().getColumn()) {
            madeStep = false;
            //System.out.println("current row: " + currentPosition.getRow() + " , current column: " + currentPosition.getColumn());
            if (this.currentPosition.getRow() < maze.getTargetPosition().getRow()) {

                if (canMoveTo(this.getElementInDirection(Direction.d, maze)) && previousDirection != Direction.d) {
                    previousDirection = Direction.u;
                    this.currentPosition.setRow(this.currentPosition.getRow()+1);
                    this.stepsTaken.add(Direction.d);
                    madeStep = true;
                    System.out.print("d,");
                } else if (this.currentPosition.getColumn() < maze.getTargetPosition().getColumn() && canMoveTo(this.getElementInDirection(Direction.r, maze)) && previousDirection != Direction.r) { // posun o riadok nizsie je blocked tak pokus o optimalny posun v stlpci
                    previousDirection = Direction.l;
                    this.currentPosition.setColumn(this.currentPosition.getColumn()+1);
                    this.stepsTaken.add(Direction.r);
                    madeStep = true;
                    System.out.print("r,");
                } else if (this.currentPosition.getColumn() > maze.getTargetPosition().getColumn() && canMoveTo(this.getElementInDirection(Direction.l, maze)) && previousDirection != Direction.l) {
                    previousDirection = Direction.r;
                    this.currentPosition.setColumn(this.currentPosition.getColumn()-1);
                    this.stepsTaken.add(Direction.l);
                    madeStep = true;
                    System.out.print("l,");
                }

            } else if (this.currentPosition.getRow() > maze.getTargetPosition().getRow()) {

                if (canMoveTo(this.getElementInDirection(Direction.u, maze)) && previousDirection != Direction.u) {
                    previousDirection = Direction.d;
                    this.currentPosition.setRow(this.currentPosition.getRow()-1);
                    this.stepsTaken.add(Direction.u);
                    madeStep = true;
                    System.out.print("u,");
                } else if (this.currentPosition.getColumn() < maze.getTargetPosition().getColumn() && canMoveTo(this.getElementInDirection(Direction.r, maze)) && previousDirection != Direction.r) { // posun o riadok nizsie je blocked tak pokus o optimalny posun v stlpci
                    previousDirection = Direction.l;
                    this.currentPosition.setColumn(this.currentPosition.getColumn()+1);
                    this.stepsTaken.add(Direction.r);
                    madeStep = true;
                    System.out.print("r,");
                } else if (this.currentPosition.getColumn() > maze.getTargetPosition().getColumn() && canMoveTo(this.getElementInDirection(Direction.l, maze)) && previousDirection != Direction.l) {
                    previousDirection = Direction.r;
                    this.currentPosition.setColumn(this.currentPosition.getColumn()-1);
                    this.stepsTaken.add(Direction.l);
                    madeStep = true;
                    System.out.print("l,");
                }
            } 

            if (this.currentPosition.getColumn() < maze.getTargetPosition().getColumn() && !madeStep) {

                if (canMoveTo(this.getElementInDirection(Direction.r, maze)) && previousDirection != Direction.r) {
                    previousDirection = Direction.l;
                    this.currentPosition.setColumn(this.currentPosition.getColumn()+1);
                    this.stepsTaken.add(Direction.r);
                    madeStep = true;
                    System.out.print("r,");
                }

            } else if (this.currentPosition.getColumn() > maze.getTargetPosition().getColumn() && madeStep) {

                if (canMoveTo(this.getElementInDirection(Direction.l, maze)) && previousDirection != Direction.l) {
                    previousDirection = Direction.r;
                    this.currentPosition.setColumn(this.currentPosition.getColumn()-1);
                    this.stepsTaken.add(Direction.l);
                    madeStep = true;
                    System.out.print("l,");
                }

            }
            //optimalna cesta je blocked
            if (!madeStep) {
                Direction directionTo = directionToMoveTo(previousDirection, idealRowDirection(maze.getTargetPosition()), idealColumnDirection(maze.getTargetPosition()), maze);
                switch (directionTo) {
                    case d:
                    if (canMoveTo(this.getElementInDirection(Direction.d, maze))) {
                        previousDirection = Direction.u;
                        this.currentPosition.setRow(this.currentPosition.getRow()+1);
                        this.stepsTaken.add(Direction.d);
                        System.out.print("d,");
                        break;
                    } else {
                        previousDirection = Direction.d;
                        this.currentPosition.setRow(this.currentPosition.getRow()-1);
                        this.stepsTaken.add(Direction.u);
                        System.out.print("u,"); // returning
                        break;
                    }
                    case u:
                    if (canMoveTo(this.getElementInDirection(Direction.u, maze))) {
                        previousDirection = Direction.d;
                        this.currentPosition.setRow(this.currentPosition.getRow()-1);
                        this.stepsTaken.add(Direction.u);
                        System.out.print("u,");
                        break;
                    } else {
                        previousDirection = Direction.u;
                        this.currentPosition.setRow(this.currentPosition.getRow()+1);
                        this.stepsTaken.add(Direction.d);
                        System.out.print("d,"); // returning
                        break;
                    }
                    case r:
                    if (canMoveTo(this.getElementInDirection(Direction.r, maze))) {
                        previousDirection = Direction.l;
                        this.currentPosition.setColumn(this.currentPosition.getColumn()+1);
                        this.stepsTaken.add(Direction.r);
                        System.out.print("r,");
                        break;
                    } else {
                        previousDirection = Direction.r;
                        this.currentPosition.setColumn(this.currentPosition.getColumn()-1);
                        this.stepsTaken.add(Direction.l);
                        System.out.print("l,"); // returning
                        break;
                    }
                    case l:
                    if (canMoveTo(this.getElementInDirection(Direction.l, maze))) {
                        previousDirection = Direction.r;
                        this.currentPosition.setColumn(this.currentPosition.getColumn()-1);
                        this.stepsTaken.add(Direction.l);
                        System.out.print("l,");
                        break;
                    } else {
                        previousDirection = Direction.l;
                        this.currentPosition.setColumn(this.currentPosition.getColumn()+1);
                        this.stepsTaken.add(Direction.r);
                        System.out.print("r,"); // returning
                        break;
                    }
                    default:
                        break;

                }
            }
        }
        //System.out.print("found position: " + currentPosition.getRow() + ", " + currentPosition.getColumn());
    }

    private boolean canMoveTo(Element element) {
        if (element == null) {
            return false;
        }
        return element.getState() != State.BLOCKED;
    }

    private Element getElementInDirection(Direction direction, Maze maze) {
        switch (direction) {
            case u:
                if (this.currentPosition.getRow() == 0) {
                 //   throw new Exception("cant move in this direction");
                 return null;
                }
                return maze.getElement(this.currentPosition.getRow()-1, this.currentPosition.getColumn());
            case d:
                if (this.currentPosition.getRow() == maze.getMazeElements().size()-1) {
                 //   throw new Exception("cant move in this direction");
                 return null;
                }
                return maze.getElement(this.currentPosition.getRow()+1, this.currentPosition.getColumn());
            case r:
                if (this.currentPosition.getRow() == maze.getMazeElements().get(0).size()-1) {
                //    throw new Exception("cant move in this direction");
                return null;
                }
                return maze.getElement(this.currentPosition.getRow(), this.currentPosition.getColumn()+1);
            case l:
                if (this.currentPosition.getColumn() == 0) {
                 //   throw new Exception("cant move in this direction");
                 return null;
                }
                return maze.getElement(this.currentPosition.getRow(), this.currentPosition.getColumn()-1);

            default:
                return maze.getElement(this.currentPosition);
        }
    }


    private Direction directionToMoveTo(Direction previousDirection, Direction idealRowDirection, Direction idealColumnDirection, Maze maze) {
        Direction resultDirection = previousDirection;
        switch (previousDirection) {
            case d:
                if (idealColumnDirection == Direction.n) {
                        if (canMoveTo(this.getElementInDirection(Direction.l, maze))) {
                            resultDirection = Direction.l;
                        } else {
                            resultDirection = Direction.r;
                        }
                } else if (idealColumnDirection == Direction.l) {
                    if (idealRowDirection == Direction.u) {
                        resultDirection = Direction.r;
                    } else if (idealRowDirection == Direction.n) {
                        if (canMoveTo(this.getElementInDirection(Direction.u, maze))) {
                            resultDirection = Direction.u;
                        } else {
                            resultDirection = Direction.r;
                        }
                    } else if (idealRowDirection == Direction.d) {
                        if (canMoveTo(this.getElementInDirection(Direction.u, maze))) {
                            resultDirection = Direction.u;
                        } else {
                            resultDirection = Direction.r;
                        }
                    }
                } else if (idealColumnDirection == Direction.r) {
                    if (idealRowDirection == Direction.u) {
                        resultDirection = Direction.l;
                    } else if (idealRowDirection == Direction.n) {
                        if (canMoveTo(this.getElementInDirection(Direction.u, maze))) {
                            resultDirection = Direction.u;
                        } else {
                            resultDirection = Direction.l;
                        }
                    } else if (idealRowDirection == Direction.d) {
                        if (canMoveTo(this.getElementInDirection(Direction.u, maze))) {
                            resultDirection = Direction.u;
                        } else {
                            resultDirection = Direction.l;
                        }
                    }
                }
            break;
            case u:
                if (idealColumnDirection == Direction.n) {
                    if (canMoveTo(this.getElementInDirection(Direction.l, maze))) {
                        resultDirection = Direction.l;
                    } else {
                        resultDirection = Direction.r;
                    }
                
                } else if (idealColumnDirection == Direction.l) {
                    if (idealRowDirection == Direction.d) {
                        resultDirection = Direction.r;
                    } else if (idealRowDirection == Direction.u) {
                        if (canMoveTo(this.getElementInDirection(Direction.d, maze))) {
                            resultDirection = Direction.d;
                        } else {
                            resultDirection = Direction.r;
                        }
                    } else if (idealRowDirection == Direction.n) {
                        if (canMoveTo(this.getElementInDirection(Direction.d, maze))) {
                            resultDirection = Direction.d;
                        } else {
                            resultDirection = Direction.r;
                        }
                    }
                } else if (idealColumnDirection == Direction.r) {
                    if (idealRowDirection == Direction.d) {
                        resultDirection = Direction.l;
                    } else if (idealRowDirection == Direction.u) {
                        if (canMoveTo(this.getElementInDirection(Direction.d, maze))) {
                            resultDirection = Direction.d;
                        } else {
                            resultDirection = Direction.l;
                        }
                    } else if (idealRowDirection == Direction.n) {
                        if (canMoveTo(this.getElementInDirection(Direction.d, maze))) {
                            resultDirection = Direction.d;
                        } else {
                            resultDirection = Direction.l;
                        }
                    }
                }
            break;
            case l:
                if (idealColumnDirection == Direction.n) {
                    if (idealRowDirection == Direction.d) {
                        if (canMoveTo(this.getElementInDirection(Direction.r, maze))) {
                            resultDirection = Direction.r;
                        } else {
                            resultDirection = Direction.u;
                        }
                    } else {
                        if (canMoveTo(this.getElementInDirection(Direction.r, maze))) {
                            resultDirection = Direction.r;
                        } else {
                            resultDirection = Direction.d;
                        }
                    }
                
                } else if (idealColumnDirection == Direction.l) {
                    if (idealRowDirection == Direction.d) {
                        if (canMoveTo(this.getElementInDirection(Direction.r, maze))) {
                            resultDirection = Direction.r;
                        } else {
                            resultDirection = Direction.u;
                        }
                    } else if (idealRowDirection == Direction.u) {
                        if (canMoveTo(this.getElementInDirection(Direction.r, maze))) {
                            resultDirection = Direction.r;
                        } else {
                            resultDirection = Direction.d;
                        }
                    } else if (idealRowDirection == Direction.n) {
                        if (canMoveTo(this.getElementInDirection(Direction.d, maze))) {
                            resultDirection = Direction.d;
                        } else if (canMoveTo(this.getElementInDirection(Direction.u, maze))) {
                            resultDirection = Direction.u;
                        } else {
                            resultDirection = Direction.r;
                        }
                    }
                } else if (idealColumnDirection == Direction.r) {
                    if (idealRowDirection == Direction.d) {
                        resultDirection = Direction.u;
                    } else if (idealRowDirection == Direction.u) {
                        if (canMoveTo(this.getElementInDirection(Direction.d, maze))) {
                            resultDirection = Direction.d;
                        } else {
                            resultDirection = Direction.l;
                        }
                    } else if (idealRowDirection == Direction.n) {
                        if (canMoveTo(this.getElementInDirection(Direction.d, maze))) {
                            resultDirection = Direction.d;
                        } else {
                            resultDirection = Direction.u;
                        }
                    }
                }
            break;
            case r: 
                if (idealColumnDirection == Direction.n) {
                    if (idealRowDirection == Direction.d) {
                        if (canMoveTo(this.getElementInDirection(Direction.l, maze))) {
                            resultDirection = Direction.l;
                        } else {
                            resultDirection = Direction.u;
                        }
                    } else {
                        if (canMoveTo(this.getElementInDirection(Direction.l, maze))) {
                            resultDirection = Direction.l;
                        } else {
                            resultDirection = Direction.d;
                        }
                    }
                } else if (idealColumnDirection == Direction.l) {
                    if (idealRowDirection == Direction.d) {
                        if (canMoveTo(this.getElementInDirection(Direction.u, maze))) {
                            resultDirection = Direction.u;
                        } else {
                            resultDirection = Direction.r;
                        }
                    } else if (idealRowDirection == Direction.u) {
                        if (canMoveTo(this.getElementInDirection(Direction.d, maze))) {
                            resultDirection = Direction.d;
                        } else {
                            resultDirection = Direction.l;
                        }
                    } else if (idealRowDirection == Direction.n) {
                        if (canMoveTo(this.getElementInDirection(Direction.d, maze))) {
                            resultDirection = Direction.d;
                        } else if (canMoveTo(this.getElementInDirection(Direction.u, maze))) {
                            resultDirection = Direction.u;
                        } else {
                            resultDirection = Direction.r;
                        }
                    }
                } else if (idealColumnDirection == Direction.r) {
                    if (idealRowDirection == Direction.u) {
                        if (canMoveTo(this.getElementInDirection(Direction.l, maze))) {
                            resultDirection = Direction.l;
                        } else {
                            resultDirection = Direction.d;
                        }
                    } else if (idealRowDirection == Direction.d) {
                        if (canMoveTo(this.getElementInDirection(Direction.u, maze))) {
                            resultDirection = Direction.u;
                        } else {
                            resultDirection = Direction.l;
                        }
                    } else if (idealRowDirection == Direction.n) {
                        if (canMoveTo(this.getElementInDirection(Direction.d, maze))) {
                            resultDirection = Direction.d;
                        } else {
                            resultDirection = Direction.u;
                        }
                    }
                }
            break;
            default:
            return previousDirection;
        }
        if (!canMoveTo(this.getElementInDirection(resultDirection, maze))) {
            resultDirection = previousDirection;
        }
        return resultDirection;
    }

    private Direction idealRowDirection(Position targetPosition) {
        if (this.currentPosition.getRow() < targetPosition.getRow()) {
            return Direction.d;
        } else if (this.currentPosition.getRow() > targetPosition.getRow()) {
            return Direction.u;
        }
        return Direction.n;
    }

    private Direction idealColumnDirection(Position targePosition) {
        if (this.currentPosition.getColumn() < targePosition.getColumn()) {
            return Direction.r;
        } else if (this.currentPosition.getColumn() > targePosition.getColumn()) {
            return Direction.l;
        }
        return Direction.n;
    }
    
}
