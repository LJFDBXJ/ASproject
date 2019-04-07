package android.weather.lifdbxj.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/2/14.
 * procinceName记录省的名字，
 * provinceName记录省的代号，
 * 另外LitePal中的每个实体类全部都继承DataSupport.
 */

public class Province extends DataSupport {
    private int id;
    private String provinceName;
    private int provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
