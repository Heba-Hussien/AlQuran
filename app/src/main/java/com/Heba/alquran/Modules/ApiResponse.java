package com.Heba.alquran.Modules;

public class ApiResponse {
    String code,status;
    Data data;

    public ApiResponse(String code, String status, Data dataopject) {
        this.code = code;
        this.status = status;
        this.data = dataopject;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}


