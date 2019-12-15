package be.annelyse.util;

public enum Color{

        BLACK(0, " "),
        WHITE(1,"#");

        private Integer intCode;
        private String symbol;

        Color(int intCode, String symbol){
            this.intCode = intCode;
            this.symbol = symbol;
        }

        public Integer getIntCode() {
            return intCode;
        }

        public String getSymbol() {
            return symbol;
        }

    @Override
    public String toString() {
        return symbol;
    }
}