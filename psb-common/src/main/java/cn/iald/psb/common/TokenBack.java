package cn.iald.psb.common;

public class TokenBack {

    /**
     * flag : ok
     * msg :
     * data : {"access_token":"T*pzCb3Sb6QgJw92eqvc9OEQ==","expires_in":"20190808011926"}
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
         * access_token : T*pzCb3Sb6QgJw92eqvc9OEQ==
         * expires_in : 20190808011926
         */

        private String access_token;
        private String expires_in;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(String expires_in) {
            this.expires_in = expires_in;
        }
    }
}
