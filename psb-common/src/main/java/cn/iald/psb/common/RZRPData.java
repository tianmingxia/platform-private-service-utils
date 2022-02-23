package cn.iald.psb.common;

public class RZRPData {

    /**
     * flag : ok
     * msg :
     * data : {"lvkedm":"0001201908070005","rzuuid":"af66b1179ff546c39d019057e00bd731"}
     */

    private String flag;
    private String msg;
    private DataBean data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lvkedm : 0001201908070005
         * rzuuid : af66b1179ff546c39d019057e00bd731
         */

        private String lvkedm;
        private String rzuuid;

        public String getLvkedm() {
            return lvkedm;
        }

        public void setLvkedm(String lvkedm) {
            this.lvkedm = lvkedm;
        }

        public String getRzuuid() {
            return rzuuid;
        }

        public void setRzuuid(String rzuuid) {
            this.rzuuid = rzuuid;
        }
    }
}
