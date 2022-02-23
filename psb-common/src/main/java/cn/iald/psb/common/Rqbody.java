package cn.iald.psb.common;

public class Rqbody {

    /**
     * accessToken : T*69jfS/n2n4Cw1UAdiiAkPQ==
     * data : {"xingming":"李连杰","xingbie":"1","minzu":"01","chushengriqi":"19560901","zhengjianleixing":"11","zhengjianhaoma":"123456789012","shengshixian":"450102","xiangzhi":"南宁市西乡塘区11号","canji":"0","ruzhushijian":"20190802114356","ruzhufanghao":"8088","zhaopian":"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","camerapic":"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","rxbdjg":"9","rxbdjgxsd":"9"}
     */

    private String accessToken;
    private DataBean data=new DataBean();

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * xingming : 李连杰
         * xingbie : 1
         * minzu : 01
         * chushengriqi : 19560901
         * zhengjianleixing : 11
         * zhengjianhaoma : 123456789012
         * shengshixian : 450102
         * xiangzhi : 南宁市西乡塘区11号
         * canji : 0
         * ruzhushijian : 20190802114356
         * ruzhufanghao : 8088
         * zhaopian : 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
         * camerapic : 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
         * rxbdjg : 9
         * rxbdjgxsd : 9
         */

        private String xingming;
        private String xingbie;
        private String minzu;
        private String chushengriqi;
        private String zhengjianleixing;
        private String zhengjianhaoma;
        private String shengshixian;
        private String xiangzhi;
        private String canji;
        private String ruzhushijian;
        private String ruzhufanghao;
        private String zhaopian;
        private String camerapic;
        private String rxbdjg;
        private String rxbdjgxsd;
        private String lastname;
        private String firstname;
        private String guoji;
        private String qianzhengzhonglei;
        private String rujingriqi;
        private String rujingkouan;
        private String huzhaofjy1;
        private String huzhaofjy2;
        private String huzhaofjy3;


        public String getLastname() {
            return lastname;
        }

        public DataBean setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public String getFirstname() {
            return firstname;
        }

        public DataBean setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public String getGuoji() {
            return guoji;
        }

        public DataBean setGuoji(String guoji) {
            this.guoji = guoji;
            return this;
        }

        public String getQianzhengzhonglei() {
            return qianzhengzhonglei;
        }

        public DataBean setQianzhengzhonglei(String qianzhengzhonglei) {
            this.qianzhengzhonglei = qianzhengzhonglei;
            return this;
        }

        public String getRujingriqi() {
            return rujingriqi;
        }

        public DataBean setRujingriqi(String rujingriqi) {
            this.rujingriqi = rujingriqi;
            return this;
        }

        public String getRujingkouan() {
            return rujingkouan;
        }

        public DataBean setRujingkouan(String rujingkouan) {
            this.rujingkouan = rujingkouan;
            return this;
        }

        public String getHuzhaofjy1() {
            return huzhaofjy1;
        }

        public DataBean setHuzhaofjy1(String huzhaofjy1) {
            this.huzhaofjy1 = huzhaofjy1;
            return this;
        }

        public String getHuzhaofjy2() {
            return huzhaofjy2;
        }

        public DataBean setHuzhaofjy2(String huzhaofjy2) {
            this.huzhaofjy2 = huzhaofjy2;
            return this;
        }

        public String getHuzhaofjy3() {
            return huzhaofjy3;
        }

        public DataBean setHuzhaofjy3(String huzhaofjy3) {
            this.huzhaofjy3 = huzhaofjy3;
            return this;
        }

        public String getXingming() {
            return xingming;
        }

        public DataBean setXingming(String xingming) {
            this.xingming = xingming;
            return this;
        }

        public String getXingbie() {
            return xingbie;
        }

        public DataBean setXingbie(String xingbie) {
            this.xingbie = xingbie;
            return this;
        }

        public String getMinzu() {
            return minzu;
        }

        public DataBean setMinzu(String minzu) {
            this.minzu = minzu;
            return this;
        }

        public String getChushengriqi() {
            return chushengriqi;
        }

        public DataBean setChushengriqi(String chushengriqi) {
            this.chushengriqi = chushengriqi;
            return this;
        }

        public String getZhengjianleixing() {
            return zhengjianleixing;
        }

        public DataBean setZhengjianleixing(String zhengjianleixing) {
            this.zhengjianleixing = zhengjianleixing;
            return this;
        }

        public String getZhengjianhaoma() {
            return zhengjianhaoma;
        }

        public DataBean setZhengjianhaoma(String zhengjianhaoma) {
            this.zhengjianhaoma = zhengjianhaoma;
            return this;
        }

        public String getShengshixian() {
            return shengshixian;
        }

        public DataBean setShengshixian(String shengshixian) {
            this.shengshixian = shengshixian;
            return this;
        }

        public String getXiangzhi() {
            return xiangzhi;
        }

        public DataBean setXiangzhi(String xiangzhi) {
            this.xiangzhi = xiangzhi;
            return this;
        }

        public String getCanji() {
            return canji;
        }

        public DataBean setCanji(String canji) {
            this.canji = canji;
            return this;
        }

        public String getRuzhushijian() {
            return ruzhushijian;
        }

        public DataBean setRuzhushijian(String ruzhushijian) {
            this.ruzhushijian = ruzhushijian;
            return this;
        }

        public String getRuzhufanghao() {
            return ruzhufanghao;
        }

        public DataBean setRuzhufanghao(String ruzhufanghao) {
            this.ruzhufanghao = ruzhufanghao;
            return this;
        }

        public String getZhaopian() {
            return zhaopian;
        }

        public DataBean setZhaopian(String zhaopian) {
            this.zhaopian = zhaopian;
            return this;
        }

        public String getCamerapic() {
            return camerapic;
        }

        public DataBean setCamerapic(String camerapic) {
            this.camerapic = camerapic;
            return this;
        }

        public String getRxbdjg() {
            return rxbdjg;
        }

        public DataBean setRxbdjg(String rxbdjg) {
            this.rxbdjg = rxbdjg;
            return this;
        }

        public String getRxbdjgxsd() {
            return rxbdjgxsd;
        }

        public DataBean setRxbdjgxsd(String rxbdjgxsd) {
            this.rxbdjgxsd = rxbdjgxsd;
            return this;
        }
    }
}
