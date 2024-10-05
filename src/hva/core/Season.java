package hva.core;

public enum Season {
    Spring,
    Summer,
    Fall,
    Winter;

    public Season nextSeason() {
        Season[] seasonsArray = Season.values();
        return seasonsArray[(this.ordinal() + 1) % seasonsArray.length];
      }
}
