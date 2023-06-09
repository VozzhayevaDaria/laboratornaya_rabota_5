package CollectionStrusture;

public enum MusicGenre {
    ROCK{
        @Override
        public String toString() {
            return "Rock";
        }
    },
    PROGRESSIVE_ROCK{
        @Override
        public String toString() {
            return "Progressive rock";
        }
    },
    POST_PUNK{
        @Override
        public String toString() {
            return "Post punk";
        }
    },
    POST_ROCK{
        @Override
        public String toString() {
            return "Post rock";
        }
    },
    PUNK_ROCK{
        @Override
        public String toString() {
            return "Punk rock";
        }
    };
}
