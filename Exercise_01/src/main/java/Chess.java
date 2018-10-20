public class Chess {
    private final String name, fidelID, fED, rtg, club;

    Chess(String test_name,String test_fidelID,String test_FED,String test_Rtg,String test_Club) {
        this.name = test_name;
        this.fidelID = test_fidelID;
        this.fED = test_FED;
        this.rtg = test_Rtg;
        this.club = test_Club;
    }

    public String getName() {
        return name;
    }

    public String getFidelID() {
        return fidelID;
    }

    public String getFED() {
        return fED;
    }

    public String getRtg() {
        return rtg;
    }

    public String getClub() {
        return club;
    }
}
