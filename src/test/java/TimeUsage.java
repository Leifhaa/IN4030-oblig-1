public class TimeUsage {

    public TimeUsage() {
    }

    public TimeUsage(double a1, double a2_K20, double a2_K100) {
        A1 = a1;
        A2_K20 = a2_K20;
        A2_K100 = a2_K100;
    }

    private double A1;
    private double A2_K20;
    private double A2_K100;

    public double getA1() {
        return A1;
    }

    public double getA2_K20() {
        return A2_K20;
    }

    public double getA2_K100() {
        return A2_K100;
    }

    public void setA1(double a1) {
        A1 = a1;
    }

    public void setA2_K20(double a2_K20) {
        A2_K20 = a2_K20;
    }

    public void setA2_K100(double a2_K100) {
        A2_K100 = a2_K100;
    }

    @Override
    public String toString() {
        return "TimeUsage{" +
                "A1=" + A1 +
                ", A2_K20=" + A2_K20 +
                ", A2_K100=" + A2_K100 +
                '}';
    }
}
