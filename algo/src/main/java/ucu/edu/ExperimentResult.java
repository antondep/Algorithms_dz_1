package ucu.edu;

public class ExperimentResult {
    public int dataSize;
    public long op1, op2, op3, totalOps; 
    public long memoryMB;

    public ExperimentResult(int dataSize, long op1, long op2, long op3, long totalOps, long memoryMB) {
        this.dataSize = dataSize;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.totalOps = totalOps;
        this.memoryMB = memoryMB;
    }

    @Override
    public String toString() {
        return String.format("Size=%d, Total=%d Top100:%d, SetRating:%d, BestGroup:%d, Mem=%dMB",
                dataSize, totalOps, op1, op2, op3, memoryMB);
    }
}
