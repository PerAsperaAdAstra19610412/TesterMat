package ynv.nigmus.testermat;

class ResultRecyclerVievItem {

    private int imageresult;
    private String result;
    private String timer;


    public ResultRecyclerVievItem(int image, String result, String timer) {
        this.imageresult = image;
        this.result = result;
        this.timer = timer;
    }


    public void setImageresult(int imageresult) {
        this.imageresult = imageresult;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }


    public int getImageresult() {
        return imageresult;
    }

    public String getResult() {
        return result;
    }

    public String getTimer() {
        return timer;
    }

}
