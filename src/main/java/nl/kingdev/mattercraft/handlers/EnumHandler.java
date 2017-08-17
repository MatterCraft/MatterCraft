package nl.kingdev.mattercraft.handlers;

public class EnumHandler {

    public enum CompressionStoneTiers {
        JUNIOR("junior", 0),
        IMPROVED("improved", 1),
        ADVANCED("advanced", 2),
        MASTER("master", 3),
        GODLIKE("godlike", 4);

        private String name;
        private int id;

        CompressionStoneTiers(String name, int id) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }


        public String toString() {
            return name;
        }
    }

    public enum WaterTankTiers {
        FIRST("first", 0),
        SECOND("second", 1),
        THIRD("third", 2),
        FOURTH("fourth", 3),
        FIFTH("fifth", 4),
        SIXTH("sixth", 5),
        SEVENTH("seventh", 6),
        FULL("full", 7),
        INFINITE("infinite", 8);

        private String name;
        private int id;

        WaterTankTiers(String name, int id) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }


        public String toString() {
            return name;
        }
    }

}
