package be.annelyse.util;

public enum Direction {


        LEFT{
            @Override
            public Direction turnLeft() {
                return DOWN;
            }

            @Override
            public Direction turnRight() {
                return UP;
            }
        },
        RIGHT{
            @Override
            public Direction turnLeft() {
                return UP;
            }

            @Override
            public Direction turnRight() {
                return DOWN;
            }
        },
        UP{
            @Override
            public Direction turnLeft() {
                return LEFT;
            }

            @Override
            public Direction turnRight() {
                return RIGHT;
            }
        },
        DOWN{
            @Override
            public Direction turnLeft() {
                return RIGHT;
            }

            @Override
            public Direction turnRight() {
                return LEFT;
            }
        };

        public abstract Direction turnLeft();

        public abstract Direction turnRight();
}
