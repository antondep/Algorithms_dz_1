package ucu.edu;

public class ExperimentResult {
    public final String dataTag;
    public final String containerType;
    public final long top100Nanos;
    public final long bestGroupNanos;
    public final long setRatingNanos;
    public final long usedMemoryMB;

    public ExperimentResult(String dataTag, String containerType,
            long top100Nanos, long bestGroupNanos, long setRatingNanos, long usedMemoryMB) {
        this.dataTag = dataTag;
        this.containerType = containerType;
        this.top100Nanos = top100Nanos;
        this.bestGroupNanos = bestGroupNanos;
        this.setRatingNanos = setRatingNanos;
        this.usedMemoryMB = usedMemoryMB;
    }

    public String toCSVLine() {
        return String.format("\"%s\",\"%s\",%.3f,%.3f,%.3f,%.3f",
                dataTag,
                containerType,
                top100Nanos / 1_000_000.0,
                bestGroupNanos / 1_000_000.0,
                setRatingNanos / 1_000_000.0,
                usedMemoryMB / 1.0);
    }

    public static String csvHeader() {
        return "dataTag,container,top100_ms,bestGroup_ms,setRating_ms,memory_mb";
    }
}
