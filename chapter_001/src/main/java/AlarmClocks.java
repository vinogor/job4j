public class AlarmClocks {

    public int timeToWakeUp(int[] nxk, int[] timeToRing) {

        int numOfClocks = nxk[0];
        int periodOfRinging = nxk[1];
        int ringsToWakeUp = nxk[2];

        int ringCounter = 0;
        int currentTime = 0;

        do {
            currentTime++;
            for (int j = 0; j < numOfClocks; j++) {
                if (((currentTime - timeToRing[j]) >= 0) && ((currentTime - timeToRing[j]) % periodOfRinging == 0)) {
                    ringCounter++;
                    break;
                }
            }
        } while (ringCounter != ringsToWakeUp);
        return currentTime;
    }
}