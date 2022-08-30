package Database;
/*
В базу данныз записываются только правельные ответы.
Эфективность проверяется по времени решения.
 */
//MatEksempel - математический пример в базе данных
class MatEksempel {

    private int id;
    private int x;
    private String operator;
    private int y;
    private int result;
    private long milliseconds;// время потраченое на решение.

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public String getOperator() {
        return operator;
    }

    public int getY() {
        return y;
    }

    public int getResult() {
        return result;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public MatEksempel(int x, String operator, int y, int result, long milliseconds) {
        this.x = x;
        this.operator = operator;
        this.y = y;
        this.result = result;
        this.milliseconds = milliseconds;
    }

    public MatEksempel(int id, int x, String operator, int y, int result, long milliseconds) {
        this.id = id;
        this.x = x;
        this.operator = operator;
        this.y = y;
        this.result = result;
        this.milliseconds = milliseconds;
    }
}
